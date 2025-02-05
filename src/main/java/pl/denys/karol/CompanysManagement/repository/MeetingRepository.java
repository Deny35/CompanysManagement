package pl.denys.karol.CompanysManagement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.denys.karol.CompanysManagement.model.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findByCompanyId(Long companyId);
}
