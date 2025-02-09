package pl.denys.karol.CompanysManagement.service;

import org.springframework.http.ResponseEntity;
import java.util.List;
import pl.denys.karol.CompanysManagement.dto.MeetingDTO;

public interface MeetingService {
    ResponseEntity<MeetingDTO> addMeeting(MeetingDTO meetingDTO);
    ResponseEntity<List<MeetingDTO>> getAllMeetingsByCompany(Long companyId);
    ResponseEntity<MeetingDTO> getMeetingById(Long id);
}
