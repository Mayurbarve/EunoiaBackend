package com.springjwt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springjwt.Exceptions.ResourceNotFound;
import com.springjwt.entities.Appointment;
import com.springjwt.entities.Doctor;
import com.springjwt.entities.User;
import com.springjwt.repositories.AppointmentRepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppointmentServe {
    
    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private UserServe userServe; // Inject UserServe to fetch user

    @Autowired
    private DoctorServe doctorServe;
    
    //Create Appointment with correct User & Doctor mapping
    public Appointment createAppointment(Long userId, Long docId, Appointment appointment) {
        User user = userServe.getUserById(userId);
        Doctor doctor = doctorServe.getDoctorById(docId);

        appointment.setUser(user);  // Set the User
        appointment.setDoctor(doctor); //Set the Doctor

        return appointmentRepo.save(appointment);
    }
    
    // Get Appointment by ID
    public Appointment getAppointmentById(Long aptId) {
        return appointmentRepo.findById(aptId)
                .orElseThrow(() -> new ResourceNotFound("Appointment not found with ID: " + aptId));
    }
    
    // Get All Appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepo.findAll();
    }
    
 // Update Appointment (Ensuring Correct Mapping)
    public Appointment updateAppointment(Long aptId, Long userId, Long docId, Appointment appointmentDetails) {
        // Fetch the existing appointment by its ID
        Appointment appointment = appointmentRepo.findById(aptId)
                .orElseThrow(() -> new ResourceNotFound("Appointment not found with ID: " + aptId));

        // Fetch the User and Doctor to ensure they exist
        User user = userServe.getUserById(userId);
        Doctor doctor = doctorServe.getDoctorById(docId);

        // Conditionally update fields
        if (appointmentDetails.getAptDate() != null) {
            appointment.setAptDate(appointmentDetails.getAptDate());
        }

        if (appointmentDetails.getDocDetails() != null) {
            appointment.setDocDetails(appointmentDetails.getDocDetails());
        }

        if (user != null) {
            appointment.setUser(user);
        }

        if (doctor != null) {
            appointment.setDoctor(doctor);
        }

        // Save and return the updated appointment
        return appointmentRepo.save(appointment);
    }

    
    // Delete Appointment
    public void deleteAppointment(Long aptId) {
        Appointment appointment = getAppointmentById(aptId);
        appointmentRepo.delete(appointment);
    }
    
    public Long getAppointmentCountByUserId(Long userId) {
        return appointmentRepo.countByUser_UserId(userId);  // Get count based on userId
    }
    
    public Map<String, Long> getAppointmentSummary(Long userId) {
        Long attendedCount = appointmentRepo.countByUserUserIdAndRemark(userId, true);
        Long notAttendedCount = appointmentRepo.countByUserUserIdAndRemark(userId, false);

        Map<String, Long> summary = new HashMap<>();
        summary.put("attended", attendedCount);
        summary.put("notAttended", notAttendedCount);

        return summary;
    }
}
