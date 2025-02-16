package pl.denys.karol.CompanysManagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.denys.karol.CompanysManagement.dto.CompanyBasicDTO;
import pl.denys.karol.CompanysManagement.model.Company;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring",imports = { Instant.class, DateTimeFormatter.class })
public interface CompanyBasicMapper {
    CompanyBasicMapper INSTANCE = Mappers.getMapper(CompanyBasicMapper.class);

    CompanyBasicDTO toDTO(Company company);
}
