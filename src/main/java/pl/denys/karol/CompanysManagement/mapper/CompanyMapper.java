package pl.denys.karol.CompanysManagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.denys.karol.CompanysManagement.dto.CompanyDTO;
import pl.denys.karol.CompanysManagement.model.Company;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring",imports = { Instant.class, DateTimeFormatter.class })
public interface CompanyMapper {

    CompanyMapper INSTANCE = Mappers.getMapper( CompanyMapper.class );

    CompanyDTO toDTO(Company company);

    Company toEntity(CompanyDTO companyDto);

    // Mapowanie z Object[] do CompanyDTO - domyślna implementacja
    default CompanyDTO mapToCompanyDTO(Object[] row) {
        if (row == null || row.length < 6) {
            throw new IllegalArgumentException("Invalid data structure for mapping to CompanyDTO");
        }

        CompanyDTO dto = new CompanyDTO();
        dto.setId(((Number) row[0]).longValue()); // Rzutowanie na Long
        dto.setName((String) row[1]);
        dto.setAddress((String) row[2]);
        dto.setCity((String) row[3]);
        dto.setLatitude((Double) row[4]);
        dto.setLongitude((Double) row[5]);

        return dto;
    }

}
