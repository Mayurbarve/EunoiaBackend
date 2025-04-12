package HealthProject.Eunoia.model;

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
