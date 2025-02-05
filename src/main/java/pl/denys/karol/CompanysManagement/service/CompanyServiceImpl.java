package pl.denys.karol.CompanysManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.denys.karol.CompanysManagement.dto.CompanyDTO;
import pl.denys.karol.CompanysManagement.exception.CompanyNotFoundException;
import pl.denys.karol.CompanysManagement.mapper.CompanyMapper;
import pl.denys.karol.CompanysManagement.model.Company;
import pl.denys.karol.CompanysManagement.repository.CompanyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private GeocodingService geocodingService;
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        try {
            List<CompanyDTO> companies = companyRepository.findAll()
                    .stream()
                    .map(companyMapper::toDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(companies);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching all companies", e);
        }
    }

    @Override
    public ResponseEntity<CompanyDTO> createCompany(CompanyDTO companyDTO) {
        try {
            Company company = companyMapper.toEntity(companyDTO);
            Company savedCompany = companyRepository.save(company);
            double[] coordinates = geocodingService.getCoordinates(companyDTO.getAddress(), companyDTO.getCity(), companyDTO.getZipCode());
            companyRepository.updateLocation(company.getId(), coordinates[0], coordinates[1]);
            return ResponseEntity.status(HttpStatus.CREATED).body(companyMapper.toDTO(savedCompany));
        } catch (Exception e) {
            throw new RuntimeException("Error creating company", e);
        }
    }

//    @Override
//    public ResponseEntity<List<CompanyDTO>> getAllCompaniesWithoutLocation() {
//        try {
//            List<Object[]> results = companyRepository.findAllWithoutLocation();
//            List<CompanyDTO> companies = results.stream()
//                    .map(companyMapper::mapToCompanyDTO)
//                    .toList();
//            return ResponseEntity.ok(companies);
//        } catch (Exception e) {
//            throw new RuntimeException("Error fetching companies without location", e);
//        }
//    }

    @Override
    @Transactional
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
    @Transactional
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

