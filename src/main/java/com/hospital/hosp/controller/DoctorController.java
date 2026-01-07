package com.hospital.hosp.controller;

import com.hospital.hosp.Model.BookAppointment;
import com.hospital.hosp.Model.Doctor;
import com.hospital.hosp.Model.pojo.AppointmentDetails;
import com.hospital.hosp.Repository.BookAppointmentRepository;
import com.hospital.hosp.service.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DoctorController {
    @Autowired
    private SpecializationService specializationService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private BookAppointmentService bookAppointmentService;
    @Autowired
    private BookAppointmentRepository bookAppointmentRepository;

    @GetMapping("/doctor/getregister")
    public String getDoctorRegister(Model model) {
        model.addAttribute("spec",doctorService.specializationList());
        return "Doctor/Register";
    }
//    @PostMapping("/doctor/doctorRegister")
//    public String postDoctorRegister(@ModelAttribute Doctor doctor){
//        doctorService.addDoctor(doctor);
//        return "Doctor/DoctorList";
//    }

    @GetMapping("/doctor/doctorLogin")
    public String getDoctorLogin(){
        return "Doctor/DoctorLogin";
    }

    @PostMapping("/doctor/doctorlogin")
    public String postDoctorLogin(HttpSession session, @ModelAttribute Doctor doctor, Model model){
        Doctor validDoctor=doctorService.getDoctorByEmailAndPassword(doctor.getEmail(),doctor.getPassword());
        if(validDoctor!=null){
            model.addAttribute("doctor",validDoctor);
            session.setAttribute("doctor",validDoctor);
            return "Doctor/DoctorHomePage";
        }
        model.addAttribute("error","Invalid email or password");
        return "Doctor/DoctorLogin";
    }

    @GetMapping("/doctor/doctorList")
    public String getdoctorList(Model model){
        model.addAttribute("doctorList",doctorService.doctorList());
        return "Doctor/DoctorList";
    }

    @GetMapping("/doctor/editDoctor")
    public String getEditForm(@RequestParam("id")  Long id,Model model){
        model.addAttribute("doctor",doctorService.getDoctorById(id));
        return "Doctor/DoctorEditForm";
    }

    @PostMapping("/doctor/updatedoctor")
    public String updateDoctor(@ModelAttribute Doctor doctor){
        doctorService.updateDoctor(doctor);
        return "redirect:/doctor/doctorList";
    }

    @GetMapping("/doctor/deleteDoctor")
    public String deleteDoctor(@RequestParam("id")  Long id){
        doctorService.deleteDoctor(id);
        return "redirect:/doctor/doctorList";
    }

    @PostMapping("/doctor/doctorRegister")
    @ResponseBody
    public ResponseEntity<Map<String,String>> registerDoctor(@Valid @RequestBody Doctor doctor){
        Map<String,String> result=new HashMap<>();

        try{
            result=doctorService.registerDoctorInfo(doctor);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            result.put("status","Failed");
            result.put("message","Failed to save Doctor Info.");
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/doctor/viewPatient")
    public String getDoctorView(@RequestParam("id") Long id,Model model){

        model.addAttribute("docList",doctorService.getDoctorById(id));
        model.addAttribute("patient",adminService.getPatientByDoctorId(id));

        return "Doctor/DoctorViewPage";
    }

    @GetMapping("/doctor/doctorPanel")
    public String doctorPanel(){
        return "Doctor/DoctorHomePage";
    }
    @GetMapping("/doctor/logout")
    public String adminLogout(){
        return "Index";
    }

    @GetMapping("/doctor/viewAppointment")
    public String viewAppointment(@RequestParam(value = "isUpdateSuccess", required = false) Boolean isUpdateSuccess,  HttpSession session,Model model){
        Doctor doctor=(Doctor) session.getAttribute("doctor");
        if (doctor == null) {
            return "redirect:/doctor/doctorlogin";
        }
        Long docId=doctor.getId();
        List<AppointmentDetails> appointments=bookAppointmentRepository.getAllByDoctorId(docId);
        model.addAttribute("appointments",appointments);
        model.addAttribute("isUpdateSuccess", isUpdateSuccess);
        return "Doctor/ViewAppointment";
    }
    @GetMapping("/doctor/view")
    public String View(@RequestParam("id") Long id, Model model){
        model.addAttribute("ViewAppointmentDetails",bookAppointmentService.getAppointmentById(id));
        return "Doctor/View";
    }

    @PostMapping("/doctor/approveOrRejectAppointment")
    public String approveAppointment(@RequestParam("id") long appointmentId, @RequestParam("isApproved") boolean isApproved){
        boolean result = this.doctorService.approveOrRejectAppointment(appointmentId,isApproved);
        return "redirect:/doctor/viewAppointment?isUpdateSuccess="+result;
    }

    @GetMapping("/doctor/getListOfAppointments")
    public String listofAppointments(HttpSession session,Model model){
        Doctor doctor=(Doctor) session.getAttribute("doctor");
        if(doctor == null){
            return "redirect:/doctor/doctorlogin";
        }
        long docId=doctor.getId();
        List<AppointmentDetails> approvedAppointments=bookAppointmentRepository.findAllByDoctorIdAndStatus(docId, BookAppointment.AppointmentStatus.APPROVED);
        model.addAttribute("listOfAppointments",approvedAppointments);
        return "Doctor/Appointments";
    }



}
