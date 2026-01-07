package com.hospital.hosp.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
@Entity
@Table(name="BookAppointment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookAppointment {
    public enum AppointmentStatus {
        PENDING,
        APPROVED,
        REJECTED
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private long id;
    @Column(name="title")
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name="specialization_id")
    private long specialization;
    @Column(name="date")
    private LocalDate date;
    @Column(name="time")
    private LocalTime time;
    @Column(name="doc_id")
    private long doctorId;

    @Column(name="Patient_id")
    private long patientId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AppointmentStatus status;

    @PrePersist
    public void setDefaultStatus(){
        if(status==null){
            status=AppointmentStatus.PENDING;
        }
    }
}
