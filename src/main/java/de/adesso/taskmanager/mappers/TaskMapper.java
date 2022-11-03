package de.adesso.taskmanager.mappers;

import de.adesso.taskmanager.entities.Responsibility;
import de.adesso.taskmanager.entities.Task;
import de.adesso.taskmanager.entities.dtos.ResponsibilityDto;
import de.adesso.taskmanager.entities.dtos.TaskDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(uses = ResponsibilityMapper.class)
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
    TaskDto toDto(Task task);
    void updateTaskFromDto(TaskDto dto, @MappingTarget Task task);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchTaskFromDto(TaskDto dto, @MappingTarget Task task);




}
