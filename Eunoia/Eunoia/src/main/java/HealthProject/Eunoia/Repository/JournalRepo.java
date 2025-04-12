package HealthProject.Eunoia.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import HealthProject.Eunoia.model.Journal;

@Repository
public interface JournalRepo extends JpaRepository<Journal, Long> {

}
