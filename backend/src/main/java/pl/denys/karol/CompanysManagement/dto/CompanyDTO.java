package pl.denys.karol.CompanysManagement.dto;

import java.util.List;
import lombok.*;

@Data
@AllArgsConstructor
public class CompanyDTO {

    private Long id;
    private String name;
    private String address;
    private String city;
    private String zipCode;
    private Double latitude;
    private Double longitude;

    private List<ContactPersonDTO> contactPersons;
    private List<MeetingDTO> meetings;
}
