package tn.esprit.devops_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.devops_project.entities.ActivitySector;

@Repository
public interface ActivitySectorRepository extends JpaRepository<ActivitySector, Long> {
}
