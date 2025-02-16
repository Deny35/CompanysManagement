package pl.denys.karol.CompanysManagement.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate lastMeetingDate; 
}