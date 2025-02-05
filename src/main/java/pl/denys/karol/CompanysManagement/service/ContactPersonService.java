package pl.denys.karol.CompanysManagement.service;

import org.springframework.http.ResponseEntity;
import pl.denys.karol.CompanysManagement.dto.ContactPersonDTO;
import java.util.List;

public interface ContactPersonService {
    ResponseEntity<ContactPersonDTO> addContactPerson(ContactPersonDTO contactPersonDTO);
    ResponseEntity<List<ContactPersonDTO>> getAllContactsByCompany(Long companyId);
    ResponseEntity<ContactPersonDTO> getContactById(Long id);
}
