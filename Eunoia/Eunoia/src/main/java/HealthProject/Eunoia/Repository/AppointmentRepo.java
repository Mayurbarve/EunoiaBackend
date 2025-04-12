package HealthProject.Eunoia.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import HealthProject.Eunoia.model.Appointment;


@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

}
