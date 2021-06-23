package spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import io.swagger.annotations.Api;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.web.bind.annotation.*;
import spring.model.geocode.GeocodeResult;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Api(tags = "Api Geocode")
@RestController
@RequestMapping("/api/v1/geocode")
public class GeocodeController {
	public static final String API_KEY = "AIzaSyCj0cY2yEvVfYhAaTz3-P2MW-YRKmhz5Uw";
	
	@GetMapping("/geocode")
	public GeocodeResult getGeocode(@RequestParam String address) throws IOException {
		OkHttpClient client = new OkHttpClient();
		String encodedAddress = URLEncoder.encode(address, "UTF-8");
		Request request = new Request.Builder()
				.url("https://maps.googleapis.com/maps/api/geocode/json?language=pt-BR&address=" + encodedAddress+"&key="+API_KEY)
				.get()
				.build();
		
		ResponseBody responseBody = client.newCall(request).execute().body();
		ObjectMapper objectMapper = new ObjectMapper();
		GeocodeResult result = objectMapper.readValue(responseBody.string(), GeocodeResult.class);
		return result;
	}
}
