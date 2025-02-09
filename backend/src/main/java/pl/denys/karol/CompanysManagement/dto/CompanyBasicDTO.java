package pl.denys.karol.CompanysManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyBasicDTO {
    private Long id;
    private String name;
    private String city;
    private Double latitude;
    private Double longitude;
}