package com.springjwt.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springjwt.entities.Mood;




@Repository
public interface MoodRepo extends JpaRepository<Mood, Long> {
	Long countByUser_UserId(Long userId);
	  
	
	@Query("SELECT m FROM Mood m WHERE m.user.userId = :userId AND m.date BETWEEN :startOfWeek AND :endOfWeek")
    List<Mood> findMoodsForUserThisWeek(@Param("userId") Long userId, @Param("startOfWeek") LocalDate startOfWeek, @Param("endOfWeek") LocalDate endOfWeek);
}
