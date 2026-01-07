package com.hospital.hosp.controller;

import com.hospital.hosp.Model.Admin;
import com.hospital.hosp.Model.Specialization;
import com.hospital.hosp.Repository.AdminRepository;
import com.hospital.hosp.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SpecializationController {
    @Autowired
    private SpecializationService specService;

    @GetMapping("/admin/specialization")
    public String getSpecialization(){
        return "Specialization/Specialization";
    }

    @PostMapping("/admin/specializationPost")
    public String postController(@ModelAttribute Specialization spec,Model model){
        specService.addSpecialization(spec);
        model.addAttribute("specList", specService.getAllSpecializations());
        return "Specialization/SpecializationList";
    }

    @GetMapping("/spec/specializationList")
    public String getSpecializationList(Model model){
        model.addAttribute("specList", specService.getAllSpecializations());
        return "Specialization/SpecializationList";
    }

    @GetMapping("/spec/specEditForm")
    public String getSpecializationEditForm(@RequestParam("id" ) Long id, Model model){
        model.addAttribute("specialization",specService.getSpecializationById(id));
        return  "Specialization/SpecializationEditForm";
    }

    @GetMapping("/spec/specDelete")
    public String deleteSpecialization(@RequestParam("id") Long id){
        specService.deleteSpecialization(id);
        return "redirect:/spec/specializationList";
    }

    @PostMapping("/spec/updateSpecialization")
    public String updateSpecializatiopnData(@ModelAttribute Specialization spec){
        specService.updateSpecialization(spec);
        return "redirect:/spec/specializationList";
    }
}
