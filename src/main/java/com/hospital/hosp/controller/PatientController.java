package com.hospital.hosp.controller;

import com.hospital.hosp.Model.BookAppointment;
import com.hospital.hosp.Model.Patient;
import com.hospital.hosp.Model.pojo.AppointmentDetails;
import com.hospital.hosp.Repository.BookAppointmentRepository;
import com.hospital.hosp.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private SpecializationService specializationService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private BookAppointmentService bookAppointmentService;
    @Autowired
    private BookAppointmentRepository bookAppointmentRepository;

    @GetMapping("/patient/patientForm")
    public String getPatientForm(){
        return "Patient/Patient";
    }

    @PostMapping("/patient/PatientData")
    public String postPatientForm(@ModelAttribute Patient patient){
        patientService.addPatient(patient);
        return "redirect:/patient/patientList";
    }
    @GetMapping("/patient/patientList")
    public String patientList(Model model){
        model.addAttribute("patientList",patientService.getAllPatients());
        return "Patient/PatientList";
    }
    @GetMapping("/patient/patientEdit")
    public String editPatientData(@RequestParam("id") long id, Model model){
        model.addAttribute("patient",patientService.getPatientById(id));
        return "Patient/PatientEdit";
    }
    @PostMapping("/patient/patientEdit")
    public String updatePatient(@ModelAttribute Patient patient){
         patientService.updatePatient(patient);
        return  "redirect:/patient/patientList";
    }
    @GetMapping("/patient/patientDelete")
    public String deletepatient(@RequestParam("id") Long id){
        patientService.deletePatient(id);
        return  "redirect:/patient/patientList";
    }

    @GetMapping("/patient/patientLoginForm")
    public String getPatientLogin(){
        return "Patient/PatientLogin";
    }
    @PostMapping("/patient/patientlogin")
    public String patientLogin(HttpSession session, @ModelAttribute Patient patient, Model model){
        Patient validPatient=patientService.getPatientByNameAndPassword(patient.getName(),patient.getPassword());
        if(validPatient!=null){
            model.addAttribute("patient",validPatient);
            session.setAttribute("patient",validPatient);
            session.setAttribute("patientId", validPatient.getId());
            return "Patient/PatientHomePage";
        }
        model.addAttribute("error","Invalid username and/or password");
        return "Patient/PatientLogin";
    }

    @GetMapping("/patient/viewDoctor")
    public String patientView( @RequestParam("id") long id , Model model){
        model.addAttribute("patient",patientService.getPatientById(id));
        model.addAttribute("doctors",adminService.getDoctorsByPatientId(id));
        return"Patient/PatientViewPage";
    }

    @GetMapping("/patient/viewAppointmentStatus")
    public String getAppointmentStatus(HttpSession session, Model model) {
        Long patientId = (Long) session.getAttribute("patientId");
        if (patientId == null) {
            return "redirect:/patient/patientLoginForm";
        }
        List<AppointmentDetails> viewAppointmentStatus = bookAppointmentRepository.getAllByPatientId(patientId);

        model.addAttribute("viewAppointmentStatus", viewAppointmentStatus);
        return "Patient/ViewAppointmentStatus";
    }


    @GetMapping("/patient/patientPortal")
    public String patientPortal(){
        return "Patient/PatientHomePage";
    }

    @GetMapping("/patient/patientProfile")
    public String patientProfile( HttpSession session ,Model model){
        Long patientId =(Long) session.getAttribute("patientId");
        Patient patient=patientService.getPatientById(patientId);
        model.addAttribute("patientProfile",patient);
        return "Patient/PatientProfile";
    }
    @GetMapping("/patient/editProfile")
    public String editProfile(HttpSession session,Model model){
        Long patientId =(Long) session.getAttribute("patientId");
        Patient patient=patientService.getPatientById(patientId);
        model.addAttribute("patientProfile",patient);
        return "Patient/EditProfile";
    }
    @PostMapping("/patient/editProfile")
    @ResponseBody
    public ResponseEntity<String> updatePatientProfile(HttpSession session, @ModelAttribute("patientProfile") Patient patientProfile, Model model) {
        patientService.updatePatient(patientProfile);

        model.addAttribute("patientProfile", patientProfile);

        return ResponseEntity.ok("Updated");
    }
    @GetMapping("/patient/backPatientProfile")
    public String backPatientProfile( HttpSession session ,Model model){
        Long patientId =(Long) session.getAttribute("patientId");
        Patient patient=patientService.getPatientById(patientId);
        model.addAttribute("patientProfile",patient);
        return "Patient/PatientProfile";
    }

    @GetMapping("/patient/patientLogout")
    public String patientLogout(){
        return "Index";
    }




}
