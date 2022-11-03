package de.adesso.taskmanager.entities.dtos;

import de.adesso.taskmanager.entities.Responsibility;
import de.adesso.taskmanager.entities.ScheduledTask;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class TaskDto {
    @Setter(AccessLevel.PRIVATE)
    private Long id;

    private String title;

    private String description;

    private Date finished;

    private Date dueDate;

    private ScheduledTask scheduledTask;

    private Set<ResponsibilityDto> responsibilities;
}
