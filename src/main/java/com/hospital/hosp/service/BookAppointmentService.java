package com.hospital.hosp.service;

import com.hospital.hosp.Model.BookAppointment;
import com.hospital.hosp.Repository.BookAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookAppointmentService {
    @Autowired
    private BookAppointmentRepository bookAppointmentRepository;
    public void addAppointment(BookAppointment bookAppointment){

        bookAppointmentRepository.save(bookAppointment);
    }
    public List<BookAppointment> getAllAppointments(){
        return bookAppointmentRepository.findAll();
    }

    public BookAppointment getAppointmentById(Long id){
        return bookAppointmentRepository.findById(id).get();
    }
    public void updateAppointment(BookAppointment bookAppointment){

        bookAppointmentRepository.save(bookAppointment);
    }

}
