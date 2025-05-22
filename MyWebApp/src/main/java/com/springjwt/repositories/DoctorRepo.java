package com.springjwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springjwt.entities.Doctor;




@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {

}
