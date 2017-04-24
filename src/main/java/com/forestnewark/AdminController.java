package com.forestnewark;

import com.forestnewark.bean.ActionItem;
import com.forestnewark.bean.Composition;
import com.forestnewark.bean.User;
import com.forestnewark.repository.ActionItemRepository;
import com.forestnewark.repository.CompositionRepository;
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
    CompositionRepository compositionRepository;

    final
    UserRepository userRepository;

    final
    ActionItemRepository actionItemRepository;

    @Autowired
    public AdminController(ActionItemRepository actionItemRepository, UserRepository userRepository, CompositionRepository compositionRepository) {

        this.actionItemRepository = actionItemRepository;
        this.userRepository = userRepository;
        this.compositionRepository = compositionRepository;
    }

    //Index Mapping
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("users",userRepository.findAll());
        model.addAttribute("actionitems",actionItemRepository.findAll());
        model.addAttribute("compositions",compositionRepository.findAll());
        return "index";
    }


    //Login Mapping. Called by Login Modal
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public RedirectView login(ModelMap model, @RequestParam("username") String username, @RequestParam("password") String password){

        model.put("currentUser",username);
        return new RedirectView("/");

    }

    //Set Default Value of "currentUser" to Guest.
    @ModelAttribute("currentUser")
    public String setUserDefault(){
        return "Guest";
    }


    //Update User. Save, Update or Delete User.
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public RedirectView updateUser(@RequestParam("action") String action, User user) {

        if (action.equals("update")){
            if(user.getId() == null){
                userRepository.save(user);
            }else{
                User updateUser = userRepository.getOne(user.getId());
                updateUser.setId(user.getId());
                updateUser.setFirstName(user.getFirstName());
                updateUser.setLastName(user.getLastName());
                updateUser.setEmail(user.getEmail());
                updateUser.setPassword(user.getPassword());
                updateUser.setRank(user.getRank());
                updateUser.setPermission(user.getPermission());
                userRepository.save(updateUser);

            }
        }



        System.out.println(user);
        userRepository.save(user);

        return new RedirectView("/");
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


    //Composition
    @RequestMapping(value = "/addComposition",method =RequestMethod.POST)
    public RedirectView updateActionItem(Composition composition, @RequestParam("action") String action){


        if(action.equals("update")){
            if(composition.getId() == null){
                compositionRepository.save(composition);
            }
            else {
                Composition updateComp = compositionRepository.getOne(composition.getId());
                updateComp.setId(composition.getId());
                updateComp.setArranger(composition.getArranger());
                updateComp.setCatagory(composition.getCatagory());
                updateComp.setComposer(composition.getComposer());
                updateComp.setCopyright(composition.getCopyright());
                updateComp.setEnsemble(composition.getEnsemble());
                updateComp.setLibraryNumber(composition.getLibraryNumber());
                updateComp.setNotes(composition.getNotes());
                updateComp.setTitle(composition.getTitle());
            }
        }
        if(action.equals("delete")){
            compositionRepository.delete(composition);

        }


        return new RedirectView("/");
    }





}
