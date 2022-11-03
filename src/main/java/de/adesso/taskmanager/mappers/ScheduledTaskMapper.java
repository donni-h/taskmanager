package de.adesso.taskmanager.mappers;

import de.adesso.taskmanager.entities.ScheduledTask;
import de.adesso.taskmanager.entities.Task;
import de.adesso.taskmanager.entities.dtos.ScheduledTaskDto;
import de.adesso.taskmanager.entities.dtos.TaskDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ScheduledTaskMapper {
    ScheduledTaskMapper INSTANCE = Mappers.getMapper(ScheduledTaskMapper.class);
    ScheduledTask fromDto(ScheduledTaskDto dto);

    void updateSTaskFromDto(ScheduledTaskDto dto, @MappingTarget ScheduledTask task);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchSTaskFromDto(ScheduledTaskDto dto, @MappingTarget ScheduledTask task);
}
