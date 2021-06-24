package spring;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import spring.model.address.Address;
import spring.repository.AddressRepository;
import spring.validation.ValidacaoModel;
import java.io.IOException;
import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTests {
	
	@Mock
	public AddressRepository addressRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void deveBuscarLatitudeLongitude() throws IOException {
		Address address = getAddress();
		
		ValidacaoModel validacaoModel = new ValidacaoModel();
		validacaoModel.devePreencherLatitudeLongitude(address);
		
		Assert.assertNotNull(address.getLatitude());
		Assert.assertNotNull(address.getLongitude());
	}
	
	@Test
	public void deveIncluir() {
		Address address = getAddress();
		
		Mockito.doReturn(address).when(addressRepository).save(address);
		
		when(addressRepository.findAll()).thenReturn(Arrays.asList(address));
		
		Assert.assertNotNull(addressRepository.findAll());
	}
	
	@Test
	public void deveAlterar() {
		Address address = getAddress();
		
		Mockito.doReturn(address).when(addressRepository).save(address);
		
		when(addressRepository.findAll()).thenReturn(Arrays.asList(address));
		
		Assert.assertNotNull(addressRepository.findAll());
		
		address.setStreetName("alterado");
		
		Mockito.doReturn(address).when(addressRepository).save(address);
		
		when(addressRepository.getAddressById(1l)).thenReturn(address);
		
		Address addressAlterado = addressRepository.getAddressById(1l);
		
		Assert.assertEquals("alterado", addressAlterado.getStreetName());
	}
	
	@Test
	public void deveRemover() {
		Address address = getAddress();
		
		Mockito.doReturn(address).when(addressRepository).save(address);
		
		when(addressRepository.findAll()).thenReturn(Arrays.asList(address));
		
		Assert.assertNotNull(addressRepository.findAll());
		
		addressRepository.delete(address);
		
		verify(addressRepository).delete(address);
	}
	
	public Address getAddress() {
		Address address = new Address();
		address.setStreetName("Rua saturno");
		address.setNumber("17");
		address.setComplement("Q16");
		address.setNeighbourhood("Arvore na frente");
		address.setCity("Anápolis");
		address.setState("Goias");
		address.setCountry("Brasil");
		address.setZipcode("75053840");
		return address;
	}

}
