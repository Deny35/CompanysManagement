package pl.denys.karol.CompanysManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.denys.karol.CompanysManagement.dto.CompanyBasicDTO;
import pl.denys.karol.CompanysManagement.dto.CompanyBasicToAddDTO;
import pl.denys.karol.CompanysManagement.dto.CompanyDTO;
import pl.denys.karol.CompanysManagement.service.CompanyService;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/api/companies")
@CrossOrigin(origins = "http://localhost:5173") 
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyBasicDTO>> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<CompanyBasicDTO>> getCompaniesAfterDate(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate) {
        return companyService.getAllCompaniesAfterDate(startDate);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<CompanyDTO> getCompanyWithDetails(@PathVariable Long id) {
        return companyService.getCompanyWithDetails(id);
    }

    @PostMapping
    public ResponseEntity<CompanyBasicToAddDTO> createCompany(@RequestBody CompanyBasicToAddDTO companyBasicToAddDTO) {
        return companyService.createCompany(companyBasicToAddDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> updateCompany(@PathVariable Long id, @RequestBody CompanyDTO companyDTO) {
        return companyService.updateCompany(id, companyDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        return companyService.deleteCompany(id);
    }
}
