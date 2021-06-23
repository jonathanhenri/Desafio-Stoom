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

import spring.model.address.Address;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressControllerIntegrationTest {
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
	public void testGetAllAddress() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/address",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetAddressById() {
		Address address = restTemplate.getForObject(getRootUrl() + "/address/1", Address.class);
		assertNotNull(address);
	}

	@Test
	public void testCreateAddress() {
		Address address = new Address();
		address.setStreetName("Rua saturno");
		address.setNumber("17");
		address.setComplement("Q16");
		address.setNeighbourhood("Arvore na frente");
		address.setCity("Anápolis");
		address.setState("Goias");
		address.setCountry("Brasil");
		address.setZipcode("75053840");
		address.setLatitude("-16.26984451257575");
		address.setLongitude("-48.978837664993755");

		ResponseEntity<Address> postResponse = restTemplate.postForEntity(getRootUrl() + "/address", address, Address.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateAddress() {
		int id = 1;
		Address address = restTemplate.getForObject(getRootUrl() + "/address/" + id, Address.class);
		address.setStreetName("Rua saturno");
		address.setNumber("17");
		address.setComplement("Q16");
		address.setNeighbourhood("Arvore na frente");
		address.setCity("Anápolis");
		address.setState("Goias");
		address.setCountry("Brasil");
		address.setZipcode("75053840");
		address.setLatitude("-16.26984451257575");
		address.setLongitude("-48.978837664993755");

		restTemplate.put(getRootUrl() + "/address/" + id, address);

		Address updatedAddress = restTemplate.getForObject(getRootUrl() + "/address/" + id, Address.class);
		assertNotNull(updatedAddress);
	}

	@Test
	public void testDeleteAddress() {
		int id = 2;
		Address address = restTemplate.getForObject(getRootUrl() + "/address/" + id, Address.class);
		assertNotNull(address);

		restTemplate.delete(getRootUrl() + "/address/" + id);

		try {
			address = restTemplate.getForObject(getRootUrl() + "/address/" + id, Address.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
