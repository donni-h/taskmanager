package de.adesso.taskmanager.entities.dtos;

import de.adesso.taskmanager.entities.ScheduledTask;
import de.adesso.taskmanager.entities.Task;
import lombok.Data;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;

import java.io.Serializable;

/**
 * A DTO for the {@link de.adesso.taskmanager.entities.Responsibility} entity
 */
@Data
public class ResponsibilityDto implements Serializable {

    private final Long id;
    private final String name;
    private final String email;
}