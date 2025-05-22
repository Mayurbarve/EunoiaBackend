package com.springjwt.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springjwt.entities.Activity;


@Repository
public interface ActivityRepo extends JpaRepository<Activity, Long> {
	Long countByUser_UserId(Long userId);
	
	@Query("SELECT a FROM Activity a WHERE a.user.id = :userId AND a.date BETWEEN :startDate AND :endDate")
    List<Activity> findActivitiesForLast7Days(Long userId, LocalDate startDate, LocalDate endDate);
}
