package HealthProject.Eunoia.Controller;

import HealthProject.Eunoia.Service.AppointmentServe;
import HealthProject.Eunoia.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/users/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentServe appointmentServe;
 
    // ✅ Create a new appointment with User ID and Doctor ID  
    @PostMapping("/create/{userId}/{docId}")
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
    @GetMapping("/all")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentServe.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    //Update an existing appointment (User ID and Doctor ID required) 
    @PutMapping("/update/{id}/{userId}/{docId}")
    public ResponseEntity<Appointment> updateAppointment(
            @PathVariable("id") Long aptId,
            @PathVariable("userId") Long userId,
            @PathVariable("docId") Long docId,
            @RequestBody Appointment updatedAppointment) {

        Appointment updated = appointmentServe.updateAppointment(aptId, userId, docId, updatedAppointment);
        return ResponseEntity.ok(updated);
    }

    // ✅ Delete an appointment
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable("id") Long  aptId) {
        appointmentServe.deleteAppointment(aptId);
        return ResponseEntity.noContent().build();
    }
}
