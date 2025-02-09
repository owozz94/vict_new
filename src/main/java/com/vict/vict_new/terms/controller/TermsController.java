package com.vict.vict_new.terms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/terms")
public class TermsController {

    @RequestMapping("")
    public String terms(){

        return "terms/terms";
    }
}
