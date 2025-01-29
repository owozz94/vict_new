package com.vict.vict_new.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class VictController {
    @RequestMapping("/")
        public String mainPage() {
            return "main/vict";
    }
}
