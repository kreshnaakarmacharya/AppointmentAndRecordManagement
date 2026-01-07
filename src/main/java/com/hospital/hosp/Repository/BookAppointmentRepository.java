package com.hospital.hosp.Repository;

import com.hospital.hosp.Model.BookAppointment;
import com.hospital.hosp.Model.pojo.AppointmentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookAppointmentRepository extends JpaRepository<BookAppointment,Long>{
   List<BookAppointment> findByDoctorId(long id);

   @Query("SELECT new com.hospital.hosp.Model.pojo.AppointmentDetails(ba,p,null) " +
           "FROM BookAppointment ba INNER JOIN Patient p ON ba.patientId=p.id WHERE ba.doctorId=:docId")
   List<AppointmentDetails> getAllByDoctorId(@Param("docId") long docId);

   @Query("SELECT new com.hospital.hosp.Model.pojo.AppointmentDetails(ba,p,null) " +
           "FROM BookAppointment ba INNER JOIN Patient p ON ba.patientId=p.id WHERE ba.doctorId=:docId AND ba.status=:status")
   List<AppointmentDetails> findAllByDoctorIdAndStatus(@Param("docId") long docId, @Param("status") BookAppointment.AppointmentStatus status);

   @Query("SELECT new com.hospital.hosp.Model.pojo.AppointmentDetails(ba,null,d) " +
           "FROM BookAppointment ba INNER JOIN Doctor d ON ba.doctorId = d.id " +
           "WHERE ba.patientId = :patientId")
   List<AppointmentDetails> getAllByPatientId(@Param("patientId") long patientId);

   @Modifying
   @Query("DELETE FROM BookAppointment b WHERE b.patientId=:patientId")
   void deleteByPatientId(@Param("patientId") long patientId);

   @Modifying
   @Query("DELETE FROM BookAppointment b WHERE b.doctorId = :docId")
   void deleteByDocId(@Param("docId") Long docId);

   @Modifying
   @Query("DELETE FROM BookAppointment b WHERE b.specialization= :specId")
   void deleteBySpecId(@Param("specId") Long specId);

}
