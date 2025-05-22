package com.springjwt.entities;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id")
    private Long docId;

    @Column(name = "doc_name")
    private String docName;

    @Column(name = "doc_degree")
    private String docDegree;

    @Column(name = "doc_exp")
    private String docExp;

    @Column(name = "doc_lang")
    private String docLang;

    @Column(name = "doc_bio")
    private String docBio;

    @Column(name = "doc_fees")
    private Float docFees;

    @Column(name = "doc_contact")
    private Long docContact;

    @Column(name = "doc_email")
    private String docEmail;

    @Column(name = "doc_avai")
    private String docAvai;

    @Column(name = "doc_services")
    private String docServices;

    // One Doctor -> Many Appointments
    @JsonIgnore
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    public Doctor() {}

    //Getters and Setters
    public Long getDocId() { return docId; }
    public void setDocId(Long docId) { this.docId = docId; }

    public String getDocName() { return docName; }
    public void setDocName(String docName) { this.docName = docName; }

    public String getDocDegree() { return docDegree; }
    public void setDocDegree(String docDegree) { this.docDegree = docDegree; }

    public String getDocExp() { return docExp; }
    public void setDocExp(String docExp) { this.docExp = docExp; }

    public String getDocLang() { return docLang; }
    public void setDocLang(String docLang) { this.docLang = docLang; }

    public String getDocBio() { return docBio; }
    public void setDocBio(String docBio) { this.docBio = docBio; }

    public Float getDocFees() { return docFees; }
    public void setDocFees(Float docFees) { this.docFees = docFees; }

    public Long getDocContact() { return docContact; }
    public void setDocContact(Long docContact) { this.docContact = docContact; }

    public String getDocEmail() { return docEmail; }
    public void setDocEmail(String docEmail) { this.docEmail = docEmail; }

    public String getDocAvai() { return docAvai; }
    public void setDocAvai(String docAvai) { this.docAvai = docAvai; }

    public String getDocServices() { return docServices; }
    public void setDocServices(String docServices) { this.docServices = docServices; }

    public List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(List<Appointment> appointments) { this.appointments = appointments; }
}


/*
 * 
 [
    {
        "docId": 1,
        "docName": "Dr. Asha Deshmukh",
        "docDegree": "MBBS, MD - Psychiatry",
        "docExp": "12 years",
        "docLang": "English, Hindi, Marathi",
        "docBio": "Specialist in mental health and wellness.",
        "docFees": 1200.0,
        "docContact": 9876543210,
        "docEmail": "asha.deshmukh@example.com",
        "docAvai": "Mon-Fri: 10am - 6pm",
        "docServices": "Therapy, Consultation"
    },
    {
        "docId": 2,
        "docName": "Dr. Rohan Mehta",
        "docDegree": "MBBS, DNB - General Medicine",
        "docExp": "10 years",
        "docLang": "English, Hindi",
        "docBio": "Experienced physician with a focus on chronic illnesses and lifestyle diseases.",
        "docFees": 1000.0,
        "docContact": 9123456789,
        "docEmail": "rohan.mehta@example.com",
        "docAvai": "Mon-Sat: 9am - 2pm",
        "docServices": "Consultation, Diabetes Management"
    },
    {
        "docId": 3,
        "docName": "Dr. Neha Kapoor",
        "docDegree": "BDS, MDS - Orthodontics",
        "docExp": "8 years",
        "docLang": "English, Hindi",
        "docBio": "Orthodontist known for smile corrections and braces treatments.",
        "docFees": 800.0,
        "docContact": 9988776655,
        "docEmail": "neha.kapoor@example.com",
        "docAvai": "Tue-Fri: 11am - 5pm",
        "docServices": "Braces, Smile Design, Dental Checkup"
    },
    {
        "docId": 4,
        "docName": "Dr. Vivek Sharma",
        "docDegree": "MBBS, MS - Orthopedics",
        "docExp": "12 years",
        "docLang": "English, Hindi",
        "docBio": "Orthopedic surgeon specializing in joint replacements and sports injuries.",
        "docFees": 1500.0,
        "docContact": 9012345678,
        "docEmail": "vivek.sharma@example.com",
        "docAvai": "Mon-Wed: 10am - 4pm",
        "docServices": "Fracture Treatment, Joint Replacement"
    },
    {
        "docId": 5,
        "docName": "Dr. Anjali Verma",
        "docDegree": "MBBS, DGO - Gynecology",
        "docExp": "14 years",
        "docLang": "English, Hindi",
        "docBio": "Gynecologist focusing on women’s health and maternity care.",
        "docFees": 1100.0,
        "docContact": 9871234560,
        "docEmail": "anjali.verma@example.com",
        "docAvai": "Mon-Sat: 9am - 3pm",
        "docServices": "Prenatal Care, PCOS Treatment, Women Health Check"
    }
]
 * 
 */
