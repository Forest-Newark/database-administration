package com.forestnewark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by forestnewark on 4/18/17.
 */

@Controller
@SessionAttributes("user")
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

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public RedirectView login(ModelMap model, @RequestParam("username") String username, @RequestParam("password") String password){

        model.put("user",username);
        return new RedirectView("/");

    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public RedirectView addUser(@RequestParam("firstName")String firstName,@RequestParam("lastName")String lastName,@RequestParam("rank") String rank,
                                @RequestParam("permission")String permission,@RequestParam("email")String email,@RequestParam("password") String password) {
        User user = new User(null,firstName,lastName,rank,permission,email,null,password);
        repo.addUser(user);
        return new RedirectView("/");
    }

    //Default to Guest
    @ModelAttribute("user")
    public String setUserDefault(){
       return "Guest";
    }

}
