package de.adesso.taskmanager.repositories;

import de.adesso.taskmanager.entities.Responsibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsibilityRepository extends JpaRepository<Responsibility, Long> {

}
