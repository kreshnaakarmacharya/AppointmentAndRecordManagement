package com.hospital.hosp.Repository;

import com.hospital.hosp.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    Patient findByNameAndPassword(String name, String password);
    @Query("SELECT p FROM Patient p WHERE p.id IN " +
            "(SELECT r.patientId FROM DocPatientRel r WHERE r.doctorId = :doctorId)")
    List<Patient> findPatientsByDoctorId(@Param("doctorId") Long doctorId);
    



}
