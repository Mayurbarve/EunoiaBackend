package com.springjwt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjwt.Exceptions.ResourceNotFound;
import com.springjwt.entities.Journal;
import com.springjwt.entities.User;
import com.springjwt.repositories.JournalRepo;
import com.springjwt.repositories.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JournalServe {
    @Autowired
    private JournalRepo journalRepo;

    @Autowired
    private UserRepository userRepo;

    // Read all journals
    public List<Journal> getAllJournals() {
        return journalRepo.findAll();
    }

    // Read journal by ID
    public Journal getJournalById(Long id) {
        return journalRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Journal not found with ID: " + id));
    }

    // Create journal
    public Journal addJournal(Long userId, Journal journal) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User not found with ID: " + userId));
        journal.setUser(user);
        return journalRepo.save(journal);
    }

    // Update journal
    public Journal updateJournal(Long id, Journal journalDetails, Long userId) {
        // Fetch the existing journal
        Journal journal = journalRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Journal not found with ID: " + id));

        // Fetch the user and validate existence
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User not found with ID: " + userId));

        // Ensure non-null fields before updating
        if (journalDetails.getTitle() != null) {
            journal.setTitle(journalDetails.getTitle());
        }
        if (journalDetails.getContent() != null) {
            journal.setContent(journalDetails.getContent());
        }
        if (journalDetails.getDate() != null) {
            journal.setDate(journalDetails.getDate());
        }
        
        journal.setUser(user); 

        return journalRepo.save(journal);
    }


    // Delete journal
    public void deleteJournal(Long id) {
        Journal journal = journalRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Journal not found with ID: " + id));
        journalRepo.delete(journal);
    }
    
    public Long getJournalCountByUserId(Long userId) {
        return journalRepo.countByUser_UserId(userId);  // Get count based on userId
    }
    
    public Map<String, Object> getWeeklyJournalCounts(Long userId) {
        LocalDate startDate = LocalDate.now().minusWeeks(4);
        List<Object[]> results = journalRepo.findWeeklyJournalCounts(userId, startDate);

        List<String> weekLabels = new ArrayList<>();
        List<Long> entryCounts = new ArrayList<>();

        for (Object[] row : results) {
            Integer week = (Integer) row[0];
            Long count = (Long) row[1];

            weekLabels.add("Week " + week);
            entryCounts.add(count);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("weekLabels", weekLabels);
        response.put("entryCounts", entryCounts);
        return response;
    }

}
