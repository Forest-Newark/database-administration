package com.forestnewark;

import com.forestnewark.bean.*;
import com.forestnewark.repository.ActionItemRepository;
import com.forestnewark.repository.CompositionRepository;
import com.forestnewark.repository.UserRepository;
import com.forestnewark.service.LibraryService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.*;


@Controller
@SessionAttributes({"currentUser", "accessRights"})
public class AdminController {

    final
    LibraryService libraryService;

    final
    CompositionRepository compositionRepository;

    final
    UserRepository userRepository;

    final
    ActionItemRepository actionItemRepository;

    @Autowired
    public AdminController(ActionItemRepository actionItemRepository, UserRepository userRepository, CompositionRepository compositionRepository, LibraryService libraryService) {

        this.actionItemRepository = actionItemRepository;
        this.userRepository = userRepository;
        this.compositionRepository = compositionRepository;
        this.libraryService = libraryService;
    }

    //Index Mapping
    @GetMapping("/")
    public String index(Model model,@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "10") int itemsPerPage){

        Page<Composition> compositionsPage = compositionRepository.findAll(new PageRequest(page-1, itemsPerPage, new Sort(Sort.Direction.valueOf("ASC"), "catagory","libraryNumber")));


        model.addAttribute("back", page-1);
        model.addAttribute("next", page+1);
        model.addAttribute("lastPage", compositionsPage.getTotalPages());
        model.addAttribute("thisPage", page);
        model.addAttribute("compositionsPage", compositionsPage);
       // model.addAttribute("query", "&name=" + name + "&itemsPerPage" + itemsPerPage);

        model.addAttribute("users",userRepository.findAll());
        model.addAttribute("actionitems",actionItemRepository.findAll());
        model.addAttribute("compositions",compositionRepository.findAll());
        return "index";
    }


    //Login Mapping. Called by Login Modal
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public RedirectView login(ModelMap model, @RequestParam("username") String username, @RequestParam("password") String password){
        if(userRepository.findByEmail(username).size()==0){
            return new RedirectView("/git ");
        }else if(!userRepository.findByEmail(username).get(0).getPassword().equals(password)){
            return new RedirectView("/");
        }else {
            model.put("currentUser",userRepository.findByEmail(username).get(0).getFirstName());
            model.put("accessRights",userRepository.findByEmail(username).get(0).getPermission());
            return new RedirectView("/");

        }

    }

   @RequestMapping("/logout")
   public RedirectView loutout(ModelMap model){
        model.put("currentUser","Guest");
        model.put("accessRights","view-only");
        return new RedirectView("/");
   }

    //Set Default Value of "currentUser" to Guest.
    @ModelAttribute("currentUser")
    public String setUserDefault(){
        return "Guest";
    }

    @ModelAttribute("accessRights")
    public String setAccessRightsDefault(){
        return "view-only";
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

        if(action.equals("delete")){
           userRepository.delete(user);

        }

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

        System.out.println(composition.getId());

        if(action.equals("update")){
            if(composition.getId() == null){
                libraryService.saveComposition(composition);
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

                libraryService.saveComposition(updateComp);
            }
        }
        if(action.equals("delete")){
            compositionRepository.delete(composition);

        }


        return new RedirectView("/");
    }


    //Search stuff
    @RequestMapping("/databaseSearch")
    public String databaseSearch(ModelMap model,@RequestParam("keyword") String keyword,@RequestParam("catagory") String catagory,@RequestParam("ensemble") String ensemble){

        System.out.println(keyword);
        model.addAttribute("users",userRepository.findAll());
        model.addAttribute("actionitems",actionItemRepository.findAll());
//        model.addAttribute("compositions", libraryService.searchCompositions(keyword,catagory,ensemble));


       return "index";
    }






    @RequestMapping(value = "/submitCSV", method = RequestMethod.POST)
    public @ResponseBody
    RedirectView uploadFileHandler(@RequestParam("file") MultipartFile file) throws IOException {


        Reader in = new FileReader(AdminController.convert(file));

        Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("Catagory", "libnum", "Title","Composer","Arranger","Copyright","Ensemble","Notes").parse(in);
        for (CSVRecord record : records) {

            Composition composition = new Composition(
                    new Catagory(record.get("Catagory")),
                    Integer.valueOf(record.get("libnum")),
                    record.get("Title"),
                    new Musician(record.get("Composer")),
                    new Musician(record.get("Arranger")),
                    new Ensemble(record.get("Ensemble")),
                    (record.get("Copyright").equals("") ? null : Integer.parseInt(record.get("Copyright"))),
                    record.get("Notes")
            );
            libraryService.saveComposition(composition);

        }

        return new RedirectView("/");
    }


    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
