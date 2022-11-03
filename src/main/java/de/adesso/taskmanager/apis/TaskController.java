package de.adesso.taskmanager.apis;

import de.adesso.taskmanager.entities.Responsibility;
import de.adesso.taskmanager.entities.ScheduledTask;
import de.adesso.taskmanager.entities.Task;
import de.adesso.taskmanager.entities.dtos.TaskDto;
import de.adesso.taskmanager.mappers.TaskMapper;
import de.adesso.taskmanager.repositories.ResponsibilityRepository;
import de.adesso.taskmanager.repositories.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import java.util.Objects;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tasks")
@RestController
public class TaskController {

    private TaskRepository repository;
    private ResponsibilityRepository responsibilityRepository;

    public TaskController(TaskRepository taskRepository, EntityManager entityManager, ResponsibilityRepository responsibilityRepository){
        this.repository = taskRepository;
        this.responsibilityRepository = responsibilityRepository;
    }
    @GetMapping
    public Iterable<Task> findAll(){
        return repository.findAll();
    };

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void insertTask(@RequestBody Task task){
        repository.save(task);
    }

    @GetMapping("/{id}")
    public Task findTask(@PathVariable Long id){
        return repository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
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

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void putTask(@RequestBody TaskDto taskDto, @PathVariable Long id){
        if (repository.findById(id).isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "task not found"
            );
        }

        Task task = repository.findById(id).get();
        TaskMapper.INSTANCE.updateTaskFromDto(taskDto, task);

        task.getResponsibilities().forEach(responsibility -> responsibility.setTask(task));
        repository.save(task);

    }

    @PatchMapping("/{id}")
    public void patchTask(@RequestBody TaskDto taskDto, @PathVariable Long id){
        if (repository.findById(id).isEmpty()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "task not found"
            );
        }

        Task task = repository.findById(id).get();

        TaskMapper.INSTANCE.patchTaskFromDto(taskDto, task);

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
}
