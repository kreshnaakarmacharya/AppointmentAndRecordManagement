package com.hospital.hosp.Repository;

import com.hospital.hosp.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    Doctor findByEmailAndPassword(String email,String password);
    @Query("SELECT d FROM Doctor d WHERE d.id IN " +
            "(SELECT r.doctorId FROM DocPatientRel r WHERE r.patientId = :patientId)")
    List<Doctor> findDoctorsByPatientId(@Param("patientId") Long patientId);



}

