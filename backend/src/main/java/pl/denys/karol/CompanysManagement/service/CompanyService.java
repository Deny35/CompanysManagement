package pl.denys.karol.CompanysManagement.service;

import org.springframework.http.ResponseEntity;

import pl.denys.karol.CompanysManagement.dto.CompanyBasicDTO;
import pl.denys.karol.CompanysManagement.dto.CompanyBasicToAddDTO;
import pl.denys.karol.CompanysManagement.dto.CompanyDTO;

import java.time.LocalDate;
import java.util.List;

public interface CompanyService {
    ResponseEntity<List<CompanyBasicDTO>> getAllCompanies();
    ResponseEntity<List<CompanyBasicDTO>> getAllCompaniesAfterDate(LocalDate startDate);
    ResponseEntity<CompanyDTO> getCompanyWithDetails(Long id);
    ResponseEntity<CompanyBasicToAddDTO> createCompany(CompanyBasicToAddDTO companyBasicToAddDTO);
    ResponseEntity<CompanyDTO> updateCompany(Long id, CompanyDTO companyDTO);
    ResponseEntity<Void> deleteCompany(Long id);
}
