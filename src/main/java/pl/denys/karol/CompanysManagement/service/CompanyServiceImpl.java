package pl.denys.karol.CompanysManagement.service;


import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import pl.denys.karol.CompanysManagement.dto.CompanyDTO;
import pl.denys.karol.CompanysManagement.mapper.CompanyMapper;
import pl.denys.karol.CompanysManagement.model.Company;
import pl.denys.karol.CompanysManagement.repository.CompanyRepository;

import org.locationtech.jts.geom.GeometryFactory;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private  CompanyRepository companyRepository;

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
        System.out.println("halo");

//        Point point = createPointFromCoordinates(companyDTO.getLongitude(), companyDTO.getLatitude());
//        Point point = new Point(companyDTO.getLongitude(), companyDTO.getLatitude());
//        company.setLocation(point);
//        System.out.println( company.getPoint());


        Company savedCompany = companyRepository.save(company);
//        System.out.println(companyDTO.getAddress());
//
//        System.out.println(company.getAddress());
        companyRepository.updateLocation(company.getId(), companyDTO.getLatitude(),companyDTO.getLongitude());
        return ResponseEntity.status(HttpStatus.CREATED).body(companyMapper.toDTO(savedCompany));
    }



    private org.locationtech.jts.geom.Point createPointFromCoordinates(Double longitude, Double latitude) {
        // Tworzymy punkt geograficzny przy użyciu GeometryFactory
        GeometryFactory geometryFactory = new GeometryFactory();
        return geometryFactory.createPoint(new org.locationtech.jts.geom.Coordinate(longitude, latitude));
    }
}



