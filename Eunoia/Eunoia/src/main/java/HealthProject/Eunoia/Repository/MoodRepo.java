package HealthProject.Eunoia.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import HealthProject.Eunoia.model.Mood;



@Repository
public interface MoodRepo extends JpaRepository<Mood, Long> {

}
