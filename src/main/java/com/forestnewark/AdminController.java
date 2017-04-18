package com.forestnewark;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by forestnewark on 4/18/17.
 */

@Controller
public class AdminController {

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
