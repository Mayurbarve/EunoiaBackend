package HealthProject.Eunoia.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import HealthProject.Eunoia.model.Activity;


@Repository
public interface ActivityRepo extends JpaRepository<Activity, Long> {

}
