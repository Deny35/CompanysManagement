package pl.denys.karol.CompanysManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.denys.karol.CompanysManagement.dto.ContactPersonDTO;
import pl.denys.karol.CompanysManagement.service.ContactPersonService;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactPersonController {

    @Autowired
    private ContactPersonService contactPersonService;

    @PostMapping
    public ResponseEntity<ContactPersonDTO> addContactPerson(@RequestBody ContactPersonDTO contactPersonDTO) {
        return contactPersonService.addContactPerson(contactPersonDTO);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<ContactPersonDTO>> getContactsByCompany(@PathVariable Long companyId) {
        return contactPersonService.getAllContactsByCompany(companyId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactPersonDTO> getContactById(@PathVariable Long id) {
        return contactPersonService.getContactById(id);
    }
}