package spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import spring.model.Dog;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DogControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllDogs() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/dogs",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetDogById() {
		Dog dog = restTemplate.getForObject(getRootUrl() + "/dogs/1", Dog.class);
		System.out.println(dog.getNome());
		assertNotNull(dog);
	}

	@Test
	public void testCreateDog() {
		Dog dog = new Dog();
		dog.setNomeDono("Jonathan");
		dog.setNome("Cacau");
		dog.setRaca("vira-lata");

		ResponseEntity<Dog> postResponse = restTemplate.postForEntity(getRootUrl() + "/dogs", dog, Dog.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateDog() {
		int id = 1;
		Dog dog = restTemplate.getForObject(getRootUrl() + "/dogs/" + id, Dog.class);
		dog.setNome("Cacau");
		dog.setRaca("vira-lata");

		restTemplate.put(getRootUrl() + "/dogs/" + id, dog);

		Dog updatedDog = restTemplate.getForObject(getRootUrl() + "/dogs/" + id, Dog.class);
		assertNotNull(updatedDog);
	}

	@Test
	public void testDeleteDog() {
		int id = 2;
		Dog dog = restTemplate.getForObject(getRootUrl() + "/dogs/" + id, Dog.class);
		assertNotNull(dog);

		restTemplate.delete(getRootUrl() + "/dogs/" + id);

		try {
			dog = restTemplate.getForObject(getRootUrl() + "/dogs/" + id, Dog.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
