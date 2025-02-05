package pl.denys.karol.CompanysManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.denys.karol.CompanysManagement.dto.MeetingDTO;
import pl.denys.karol.CompanysManagement.exception.CompanyNotFoundException;
import pl.denys.karol.CompanysManagement.mapper.MeetingMapper;
import pl.denys.karol.CompanysManagement.model.Company;
import pl.denys.karol.CompanysManagement.model.Meeting;
import pl.denys.karol.CompanysManagement.repository.CompanyRepository;
import pl.denys.karol.CompanysManagement.repository.MeetingRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    private MeetingRepository meetingRepository;
    
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private MeetingMapper meetingMapper;

    @Override
    public ResponseEntity<MeetingDTO> addMeeting(MeetingDTO meetingDTO) {
        Company company = companyRepository.findById(meetingDTO.getCompanyId())
                .orElseThrow(() -> new CompanyNotFoundException("Company not found with id: " + meetingDTO.getCompanyId()));
        
        Meeting meeting = meetingMapper.toEntity(meetingDTO);
        meeting.setCompany(company);
        
        Meeting savedMeeting = meetingRepository.save(meeting);
        MeetingDTO responseDTO = meetingMapper.toDTO(savedMeeting);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Override
    public ResponseEntity<List<MeetingDTO>> getAllMeetingsByCompany(Long companyId) {
        List<Meeting> meetings = meetingRepository.findByCompanyId(companyId);
        List<MeetingDTO> meetingDTOs = meetings.stream()
                .map(meetingMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(meetingDTOs);
    }

    @Override
    public ResponseEntity<MeetingDTO> getMeetingById(Long id) {
        Meeting meeting = meetingRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Meeting not found with id: " + id));
        return ResponseEntity.ok(meetingMapper.toDTO(meeting));
    }
}