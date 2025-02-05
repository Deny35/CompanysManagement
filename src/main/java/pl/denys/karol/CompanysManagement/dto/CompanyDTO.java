package pl.denys.karol.CompanysManagement.dto;

import lombok.*;

@Data
public class CompanyDTO {

    private Long id;
    private String name;
    private String address;
    private String city;
    private String zipCode;
    private Double latitude;
    private Double longitude;

}
