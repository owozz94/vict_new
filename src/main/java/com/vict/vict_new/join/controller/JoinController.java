package com.vict.vict_new.join.controller;

import com.vict.vict_new.join.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/join")
public class JoinController {

    @GetMapping("")
    public String join(@Validated @ModelAttribute("form") User user){
        return "join/joinForm";
    }
}
