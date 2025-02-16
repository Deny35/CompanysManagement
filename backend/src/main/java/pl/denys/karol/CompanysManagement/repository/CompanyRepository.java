package pl.denys.karol.CompanysManagement.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.denys.karol.CompanysManagement.model.Company;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    
    @Modifying
    @Query(value = "UPDATE company SET location = ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326), latitude = :latitude, longitude = :longitude WHERE id = :id", nativeQuery = true)
    void updateLocation(@Param("id") Long id, @Param("latitude") double latitude, @Param("longitude") double longitude);

    @Query(value = "SELECT c.id, c.name, c.city, COALESCE(c.latitude, 0.0), COALESCE(c.longitude, 0.0), (SELECT MAX(m.date) FROM meeting m WHERE m.company_id = c.id) as lastMeetingDate FROM company c", nativeQuery = true)
    List<Object[]> findAllWithLastMeeting();
    
    @Query(value = """
        SELECT c.id, c.name, c.city, 
               COALESCE(c.latitude, 0.0), COALESCE(c.longitude, 0.0),
               (SELECT MAX(m.date) FROM meeting m WHERE m.company_id = c.id) as lastMeetingDate
        FROM company c
        LEFT JOIN meeting m ON c.id = m.company_id
        GROUP BY c.id, c.name, c.city, c.latitude, c.longitude
        HAVING MAX(m.date) IS NULL OR MAX(m.date) >= CAST(:startDate AS DATE)
    """, nativeQuery = true)
    List<Object[]> findCompaniesWithLastMeetingAfter(@Param("startDate") LocalDate startDate);
    
}
