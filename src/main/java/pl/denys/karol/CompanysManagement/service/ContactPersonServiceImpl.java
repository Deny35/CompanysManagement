package pl.denys.karol.CompanysManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.denys.karol.CompanysManagement.dto.ContactPersonDTO;
import pl.denys.karol.CompanysManagement.exception.CompanyNotFoundException;
import pl.denys.karol.CompanysManagement.mapper.ContactPersonMapper;
import pl.denys.karol.CompanysManagement.model.Company;
import pl.denys.karol.CompanysManagement.model.ContactPerson;
import pl.denys.karol.CompanysManagement.repository.CompanyRepository;
import pl.denys.karol.CompanysManagement.repository.ContactPersonRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactPersonServiceImpl implements ContactPersonService {
    @Autowired
    private ContactPersonRepository contactPersonRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ContactPersonMapper contactPersonMapper;

    @Override
    public ResponseEntity<ContactPersonDTO> addContactPerson(ContactPersonDTO contactPersonDTO) {
        Company company = companyRepository.findById(contactPersonDTO.getCompanyId())
                .orElseThrow(() -> new CompanyNotFoundException("Company not found with id: " + contactPersonDTO.getCompanyId()));

        ContactPerson contactPerson = contactPersonMapper.toEntity(contactPersonDTO);
        contactPerson.setCompany(company);

        ContactPerson savedContact = contactPersonRepository.save(contactPerson);
        ContactPersonDTO responseDTO = contactPersonMapper.toDTO(savedContact);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Override
    public ResponseEntity<List<ContactPersonDTO>> getAllContactsByCompany(Long companyId) {
        List<ContactPerson> contacts = contactPersonRepository.findByCompanyId(companyId);
        List<ContactPersonDTO> contactDTOs = contacts.stream()
                .map(contactPersonMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(contactDTOs);
    }

    @Override
    public ResponseEntity<ContactPersonDTO> getContactById(Long id) {
        ContactPerson contactPerson = contactPersonRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Contact not found with id: " + id));
        return ResponseEntity.ok(contactPersonMapper.toDTO(contactPerson));
    }
}

