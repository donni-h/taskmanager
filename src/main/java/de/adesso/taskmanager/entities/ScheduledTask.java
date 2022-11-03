package de.adesso.taskmanager.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "scheduled_tasks")
@SQLDelete(sql = "UPDATE scheduled_tasks SET deleted = NOW() WHERE id =?")
@Where(clause = "deleted IS null")
public class ScheduledTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    private String title;

    @Column()
    private String description;

    @Column(length = 45)
    private String unit;

    @Column(columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;

    @Column(columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;

    @Column
    private Integer repetitions;

    @Column(name = "repetitions_left")
    private Integer repetitionsLeft;

    @Column
    private Integer length;

    @Column
    private Integer weekday;

    @Column(length = 10, name="special_length")
    private String specialLength;

    @Column
    private Integer month;

    @Column
    private Integer day;

    @Column(columnDefinition = "DATETIME", name ="next_schedule")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nextSchedule;

    @JsonManagedReference(value = "task-scheduledTask")
    @OneToMany(mappedBy = "scheduledTask")
    private Set<Task> tasks = new HashSet<>();

    @JsonManagedReference(value = "responsibility-scheduledTask")
    @OneToMany(mappedBy = "scheduledTask", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("responsibilities")
    private Set<Responsibility> responsibilities = new HashSet<>();

    @Column
    private Date deleted;
}
