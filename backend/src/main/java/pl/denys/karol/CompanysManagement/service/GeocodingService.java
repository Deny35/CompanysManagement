package pl.denys.karol.CompanysManagement.service;

public interface GeocodingService {
    double[] getCoordinates(String address, String city, String zipCode);
}
