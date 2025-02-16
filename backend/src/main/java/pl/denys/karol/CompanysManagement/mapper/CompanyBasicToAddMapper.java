package pl.denys.karol.CompanysManagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.denys.karol.CompanysManagement.dto.CompanyBasicToAddDTO;
import pl.denys.karol.CompanysManagement.model.Company;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring",imports = { Instant.class, DateTimeFormatter.class })
public interface CompanyBasicToAddMapper {

    CompanyBasicToAddMapper INSTANCE = Mappers.getMapper( CompanyBasicToAddMapper.class);

   Company toEntity(CompanyBasicToAddDTO companyBasicToAddDTO);
   
   CompanyBasicToAddDTO toDTO(Company company);
}