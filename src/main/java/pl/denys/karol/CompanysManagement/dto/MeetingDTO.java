package pl.denys.karol.CompanysManagement.dto;

import lombok.*;

import java.time.LocalDate;

@Data
public class MeetingDTO {
    private Long id;
    private LocalDate date;
    private String topic;
    private String notes;
    private Long companyId;
}
