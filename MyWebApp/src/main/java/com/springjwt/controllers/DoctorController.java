package com.springjwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springjwt.entities.Doctor;
import com.springjwt.services.DoctorServe;



@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorServe doctorServe;

    // Create a new doctor 
    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        Doctor createdDoctor = doctorServe.addDoctor(doctor);
        return ResponseEntity.ok(createdDoctor);
    }

    // Get all doctors 	
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorServe.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    // Get doctor by ID		
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable("id") Long docId) {
        Doctor doctor = doctorServe.getDoctorById(docId);
        return ResponseEntity.ok(doctor);
    }

    // Update doctor details
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable("id") Long docId, @RequestBody Doctor updatedDoctor) {
        Doctor doctor = doctorServe.updateDoctor(docId, updatedDoctor);
        return ResponseEntity.ok(doctor);
    }

    // Delete doctor by ID 
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDoctor(@PathVariable("id") Long docId) {
        doctorServe.deleteDoctor(docId);
        return ResponseEntity.ok("Doctor deleted successfully");
    }

}
