package HealthProject.Eunoia.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import HealthProject.Eunoia.Exceptions.ResourceNotFound;
import HealthProject.Eunoia.Repository.JournalRepo;
import HealthProject.Eunoia.Repository.UserRepository;
import HealthProject.Eunoia.model.Journal;
import HealthProject.Eunoia.model.User;
import java.util.List;

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
}
