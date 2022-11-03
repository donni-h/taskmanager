package de.adesso.taskmanager.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="tasks")
@SQLDelete(sql = "UPDATE tasks SET deleted = NOW() WHERE id =?")
@Where(clause = "deleted IS null")
public class Task extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(length = 255)
    private String title;

    @Column(columnDefinition="TEXT")
    private String description;

    @Column(columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finished;

    @Column(columnDefinition = "DATETIME", name = "due_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;

    @JsonBackReference(value = "task-scheduledTask")
    @ManyToOne
    @JoinColumn(name = "scheduled_task_id")
    private ScheduledTask scheduledTask;

    @JsonManagedReference(value = "responsibility-task")
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("responsibilities")
    private Set<Responsibility> responsibilities = new HashSet<>();

    @Column
    private Date deleted;

    @AfterMapping
    public void establishRelation(Set<Responsibility> childEntities) {
        childEntities.forEach(childEntity -> {
            childEntity.setTask(this);
        });
        // you could do stuff with the EntityManager here
    }
}
