package com.kos.springSecurity.demoSecurity.controllers;

import com.kos.springSecurity.demoSecurity.models.Person;
import com.kos.springSecurity.demoSecurity.security.PersonDetails;
import com.kos.springSecurity.demoSecurity.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class HelloController {

    private final AdminService adminService;

    @Autowired
    public HelloController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/")
    public String startPage() {
        return "redirect:/hello";
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/getUserInfo")
    public String getUserInfo() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        System.out.println(personDetails.getPerson());

        return "/hello";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/secondAdmin")
    public String secondAdminPage() {
        adminService.doAdminStuff();
        return "second_admin";
    }
}
