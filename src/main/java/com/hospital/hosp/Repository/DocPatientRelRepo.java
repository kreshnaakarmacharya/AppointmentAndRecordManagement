package com.hospital.hosp.Repository;

import com.hospital.hosp.Model.DocPatientRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DocPatientRelRepo extends JpaRepository<DocPatientRel, Long> {
    DocPatientRel findById(long id);
    @Modifying
    @Query("DELETE FROM DocPatientRel r WHERE r.doctorId = :doctorId")
    void deleteByDoctorId(@Param("doctorId") Long doctorId);

    @Modifying
    @Query("DELETE  FROM DocPatientRel  r WHERE r.patientId=:patientID")
    void deleteByPatientId(@Param("patientID") Long patientID);
}
