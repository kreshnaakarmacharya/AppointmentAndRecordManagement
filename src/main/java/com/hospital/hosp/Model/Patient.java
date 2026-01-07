package com.hospital.hosp.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="Patient_tbl")
@Data
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fullname")
    private String name;
    @Column(name = "gender")
    private String gender;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password;
    @Column(name = "dob")
    private LocalDate dob;
    @Column(name = "marital_status")
    private String maritalStatus;
    @Column(name = "present_address")
    private String presentAddress;
    @Column(name = "communication_address")
    private String communicationAddress;
    @ElementCollection
    @CollectionTable
    @Column(name = "medical_condition")
    private List<String> medicalHistory;
    @Column(name = "other_details")
    private String otherDetails;

}
