package pl.denys.karol.CompanysManagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.denys.karol.CompanysManagement.dto.MeetingDTO;
import pl.denys.karol.CompanysManagement.model.Meeting;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring",imports = { Instant.class, DateTimeFormatter.class })
public interface MeetingMapper {

    MeetingMapper INSTANCE = Mappers.getMapper( MeetingMapper.class );
    MeetingDTO toDTO(Meeting meeting);

    Meeting toEntity(MeetingDTO meetingDTO);
}
