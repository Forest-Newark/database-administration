package com.forestnewark;

import com.forestnewark.bean.*;
import com.forestnewark.service.LibraryService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.*;


@Controller
@SessionAttributes({"currentUser", "accessRights"})
public class AdminController {

    private final
    LibraryService libraryService;


    @Autowired
    public AdminController(LibraryService libraryService) {

        this.libraryService = libraryService;
    }


    /**
     * Mapping for Root Request
     *
     * @param model        to render and view
     * @param page         of the composition database set
     * @param itemsPerPage number of items to display in composition database set
     * @return String of index
     */
    @GetMapping("/")
    public String index(ModelMap model, @RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "10") int itemsPerPage) {

        //TODO: Make user sortable
        Sort sort = new Sort(Sort.Direction.valueOf("ASC"), "catagory", "libraryNumber");

        Page<Composition> compositionsPage = libraryService.findAllCompositionsPageable(page, itemsPerPage, sort);

        //Model Attributes for compositions
        model.addAttribute("back", page - 1);
        model.addAttribute("next", page + 1);
        model.addAttribute("lastPage", compositionsPage.getTotalPages());
        model.addAttribute("thisPage", page);
        model.addAttribute("compositionsPage", compositionsPage);
        model.addAttribute("compositionCount",libraryService.compositionCount());

        //Model Attributes for users and action items

        model.addAttribute("users", libraryService.findAllUsers());
        model.addAttribute("actionitems", libraryService.findAllActionItems());

        return "index";
    }

    //TODO: Refactor to "Login Service"
    //Login Mapping. Called by Login Modal
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RedirectView login(ModelMap model, @RequestParam("username") String email, @RequestParam("password") String password) {
        if (libraryService.findUserByEmail(email).size() == 0) {
            return new RedirectView("/");
        } else if (!libraryService.findUserByEmail(email).get(0).getPassword().equals(password)) {
            return new RedirectView("/");
        } else {
            model.put("currentUser", libraryService.findUserByEmail(email).get(0).getFirstName());
            model.put("accessRights", libraryService.findUserByEmail(email).get(0).getPermission());
            return new RedirectView("/");

        }

    }

    @RequestMapping("/logout")
    public RedirectView loutout(ModelMap model) {
        model.put("currentUser", "Guest");
        model.put("accessRights", "view-only");
        return new RedirectView("/");
    }

    /**
     * Set default user name
     *
     * @return user as "Guest"
     */
    @ModelAttribute("currentUser")
    public String setUserDefault() {
        return "Guest";
    }

    /**
     * Set default Access Rights
     *
     * @return accessRights as "view-only"
     */
    @ModelAttribute("accessRights")
    public String setAccessRightsDefault() {
        return "view-only";
    }


    //Update User. Save, Update or Delete User.
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public RedirectView updateUser(@RequestParam("action") String action, User user) {

        if (action.equals("update")) {
            if (user.getId() == null) {
                libraryService.saveUser(user);
            } else {
                //TODO: look into refactoring this... Let libraryService Handle
                User updateUser = libraryService.getOneUser(user.getId());
                updateUser.setId(user.getId());
                updateUser.setFirstName(user.getFirstName());
                updateUser.setLastName(user.getLastName());
                updateUser.setEmail(user.getEmail());
                updateUser.setPassword(user.getPassword());
                updateUser.setRank(user.getRank());
                updateUser.setPermission(user.getPermission());
                libraryService.saveUser(updateUser);
            }
        }

        if (action.equals("delete")) {

            libraryService.deleteUser(user);

        }
        return new RedirectView("/");
    }

    //Action Item
    @RequestMapping(value = "/addActionItem", method = RequestMethod.POST)
    public RedirectView updateActionItem(ActionItem actionItem, @RequestParam("action") String action) {

        if (action.equals("update")) {
            if (actionItem.getId() == null) {
                libraryService.saveActionItem(actionItem);
            } else {
                //TODO: Refactor: Let Library Service Handle
                ActionItem updateItem = libraryService.getOneActionItem(actionItem.getId());
                updateItem.setId(actionItem.getId());
                updateItem.setItem(actionItem.getItem());
                updateItem.setStatus(actionItem.getStatus());
                updateItem.setPriority(actionItem.getPriority());
                updateItem.setComments(actionItem.getComments());
                libraryService.saveActionItem(updateItem);

            }
        }
        if (action.equals("delete")) {
            libraryService.deleteActionItem(actionItem);
        }

        return new RedirectView("/");
    }


    //Composition
    @RequestMapping(value = "/addComposition", method = RequestMethod.POST)
    public RedirectView updateActionItem(Composition composition, @RequestParam("action") String action) {

        System.out.println(composition.getId());

        if (action.equals("update")) {
            if (composition.getId() == null) {
                libraryService.saveComposition(composition);
            } else {

                //TODO: Refactor: Let LibraryService Handle
                Composition updateComp = libraryService.getOneComposition(composition.getId());
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
        if (action.equals("delete")) {
            libraryService.deleteComposition(composition);

        }


        return new RedirectView("/");
    }


    //TODO: Make search results work and Pageable
    //Search stuff
    @RequestMapping("/databaseSearch")
    public String databaseSearch(ModelMap model,@RequestParam("keyword") String keyword, @RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "10") int itemsPerPage) {


        Sort sort = new Sort(Sort.Direction.valueOf("ASC"), "catagory", "libraryNumber");

        Page<Composition> compositionsPage = libraryService.searchCompositions(keyword, page, itemsPerPage,sort);

        model.addAttribute("back", page - 1);
        model.addAttribute("next", page + 1);
        model.addAttribute("lastPage", compositionsPage.getTotalPages());
        model.addAttribute("thisPage", page);
        model.addAttribute("compositionsPage", compositionsPage);
        model.addAttribute("compositionCount",libraryService.compositionCount());
        model.addAttribute("users", libraryService.findAllUsers());
        model.addAttribute("actionitems", libraryService.findAllActionItems());



        return "index";
    }


    @RequestMapping(value = "/submitCSV", method = RequestMethod.POST)
    public
    @ResponseBody
    RedirectView uploadFileHandler(@RequestParam("file") MultipartFile file) throws IOException {


        Reader in = new FileReader(AdminController.convert(file));

        Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("Catagory", "libnum", "Title", "Composer", "Arranger", "Copyright", "Ensemble", "Notes").parse(in);

        //TODO: Refactor to file service
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

    //TODO: Refactor to file service
    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}
