package pl.denys.karol.CompanysManagement.service;

import org.springframework.http.ResponseEntity;
import pl.denys.karol.CompanysManagement.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {
    ResponseEntity<List<CompanyDTO>> getAllCompanies();

    ResponseEntity<CompanyDTO> createCompany(CompanyDTO companyDTO);

//    ResponseEntity<List<CompanyDTO>> getAllCompaniesWithoutLocation();

    ResponseEntity<CompanyDTO> updateCompany(Long id, CompanyDTO companyDTO);
    ResponseEntity<Void> deleteCompany(Long id);
}
