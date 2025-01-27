package pl.denys.karol.CompanysManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.denys.karol.CompanysManagement.model.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE company SET location = ST_SetSRID(ST_MakePoint(:longitude, :latitude), 4326), latitude = :latitude, longitude = :longitude WHERE id = :id", nativeQuery = true)
    void updateLocation(@Param("id") Long id, @Param("latitude") double latitude, @Param("longitude") double longitude);
}
