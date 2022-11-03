package de.adesso.taskmanager.apis;

import de.adesso.taskmanager.entities.Responsibility;
import de.adesso.taskmanager.entities.ScheduledTask;
import de.adesso.taskmanager.entities.Task;
import de.adesso.taskmanager.entities.dtos.ScheduledTaskDto;
import de.adesso.taskmanager.entities.dtos.TaskDto;
import de.adesso.taskmanager.mappers.ScheduledTaskMapper;
import de.adesso.taskmanager.mappers.TaskMapper;
import de.adesso.taskmanager.repositories.ResponsibilityRepository;
import de.adesso.taskmanager.repositories.ScheduledTaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import java.util.Objects;
import java.util.Set;
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/scheduled-tasks")
@RestController
public class ScheduledTaskController {

    private ScheduledTaskRepository repository;
    private EntityManager entityManager;
    private ResponsibilityRepository responsibilityRepository;

    public ScheduledTaskController(ScheduledTaskRepository scheduledTaskRepository, EntityManager entityManager, ResponsibilityRepository responsibilityRepository) {
        this.repository = scheduledTaskRepository;
        this.entityManager = entityManager;
        this.responsibilityRepository = responsibilityRepository;
    }
    @GetMapping
    public Iterable<ScheduledTask> findAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ScheduledTask getSTask(@PathVariable Long id){
        return repository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    @PostMapping
    public @ResponseBody String insertScheduledTask(@RequestBody ScheduledTask sTask){
        repository.save(sTask);
        return HttpStatus.OK.toString();
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        if (repository.findById(id).isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "task not found"
            );
        }

        repository.deleteById(id);
    }
    @PatchMapping("/{id}")
    public void patchTask(@RequestBody ScheduledTaskDto taskDto, @PathVariable Long id){
        if (repository.findById(id).isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "task not found"
            );
        }

        ScheduledTask task = repository.findById(id).get();

        ScheduledTaskMapper.INSTANCE.patchSTaskFromDto(taskDto, task);
        repository.save(task);
    }

    @PutMapping("/{id}")
    public void putTask(@RequestBody ScheduledTaskDto taskDto, @PathVariable Long id){
        if (repository.findById(id).isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "task not found"
            );
        }

        ScheduledTask task = repository.findById(id).get();

        ScheduledTaskMapper.INSTANCE.updateSTaskFromDto(taskDto, task);

        repository.save(task);
    }
    @GetMapping("/{id}/responsibility")
    public Set<Responsibility> getResponsibilities(@PathVariable Long id){
        if (repository.findById(id).isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "task not found"
            );
        }

        return repository.findById(id).get().getResponsibilities();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}/responsibility/{id2}")
    public void deleteResponsibility(@PathVariable("id") Long id, @PathVariable("id2") Long resId){

        if (repository.findById(id).isEmpty())
            throw new TaskNotFoundException(id);
        if(responsibilityRepository.findById(resId).isEmpty())
            throw new ResponsibilityNotFoundException(resId);

        ScheduledTask check = responsibilityRepository.findById(resId).get().getScheduledTask();
        if (!Objects.equals(check.getId(), id))
            throw new ResponsibilityNotFoundException(resId);

        responsibilityRepository.deleteById(resId);

    }
}
