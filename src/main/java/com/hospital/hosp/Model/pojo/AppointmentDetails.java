package com.hospital.hosp.Model.pojo;

import com.hospital.hosp.Model.BookAppointment;
import com.hospital.hosp.Model.Doctor;
import com.hospital.hosp.Model.Patient;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class AppointmentDetails {
    private BookAppointment bookAppointment;
    private Patient patient;
    private Doctor doctor;

}
