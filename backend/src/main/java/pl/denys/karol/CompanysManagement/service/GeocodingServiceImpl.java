package pl.denys.karol.CompanysManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Map;

@Service
public class GeocodingServiceImpl implements GeocodingService {

    @Value("${google.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public void checkApiKey() {
        if (apiKey.isEmpty()) {
            throw new IllegalStateException("Google Maps API key is missing!");
        }

        String testUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=Test&key=" + apiKey;
        Map<String, Object> response = restTemplate.getForObject(testUrl, Map.class);

        if (response != null && "REQUEST_DENIED".equals(response.get("status"))) {
            throw new IllegalStateException("Google Maps API key is invalid or missing permissions!");
        } else if (response == null) {
            throw new IllegalStateException("Error contacting Google Maps API.");
        }
    }

    @Override
    public double[] getCoordinates(String address, String city, String zipCode) {


        String formattedAddress = address.replace(" ", "+");
        String formattedCity = city.replace(" ", "+");
        String formattedZipCode = zipCode.replace(" ", "+");
        checkApiKey();
        String fullAddress = formattedAddress + "," + formattedCity + "," + formattedZipCode;
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + fullAddress + "&key=" + apiKey;

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response != null && "OK".equals(response.get("status"))) {
            List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");
            if (!results.isEmpty()) {
                Map<String, Object> location = (Map<String, Object>) results.get(0).get("geometry");
                Map<String, Object> coordinates = (Map<String, Object>) location.get("location");
                double latitude = (double) coordinates.get("lat");
                double longitude = (double) coordinates.get("lng");
                return new double[]{latitude, longitude};
            }
        }

        throw new RuntimeException("Unable to get coordinates for address: " + fullAddress);
    }
}
