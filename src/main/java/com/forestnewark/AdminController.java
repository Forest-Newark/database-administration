package com.forestnewark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by forestnewark on 4/18/17.
 */

@Controller
public class AdminController {


    final
    DatabaseRepository repo;

    @Autowired
    public AdminController(DatabaseRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("users",repo.getAllUsers());
        model.addAttribute("actionitems",repo.getAllActionItems());
        return "index";
    }



}
