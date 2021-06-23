package spring.controller;

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
import org.springframework.web.bind.annotation.RestController;
import spring.exception.ResourceNotFoundException;
import spring.model.address.Address;
import spring.repository.AddressRepository;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@Api(tags = "Api dos Enderecos")
@RestController
@RequestMapping("/api/v1")
public class AddressController {
	@Autowired
	private AddressRepository addressRepository;
	
	@GetMapping("/address")
	public List<Address> getAllAddress() {
		return addressRepository.findAll();
	}

	@GetMapping("/address/{id}")
	public ResponseEntity<Address> getAddresById(@PathVariable(value = "id") Long addressId)
			throws ResourceNotFoundException {
		Address address = addressRepository.findById(addressId)
				.orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + addressId));
		return ResponseEntity.ok().body(address);
	}

	@PostMapping("/address")
	public Address createAddres(@RequestBody Address address) {
		return addressRepository.save(address);
	}

	@PutMapping("/address/{id}")
	public ResponseEntity<Address> updateAddres(@PathVariable(value = "id") Long addressId,
											 @Valid @RequestBody Address addressDetails) throws ResourceNotFoundException {
		Address address = addressRepository.findById(addressId)
				.orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + addressId));
		
		
		addressDetails.setId(address.getId());
		final Address updatedAddress = addressRepository.save(addressDetails);
		return ResponseEntity.ok(updatedAddress);
	}

	@DeleteMapping("/address/{id}")
	public Map<String, Boolean> deleteAddres(@PathVariable(value = "id") Long addressId)
			throws ResourceNotFoundException {
		Address address = addressRepository.findById(addressId)
				.orElseThrow(() -> new ResourceNotFoundException("Address not found for this id :: " + addressId));

		addressRepository.delete(address);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
