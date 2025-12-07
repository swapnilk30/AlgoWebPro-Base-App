package com.algowebpro.scm.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.algowebpro.scm.entity.ScmUser;

@Controller
@RequestMapping("/scm-user")
public class ScmUserViewController {

    // User Dashboard Page
    @GetMapping("/dashboard")
    public String userDashboard() {

        return "scm/user/dashboard";
    }


    // User Profile Page
    @GetMapping("/profile")
    public String userProfile(Principal principal, Model model) {
        //String email = principal.getName();
        //ScmUser user = scmUserService.findByEmail(email);
        
        //model.addAttribute("user", user);
        //model.addAttribute("pageTitle", "My Profile - Smart Contact Manager");
        
        return "scm/user/profile";
    }

    



    //user add contacts page

    //user view page

    //user edit contact page

    //user delete contact

    //user search contact


}
