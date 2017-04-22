package com.forestnewark;

import com.forestnewark.bean.ActionItem;
import com.forestnewark.bean.User;
import com.forestnewark.repository.ActionItemRepository;
import com.forestnewark.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;




@Controller
@SessionAttributes("currentUser")
public class AdminController {

    final
    UserRepository userRepository;

    final
    ActionItemRepository actionItemRepository;

    @Autowired
    public AdminController(ActionItemRepository actionItemRepository, UserRepository userRepository) {

        this.actionItemRepository = actionItemRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("users",userRepository.findAll());
        model.addAttribute("actionitems",actionItemRepository.findAll());
        return "index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public RedirectView login(ModelMap model, @RequestParam("username") String username, @RequestParam("password") String password){

        model.put("currentUser",username);
        return new RedirectView("/");

    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public RedirectView updateUser(@RequestParam("action") String action, User user) {

        System.out.println(user);
        userRepository.save(user);

        return new RedirectView("/");
    }

    //Default to Guest
    @ModelAttribute("currentUser")
    public String setUserDefault(){
       return "Guest";
    }




    //Action Item
    @RequestMapping(value = "/addActionItem",method =RequestMethod.POST)
    public RedirectView updateActionItem(ActionItem actionItem, @RequestParam("action") String action){

        if(action.equals("update")){
            if(actionItem.getId() == null){
                actionItemRepository.save(actionItem);
            }
            else {
                ActionItem updateItem = actionItemRepository.getOne(actionItem.getId());
                updateItem.setId(actionItem.getId());
                updateItem.setItem(actionItem.getItem());
                updateItem.setStatus(actionItem.getStatus());
                updateItem.setPriority(actionItem.getPriority());
                updateItem.setComments(actionItem.getComments());
                actionItemRepository.save(updateItem);

            }
        }
        if(action.equals("delete")){
            actionItemRepository.delete(actionItem);
        }

        return new RedirectView("/");
    }



}
