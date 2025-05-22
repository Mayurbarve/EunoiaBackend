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

import com.springjwt.entities.Appointment;
import com.springjwt.services.AppointmentServe;


@RestController
@RequestMapping("/users/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentServe appointmentServe;
 
    // ✅ Create a new appointment with User ID and Doctor ID  
    @PostMapping("{userId}/{docId}")
    public ResponseEntity<Appointment> createAppointment(
            @PathVariable Long userId,
            @PathVariable Long docId,
            @RequestBody Appointment appointment) {

        Appointment newAppointment = appointmentServe.createAppointment(userId, docId, appointment);
        return ResponseEntity.ok(newAppointment);
    }

    // Get appointment by ID 
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable("id") Long aptId) {
        Appointment appointment = appointmentServe.getAppointmentById(aptId);
        return ResponseEntity.ok(appointment);
    }

    // Get all appointments  
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentServe.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    //Update an existing appointment (User ID and Doctor ID required) 
    @PutMapping("/{id}/{userId}/{docId}")
    public ResponseEntity<Appointment> updateAppointment(
            @PathVariable("id") Long aptId,
            @PathVariable("userId") Long userId,
            @PathVariable("docId") Long docId,
            @RequestBody Appointment updatedAppointment) {

        Appointment updated = appointmentServe.updateAppointment(aptId, userId, docId, updatedAppointment);
        return ResponseEntity.ok(updated);
    }

    // ✅ Delete an appointment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable("id") Long  aptId) {
        appointmentServe.deleteAppointment(aptId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{userId}/count")
    public ResponseEntity<Long> getAppointmentCount(@PathVariable Long userId) {
        Long count = appointmentServe.getAppointmentCountByUserId(userId);
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/summary/{userId}")
    public Map<String, Long> getAppointmentSummary(@PathVariable Long userId) {
        return appointmentServe.getAppointmentSummary(userId);
    }
}
