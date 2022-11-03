package de.adesso.taskmanager.entities.dtos;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.adesso.taskmanager.entities.Responsibility;
import de.adesso.taskmanager.entities.Task;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ScheduledTaskDto {
    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.PRIVATE)
    private Long id;

    private String title;

    private String description;

    private String intervall;

    private Date start;

    private Date end;

    private Integer repetitions;

    private Integer repetitionsLeft;

    private Integer length;

    private Integer weekday;

    private String specialLength;

    private Integer month;

    private Integer day;

    private Date nextSchedule;

    @JsonProperty("responsibilities")
    private Set<Responsibility> responsibilities = new HashSet<>();
}
