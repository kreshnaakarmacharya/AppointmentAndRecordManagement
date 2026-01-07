package com.hospital.hosp.controller;

import com.hospital.hosp.Model.Admin;
import com.hospital.hosp.Model.DocPatientRel;
import com.hospital.hosp.service.AdminService;
import com.hospital.hosp.service.DoctorService;
import com.hospital.hosp.service.PatientService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class Admincontroller {
    @Autowired
    private AdminService adminService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/admin/register")
    public String getAdminRegister(){
        return "/Admin/AdminRegister";
    }

    @PostMapping("/admin/registerAdmin")
    public String postAdminRegister(@ModelAttribute Admin admin){
        adminService.addAdmin(admin);
        return "/Admin/AdminLogin";
    }

    @GetMapping("/admin/login")
    public String getAdminLogin(){
        return "/Admin/AdminLogin";
    }

    @PostMapping("/admin/login")
    public String postAdminLogin(HttpSession session, @ModelAttribute Admin admin, Model model){
        Admin validAdmin=adminService.getAdminByUsernameAndPassword(admin.getUsername(),admin.getPassword());
        if(validAdmin!=null){
            model.addAttribute("admin",validAdmin);
            session.setAttribute("user",validAdmin);
            return "/Admin/AdminHomePage";
        }
        model.addAttribute("message","Invalid Username/Password");
        return "/Admin/AdminLogin";
    }
    @GetMapping("/admin/homepage")
    public String getHospitalAdmin(){
        return "/Admin/AdminHomePage";
    }

    @GetMapping("/admin/logout")
    public String adminLogout(){
        return "Index";
    }

    @GetMapping("/doctorPatientRelation")
    public String getDoctorPatientRealtion(Model model){
        model.addAttribute("doctorList",doctorService.doctorList());
        model.addAttribute("patientList",patientService.getAllPatients());
        return "Admin/DoctorPatientRelation";
    }

    @PostMapping("/saveDocPatientRel")
    @ResponseBody
    public ResponseEntity<Map<String,String>> saveDocPatientRelation(@Valid @RequestBody DocPatientRel docPatientRel){
        Map<String, String> result = new HashMap<>();

        try{
            result = this.adminService.saveDocPatientRelation(docPatientRel);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch(Exception er){
            result.put("status","failed");
            result.put("message","Failed to save doctor patient relation. Try again later.");
            er.printStackTrace();
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
