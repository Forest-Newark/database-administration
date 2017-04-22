package com.forestnewark;

import com.forestnewark.repository.ActionItemRepository;
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
    ActionItemRepository actionItemRepository;

    final
    DatabaseRepository repo;

    @Autowired
    public AdminController(DatabaseRepository repo, ActionItemRepository actionItemRepository) {
        this.repo = repo;
        this.actionItemRepository = actionItemRepository;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("users",repo.getAllUsers());
        model.addAttribute("actionitems",actionItemRepository.findAll());
        return "index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public RedirectView login(ModelMap model, @RequestParam("username") String username, @RequestParam("password") String password){

        model.put("user",username);
        return new RedirectView("/");

    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public RedirectView updateUser(@RequestParam("action") String action,@RequestParam("id") Integer id,@RequestParam("firstName")String firstName,@RequestParam("lastName")String lastName,@RequestParam("rank") String rank,
                                @RequestParam("permission")String permission,@RequestParam("email")String email,@RequestParam("password") String password,@RequestParam("username") String userName) {
        User user = new User(id,firstName,lastName,rank,permission,email,userName,password);

        if(action.equals("update")){
            repo.updateUser(user);
        }
        if(action.equals("delete")){
            repo.deleteUser(user);
        }

        return new RedirectView("/");
    }

    //Default to Guest
    @ModelAttribute("user")
    public String setUserDefault(){
       return "Guest";
    }




    //Update Action Item
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
