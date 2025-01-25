package pl.denys.karol.CompanysManagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String topic;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
