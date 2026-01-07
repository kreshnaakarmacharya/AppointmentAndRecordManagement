package com.hospital.hosp.service;

import com.hospital.hosp.Model.Admin;
import com.hospital.hosp.Model.DocPatientRel;
import com.hospital.hosp.Model.Doctor;
import com.hospital.hosp.Model.Patient;
import com.hospital.hosp.Repository.AdminRepository;
import com.hospital.hosp.Repository.DocPatientRelRepo;
import com.hospital.hosp.Repository.DoctorRepository;
import com.hospital.hosp.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepo;
    @Autowired
    private DocPatientRelRepo docPatientRelRepo;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    public void addAdmin(Admin admin){

        adminRepo.save(admin);
    }
    public Admin getAdminByUsernameAndPassword(String username,String password){
        return adminRepo.findByUsernameAndPassword(username,password);
    }

    public Map<String, String> saveDocPatientRelation(DocPatientRel docPatientRel) throws Exception{
        Map<String, String> result = new HashMap<>();

        this.docPatientRelRepo.save(docPatientRel);

        result.put("status","success");
        result.put("message","The doctor patient relation have been saved");

        return result;
    }
    public List<Patient> getPatientByDoctorId(Long doctorId){
        return patientRepository.findPatientsByDoctorId(doctorId);
    }

    public List<Doctor> getDoctorsByPatientId(Long  patientId){
        return doctorRepository.findDoctorsByPatientId(patientId);
    }

}
