package pl.denys.karol.CompanysManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompanyBasicToAddDTO {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String zipCode;
}
