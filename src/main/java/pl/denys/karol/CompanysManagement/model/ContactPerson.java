package pl.denys.karol.CompanysManagement.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ContactPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String position;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}
