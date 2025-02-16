package pl.denys.karol.CompanysManagement.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class ContactPersonDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String position;
    private Long companyId;

}
