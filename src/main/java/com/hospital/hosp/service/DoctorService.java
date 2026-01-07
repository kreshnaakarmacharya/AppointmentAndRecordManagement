package com.hospital.hosp.service;

import com.hospital.hosp.Model.BookAppointment;
import com.hospital.hosp.Model.Doctor;
import com.hospital.hosp.Model.Specialization;
import com.hospital.hosp.Repository.BookAppointmentRepository;
import com.hospital.hosp.Repository.DocPatientRelRepo;
import com.hospital.hosp.Repository.DoctorRepository;
import com.hospital.hosp.Repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private SpecializationRepository specializationRepository;
    @Autowired
    private BookAppointmentRepository bookAppointmentRepository;
    @Autowired
    private DocPatientRelRepo docPatientRelRepo;

    public void addDoctor(Doctor doctor){
        doctorRepository.save(doctor);
    }

    public List<Specialization> specializationList(){
        return specializationRepository.findAll();
    }

    public Doctor getDoctorByEmailAndPassword(String email,String password){
        return doctorRepository.findByEmailAndPassword(email,password);
    }

    public List<Doctor> doctorList(){
        return doctorRepository.findAll();
    }

    @Transactional
    public void deleteDoctor(Long id) {
        docPatientRelRepo.deleteByDoctorId(id);
        bookAppointmentRepository.deleteByDocId(id);
        doctorRepository.deleteById(id);
    }


    public void updateDoctor(Doctor doctor){
        Doctor updateDoctor=doctorRepository.findById(doctor.getId()).get();
        updateDoctor.setName(doctor.getName());
        updateDoctor.setEmail(doctor.getEmail());
        updateDoctor.setAddress(doctor.getAddress());
        updateDoctor.setPhone(doctor.getPhone());
        updateDoctor.setGender(doctor.getGender());
        updateDoctor.setNote(doctor.getNote());
        doctorRepository.save(updateDoctor);
    }

    public Doctor getDoctorById(Long id){
        return doctorRepository.findById(id).get();
    }

    public Map<String,String> registerDoctorInfo(Doctor doctor) throws Exception{
        Map<String,String> result = new HashMap<>();
        this.doctorRepository.save(doctor);
        result.put("status","Success");
        result.put("message","You have sucessfully created doctor Data.");
        return result;
    }

    public boolean approveOrRejectAppointment(long bookAppointmentId, boolean isApproved){
        boolean result = false;

        try{
            BookAppointment bookAppointment = this.bookAppointmentRepository.findById(bookAppointmentId).get();

            if(isApproved){
                bookAppointment.setStatus(BookAppointment.AppointmentStatus.APPROVED);
            } else {
                bookAppointment.setStatus(BookAppointment.AppointmentStatus.REJECTED);
            }
            this.bookAppointmentRepository.save(bookAppointment);
            result = true;
        } catch(Exception er){
            er.printStackTrace();
            result = false;
        } finally {
            return result;
        }
    }


}
