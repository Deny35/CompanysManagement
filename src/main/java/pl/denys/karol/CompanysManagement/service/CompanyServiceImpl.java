package pl.denys.karol.CompanysManagement.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import pl.denys.karol.CompanysManagement.dto.CompanyDTO;
import pl.denys.karol.CompanysManagement.mapper.CompanyMapper;
import pl.denys.karol.CompanysManagement.model.Company;
import pl.denys.karol.CompanysManagement.repository.CompanyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private  CompanyRepository companyRepository;
    @Autowired
    private GeocodingService geocodingService;
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        List<CompanyDTO> companies = companyRepository.findAll()
                .stream()
                .map(companyMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(companies);
    }

    @Override
    public ResponseEntity<CompanyDTO> createCompany( CompanyDTO companyDTO) {

        Company company = companyMapper.toEntity(companyDTO);
        Company savedCompany = companyRepository.save(company);
        double[] coordinates = geocodingService.getCoordinates("Jacka Kaczmarskiego 35", "Wołów", "56-100");
        System.out.println("Latitude: " + coordinates[0] + ", Longitude: " + coordinates[1]);
        companyRepository.updateLocation(company.getId(), coordinates[0], coordinates[1]);
//        companyRepository.updateLocation(company.getId(), companyDTO.getLatitude(),companyDTO.getLongitude());
        return ResponseEntity.status(HttpStatus.CREATED).body(companyMapper.toDTO(savedCompany));
    }
}



