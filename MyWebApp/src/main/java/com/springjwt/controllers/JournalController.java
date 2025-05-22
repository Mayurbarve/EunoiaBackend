package com.springjwt.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springjwt.entities.Journal;
import com.springjwt.services.JournalServe;



@RestController
@RequestMapping("/users/journals")
public class JournalController {

    @Autowired
    private JournalServe journalServe;

    // Add a new journal
    @PostMapping("/{id}")
    public ResponseEntity<Journal> addJournal(@PathVariable("id") Long userId, @RequestBody Journal journal) {
        Journal createdJournal = journalServe.addJournal(userId, journal);
        return ResponseEntity.ok(createdJournal);
    }

    // Get all journals
    @GetMapping
    public ResponseEntity<List<Journal>> getAllJournals() {
        List<Journal> journals = journalServe.getAllJournals();
        return ResponseEntity.ok(journals);
    }

    // Get journal by ID
    @GetMapping("/{id}")
    public ResponseEntity<Journal> getJournalById(@PathVariable("id") Long id) {
        Journal journal = journalServe.getJournalById(id);
        return ResponseEntity.ok(journal);
    }

    // Update a journal
    @PutMapping("/{id}/{userId}")
    public ResponseEntity<Journal> updateJournal(
            @PathVariable("id") Long id,
            @PathVariable("userId") Long userId,
            @RequestBody Journal journalDetails) {

        Journal updatedJournal = journalServe.updateJournal(id, journalDetails, userId);
        return ResponseEntity.ok(updatedJournal);
    }

    // Delete a journal
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJournal(@PathVariable("id") Long id) {
        journalServe.deleteJournal(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("{userId}/count")
    public ResponseEntity<Long> getJournalCount(@PathVariable Long userId) {
        Long count = journalServe.getJournalCountByUserId(userId);
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/weekly-count/{userId}")
    public ResponseEntity<Map<String, Object>> getWeeklyCounts(@PathVariable Long userId) {
        Map<String, Object> data = journalServe.getWeeklyJournalCounts(userId);
        return ResponseEntity.ok(data);
    }
    
}
