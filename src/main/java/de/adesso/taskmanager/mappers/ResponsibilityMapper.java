package de.adesso.taskmanager.mappers;

import de.adesso.taskmanager.entities.Responsibility;
import de.adesso.taskmanager.entities.ScheduledTask;
import de.adesso.taskmanager.entities.dtos.ResponsibilityDto;
import de.adesso.taskmanager.entities.dtos.ScheduledTaskDto;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface ResponsibilityMapper {

    ResponsibilityMapper INSTANCE = Mappers.getMapper(ResponsibilityMapper.class);

    ResponsibilityDto map(Responsibility responsibility);
    Responsibility map(ResponsibilityDto dto);

    Set<ResponsibilityDto> map(Set<Responsibility> responsibilities);


}
