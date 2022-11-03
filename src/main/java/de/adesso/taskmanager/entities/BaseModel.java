package de.adesso.taskmanager.entities;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter @Setter @NoArgsConstructor @MappedSuperclass
public class BaseModel {

    @Transient
    private Date createdAt;
    @Transient
    private Date updatedAt;
}
