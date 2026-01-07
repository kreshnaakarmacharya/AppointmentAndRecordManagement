package com.hospital.hosp.controller;

import com.hospital.hosp.Model.BookAppointment;
import com.hospital.hosp.Model.Doctor;
import com.hospital.hosp.Model.Patient;
import com.hospital.hosp.Model.Specialization;
import com.hospital.hosp.service.BookAppointmentService;
import com.hospital.hosp.service.DoctorService;
import com.hospital.hosp.service.PatientService;
import com.hospital.hosp.service.SpecializationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class BookAppointmentController {
    @Autowired
    private SpecializationService specializationService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private BookAppointmentService bookAppointmentService;
    @Autowired
    private PatientService patientService;
    @GetMapping("/patient/bookAppointment")
    public String getBookAppointment(Model model){
        List<Specialization> specList = this.specializationService.getAllSpecializations();
        List<Doctor> doctorList = this.doctorService.doctorList();

        model.addAttribute("specList",specList);
        model.addAttribute("doctorList",doctorList);

        return "Patient/BookAppointment";
    }
    @PostMapping("/patient/saveAppointment")
    public String bookAppointment(HttpSession session,
                                  @ModelAttribute BookAppointment bookAppointment,
                                  Model model) {

        Patient patient = (Patient) session.getAttribute("patient");
        if (patient == null) {
            return "redirect:/patient/login";
        }
        bookAppointment.setPatientId(patient.getId());
        bookAppointmentService.addAppointment(bookAppointment);
        model.addAttribute("status", bookAppointment.getStatus());
        return "Patient/BookAppointment";
    }



}
