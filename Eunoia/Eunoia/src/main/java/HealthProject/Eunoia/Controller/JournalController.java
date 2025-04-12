package HealthProject.Eunoia.Controller;

import HealthProject.Eunoia.Service.JournalServe;
import HealthProject.Eunoia.model.Journal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/users/journals")
public class JournalController {

    @Autowired
    private JournalServe journalServe;

    // Add a new journal
    @PostMapping("/create/{id}")
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
    @PutMapping("/update/{id}/{userId}")
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
}
