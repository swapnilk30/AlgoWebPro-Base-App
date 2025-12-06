package com.algowebpro.scm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.algowebpro.scm.entity.ScmUser;
import com.algowebpro.scm.forms.ScmUserForm;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
@Controller
@RequestMapping("/scm")
public class ScmViewController {

    private static final Logger log = LoggerFactory.getLogger(ScmViewController.class);


    @GetMapping("/home")
    public String home(Model model) {
        log.info("Smart Contact Manager: Home page accessed");
        model.addAttribute("message", "Welcome to Smart Contact Manager!");
        return "scm/home"; // loads templates/scm/home.html
    }

    @GetMapping("/about")
    public String about(Model model) {
        log.info("Smart Contact Manager: About page accessed");

        model.addAttribute("title", "About Smart Contact Manager");
        model.addAttribute("description",
                "Smart Contact Manager (SCM) helps users securely store, organize, and manage personal and professional contacts using a clean and modern interface.");

        return "scm/about";
    }

    @GetMapping("/services")
    public String services(Model model) {
        log.info("Smart Contact Manager: Services page accessed");

        model.addAttribute("title", "SCM Features & Services");

        model.addAttribute("services", new String[]{
                "Secure contact storage",
                "Smart search and filtering",
                "Tag-based contact grouping",
                "Cloud sync & backup",
                "Import/export contacts",
                "Profile photos & notes"
        });

        return "scm/services";
    }


    @GetMapping("/test-tailwind")
    public String testTailwind() {
        return "scm/test-tailwind"; // This should point to your test HTML file
    }


    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("pageTitle", "Login - Smart Contact Manager");
        return "scm/auth/login";
    }
    
    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        ScmUserForm scmUserForm = new ScmUserForm();
        model.addAttribute("scmUserForm", scmUserForm);
        model.addAttribute("pageTitle", "Sign Up - Smart Contact Manager");
        return "scm/auth/signup";
    }

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute ScmUserForm scmUserForm) {

        log.info("Smart Contact Manager: registerUser()  accessed");

        return "redirect:/scm/signup";
    }


}
