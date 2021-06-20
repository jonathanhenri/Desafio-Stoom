package spring.controller;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.exception.ResourceNotFoundException;
import spring.model.Dog;
import spring.repository.DogRepository;

@CrossOrigin(origins = "http://localhost:4200")
@Api(tags = "Api dos Dogs")
@RestController
@RequestMapping("/api/v1")
public class DogController {
	@Autowired
	private DogRepository dogsRepository;

	@GetMapping("/dogs")
	public List<Dog> getAllDogs() {
		return dogsRepository.findAll();
	}

	@GetMapping("/dogs/{id}")
	public ResponseEntity<Dog> getDogById(@PathVariable(value = "id") Long dogId)
			throws ResourceNotFoundException {
		Dog dog = dogsRepository.findById(dogId)
				.orElseThrow(() -> new ResourceNotFoundException("Dog not found for this id :: " + dogId));
		return ResponseEntity.ok().body(dog);
	}

	@PostMapping("/dogs")
	public Dog createDog(@Valid @RequestBody Dog dog) {
		return dogsRepository.save(dog);
	}

	@PutMapping("/dogs/{id}")
	public ResponseEntity<Dog> updateDog(@PathVariable(value = "id") Long dogId,
											  @Valid @RequestBody Dog dogDetails) throws ResourceNotFoundException {
		Dog dog = dogsRepository.findById(dogId)
				.orElseThrow(() -> new ResourceNotFoundException("Dog not found for this id :: " + dogId));

		dog.setNomeDono(dogDetails.getNomeDono());
		dog.setRaca(dogDetails.getRaca());
		dog.setNome(dogDetails.getNome());
		final Dog updatedDog = dogsRepository.save(dog);
		return ResponseEntity.ok(updatedDog);
	}

	@DeleteMapping("/dogs/{id}")
	public Map<String, Boolean> deleteDog(@PathVariable(value = "id") Long dogId)
			throws ResourceNotFoundException {
		Dog dog = dogsRepository.findById(dogId)
				.orElseThrow(() -> new ResourceNotFoundException("Dog not found for this id :: " + dogId));

		dogsRepository.delete(dog);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
