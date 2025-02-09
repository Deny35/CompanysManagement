package pl.denys.karol.CompanysManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.denys.karol.CompanysManagement.dto.MeetingDTO;
import pl.denys.karol.CompanysManagement.service.MeetingService;
import java.util.List;

@RestController
@RequestMapping("/api/meetings")
@CrossOrigin(origins = "http://localhost:5173") 
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @PostMapping
    public ResponseEntity<MeetingDTO> addMeeting(@RequestBody MeetingDTO meetingDTO) {
        return meetingService.addMeeting(meetingDTO);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<MeetingDTO>> getMeetingsByCompany(@PathVariable Long companyId) {
        return meetingService.getAllMeetingsByCompany(companyId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeetingDTO> getMeetingById(@PathVariable Long id) {
        return meetingService.getMeetingById(id);
    }
}
