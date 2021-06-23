package spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.web.bind.annotation.*;
import spring.model.geocode.GeocodeResult;

import java.io.IOException;
import java.net.URLEncoder;

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
				.url("https://google-maps-geocoding.p.rapidapi.com/geocode/json?language=en&address=" + encodedAddress)
				.get()
				.addHeader("x-rapidapi-host", "google-maps-geocoding.p.rapidapi.com")
				.addHeader("x-rapidapi-key", API_KEY)
				.build();
		
		ResponseBody responseBody = client.newCall(request).execute().body();
		ObjectMapper objectMapper = new ObjectMapper();
		GeocodeResult result = objectMapper.readValue(responseBody.string(), GeocodeResult.class);
		return result;
	}
	
}
