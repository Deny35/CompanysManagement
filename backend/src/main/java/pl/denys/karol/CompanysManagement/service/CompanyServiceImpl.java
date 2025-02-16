package pl.denys.karol.CompanysManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import pl.denys.karol.CompanysManagement.dto.CompanyBasicDTO;
import pl.denys.karol.CompanysManagement.dto.CompanyBasicToAddDTO;
import pl.denys.karol.CompanysManagement.dto.CompanyDTO;
import pl.denys.karol.CompanysManagement.exception.CompanyNotFoundException;
import pl.denys.karol.CompanysManagement.mapper.CompanyBasicToAddMapper;
import pl.denys.karol.CompanysManagement.mapper.CompanyMapper;
import pl.denys.karol.CompanysManagement.model.Company;
import pl.denys.karol.CompanysManagement.repository.CompanyRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private GeocodingService geocodingService;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private CompanyBasicToAddMapper companyBasicToAddMapper;

    @Override
    public ResponseEntity<List<CompanyBasicDTO>> getAllCompanies() {
        try {
            List<Object[]> results = companyRepository.findAllWithLastMeeting();
            
            List<CompanyBasicDTO> companies = results.stream().map(row -> {
                System.out.println("Raw date value: " + row[5]); // Debug - sprawdzanie wartości przed konwersją

                return new CompanyBasicDTO(
                    ((Number) row[0]).longValue(),
                    (String) row[1],
                    (String) row[2],
                    ((Number) row[3]).doubleValue(),
                    ((Number) row[4]).doubleValue(), 
                    row[5] != null ? ((java.sql.Date) row[5]).toLocalDate() : null
                );
            }).toList();
            
            return ResponseEntity.ok(companies);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching all companiesss", e);
        }
    }

    @Override
    public ResponseEntity<List<CompanyBasicDTO>> getAllCompaniesAfterDate(LocalDate startDate) {
        try {
            List<Object[]> results = companyRepository.findCompaniesWithLastMeetingAfter(startDate);

            List<CompanyBasicDTO> companies = results.stream().map(row -> new CompanyBasicDTO(
                    ((Number) row[0]).longValue(),
                    (String) row[1],
                    (String) row[2],
                    ((Number) row[3]).doubleValue(),
                    ((Number) row[4]).doubleValue(),
                    row[5] != null ? ((java.sql.Date) row[5]).toLocalDate() : null
            )).toList();

            return ResponseEntity.ok(companies);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching companies after date", e);
        }
    }

    @Override
    public ResponseEntity<CompanyDTO> getCompanyWithDetails(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));

        CompanyDTO companyDTO = companyMapper.toDTO(company);
        return ResponseEntity.status(HttpStatus.OK).body(companyDTO);
    }

    @Override
    @Transactional
    public ResponseEntity<CompanyBasicToAddDTO> createCompany(CompanyBasicToAddDTO companyBasicToAddDTO) {
        try {
            Company company = companyBasicToAddMapper.toEntity(companyBasicToAddDTO);
            Company savedCompany = companyRepository.save(company);
            double[] coordinates = geocodingService.getCoordinates(companyBasicToAddDTO.getAddress(), companyBasicToAddDTO.getCity(), companyBasicToAddDTO.getZipCode());
            companyRepository.updateLocation(company.getId(), coordinates[0], coordinates[1]);
            return ResponseEntity.status(HttpStatus.CREATED).body(companyBasicToAddMapper.toDTO(savedCompany));
        } catch (Exception e) {
            throw new RuntimeException("Error creating company", e);
        }
    }

    @Override
    public ResponseEntity<CompanyDTO> updateCompany(Long id, CompanyDTO companyDTO) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found with id: " + id));
        try {
            existingCompany.setName(companyDTO.getName());
            existingCompany.setAddress(companyDTO.getAddress());
            existingCompany.setCity(companyDTO.getCity());
            existingCompany.setZipCode(companyDTO.getZipCode());
            existingCompany.setLatitude(companyDTO.getLatitude());
            existingCompany.setLongitude(companyDTO.getLongitude());

            Company updatedCompany = companyRepository.save(existingCompany);
            companyRepository.updateLocation(id, updatedCompany.getLatitude(), updatedCompany.getLongitude());

            return ResponseEntity.ok(companyMapper.toDTO(updatedCompany));
        } catch (Exception e) {
            throw new RuntimeException("Error updating company with id: " + id, e);
        }
    }

    @Override
    public ResponseEntity<Void> deleteCompany(Long id) {
        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found with id: " + id));
        try {
            companyRepository.delete(existingCompany);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new RuntimeException("Error deleting company with id: " + id, e);
        }
    }
}

