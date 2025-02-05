package pl.denys.karol.CompanysManagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.denys.karol.CompanysManagement.dto.ContactPersonDTO;
import pl.denys.karol.CompanysManagement.model.ContactPerson;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring",imports = { Instant.class, DateTimeFormatter.class })
public interface ContactPersonMapper {

    ContactPersonMapper INSTANCE = Mappers.getMapper(ContactPersonMapper.class);

    @Mapping(source = "companyId", target = "company.id")
    ContactPerson toEntity(ContactPersonDTO contactPersonDTO);

    @Mapping(source = "company.id", target = "companyId")
    ContactPersonDTO toDTO(ContactPerson contactPerson);
}
