package com.vict.vict_new.join.controller;

import com.vict.vict_new.join.dto.User;
import com.vict.vict_new.join.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/join")
    public ResponseEntity<String> join(User user, RedirectAttributes redirect){
        int success = service.insertUser(user);
        if(success >0){
            return ResponseEntity.status(HttpStatus.CREATED).body("success");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail");
        }
    }
}
