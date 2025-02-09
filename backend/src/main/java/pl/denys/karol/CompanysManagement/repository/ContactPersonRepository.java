package pl.denys.karol.CompanysManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.denys.karol.CompanysManagement.model.ContactPerson;

import java.util.List;

public interface ContactPersonRepository extends JpaRepository<ContactPerson, Long> {
    List<ContactPerson> findByCompanyId(Long companyId);
}
