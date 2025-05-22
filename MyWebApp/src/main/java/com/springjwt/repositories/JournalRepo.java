package com.springjwt.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springjwt.entities.Journal;



@Repository
public interface JournalRepo extends JpaRepository<Journal, Long> {
	Long countByUser_UserId(Long userId);
	
	@Query("SELECT FUNCTION('WEEK', j.date) AS week, COUNT(j) AS count " +
		       "FROM Journal j WHERE j.user.id = :userId AND j.date >= :startDate " +
		       "GROUP BY FUNCTION('WEEK', j.date) ORDER BY week")
		List<Object[]> findWeeklyJournalCounts(@Param("userId") Long userId,
		                                       @Param("startDate") LocalDate startDate);
}
