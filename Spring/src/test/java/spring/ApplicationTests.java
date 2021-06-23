package spring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.model.address.Address;
import spring.validation.ValidacaoModel;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void deveBuscarLatitudeLongitude() throws IOException {
		Address address = new Address();
		address.setStreetName("Rua saturno");
		address.setNumber("17");
		address.setComplement("Q16");
		address.setNeighbourhood("Arvore na frente");
		address.setCity("Anápolis");
		address.setState("Goias");
		address.setCountry("Brasil");
		address.setZipcode("75053840");
		
		ValidacaoModel validacaoModel = new ValidacaoModel();
		validacaoModel.devePreencherLatitudeLongitude(address);
		
		Assert.assertNotNull(address.getLatitude());
		Assert.assertNotNull(address.getLongitude());
	}

}
