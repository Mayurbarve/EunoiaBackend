package com.springjwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springjwt.entities.Appointment;




@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
	Long countByUser_UserId(Long userId);
	
	Long countByUserUserIdAndRemark(Long userId, boolean remark);
}
