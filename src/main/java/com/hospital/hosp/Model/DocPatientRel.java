package com.hospital.hosp.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name="doctor_patient_relation")
@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class DocPatientRel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "doctor_id")
    @Min(value = 1, message="Invalid doctor ID")
    private  Long doctorId;

    @Min(value = 1, message="Invalid patient ID")
    @Column(name = "patient_id")
    private Long patientId;
}
