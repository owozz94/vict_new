package com.vict.vict_new.join.controller;

import com.vict.vict_new.join.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/join")
public class JoinRestController {

    private final JoinService service;
    @RequestMapping("/emailExist/{email}")
    public int emailExist(@PathVariable("email") String email){
        int emailExt = service.getEmailExist(email);
        return emailExt;
    }
}
