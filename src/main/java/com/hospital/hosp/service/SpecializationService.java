package com.hospital.hosp.service;

import com.hospital.hosp.Model.Admin;
import com.hospital.hosp.Model.Specialization;
import com.hospital.hosp.Repository.AdminRepository;
import com.hospital.hosp.Repository.BookAppointmentRepository;
import com.hospital.hosp.Repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpecializationService {
    @Autowired
    private SpecializationRepository specRepository;

    @Autowired
    private BookAppointmentRepository bookAppointmentRepository;


    public void addSpecialization(Specialization specialization){
        specRepository.save(specialization);
    }
    public List<Specialization> getAllSpecializations(){
        return specRepository.findAll();
    }
    public Specialization getSpecializationById(Long id){
        return specRepository.findById(id).get();
    }
    @Transactional
    public void deleteSpecialization(Long id) {
        bookAppointmentRepository.deleteBySpecId(id);
        specRepository.deleteById(id);
    }
    public void updateSpecialization(Specialization specialization){
        specRepository.save(specialization);
    }

}
