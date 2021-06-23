package spring.validation;

import org.apache.logging.log4j.util.Strings;
import spring.controller.GeocodeController;
import spring.model.address.Address;
import spring.model.geocode.GeocodeResult;
import java.io.IOException;

public class ValidacaoModel {
	
	public void devePreencherLatitudeLongitude(Address address) throws IOException {
		if (address != null && Strings.isEmpty(address.getLatitude()) || Strings.isEmpty(address.getLongitude())) {
			GeocodeController geocodeController = new GeocodeController();
			GeocodeResult result = geocodeController.getGeocode(formatarAddressParaRequisicao(address));
			
			if (result != null && result.getResults() != null && !result.getResults().isEmpty()) {
				address.setLatitude(result.getResults().get(0).getGeometry().getGeocodeLocation().getLatitude());
				address.setLongitude(result.getResults().get(0).getGeometry().getGeocodeLocation().getLongitude());
			}
		}
	}
	
	private String formatarAddressParaRequisicao(Address address) {
		StringBuilder addressBuilder = new StringBuilder();
		
		addStringBuilder(addressBuilder, address.getNumber());
		addStringBuilder(addressBuilder, address.getStreetName());
		addStringBuilder(addressBuilder, address.getNeighbourhood());
		addStringBuilder(addressBuilder, address.getComplement());
		addStringBuilder(addressBuilder, address.getZipcode());
		addStringBuilder(addressBuilder, address.getCity());
		addStringBuilder(addressBuilder, address.getState());
		addStringBuilder(addressBuilder, address.getCountry());
		
		return addressBuilder.toString();
	}
	
	private void addStringBuilder(StringBuilder addressBuilder, String value) {
		if (Strings.isNotEmpty(value)) {
			addressBuilder.append(value);
			addressBuilder.append(",");
		}
	}
	
	
}
