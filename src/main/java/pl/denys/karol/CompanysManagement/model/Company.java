package pl.denys.karol.CompanysManagement.model;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;


import java.util.List;

@Entity
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String address;
    private String city;
    private String zipCode;
    private Double latitude;
    private Double longitude;

    @Transient
    @Column(columnDefinition = "GEOGRAPHY(Point, 4326)")
    private Point location;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContactPerson> contactPersons;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Meeting> meetings;

   
}
