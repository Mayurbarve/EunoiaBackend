package HealthProject.Eunoia.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import HealthProject.Eunoia.model.Doctor;


@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {

}
