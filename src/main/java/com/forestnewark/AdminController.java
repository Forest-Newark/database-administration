package com.forestnewark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by forestnewark on 4/18/17.
 */

@Controller
public class AdminController {


    final
    UserRepository repo;

    @Autowired
    public AdminController(UserRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("users",repo.getAllUsers());
        return "index";
    }



}
