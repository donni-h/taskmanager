package de.adesso.taskmanager.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "responsibilities")
public class Responsibility extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    private String name;

    @Column(length = 45)
    private String email;

    @JsonBackReference(value = "responsibility-scheduledTask")
    @ManyToOne
    @JoinColumn(name="scheduled_task_id")
    private ScheduledTask scheduledTask;

    @JsonBackReference(value = "responsibility-task")
    @ManyToOne
    @JoinColumn(name="task_id")
    private Task task;

    @BeforeMapping
    public void setParent(@MappingTarget Task task){
        this.task = task;
    }
}
