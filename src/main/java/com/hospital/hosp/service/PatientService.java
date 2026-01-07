package com.hospital.hosp.service;

import com.hospital.hosp.Model.Patient;
import com.hospital.hosp.Repository.BookAppointmentRepository;
import com.hospital.hosp.Repository.DocPatientRelRepo;
import com.hospital.hosp.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private BookAppointmentRepository bookAppointmentRepository;

    @Autowired
    private DocPatientRelRepo docPatientRelRepo;

    public void addPatient(Patient p){
        patientRepository.save(p);
    }
    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }
    @Transactional
    public  void deletePatient(Long id){
        docPatientRelRepo.deleteByPatientId(id);
        bookAppointmentRepository.deleteByPatientId(id);
        patientRepository.deleteById(id);
    }
    public void updatePatient(Patient p){
        Patient oldPatient = patientRepository.findById(p.getId()).get();
        oldPatient.setName(p.getName());
        oldPatient.setGender(p.getGender());
        oldPatient.setDob(p.getDob());
        oldPatient.setPhone(p.getPhone());
        oldPatient.setMaritalStatus(p.getMaritalStatus());
        oldPatient.setCommunicationAddress(p.getCommunicationAddress());
        oldPatient.setPresentAddress(p.getPresentAddress());
        oldPatient.setMedicalHistory(p.getMedicalHistory());
        oldPatient.setOtherDetails(p.getOtherDetails());

        patientRepository.save(oldPatient);
    }
    public Patient getPatientById(Long id){
        return patientRepository.findById(id).get();
    }

    public Patient getPatientByNameAndPassword(String name, String password){
        return patientRepository.findByNameAndPassword(name, password);
    }

}
