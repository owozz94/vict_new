package com.vict.vict_new.join.controller;

import com.vict.vict_new.join.dto.User;
import com.vict.vict_new.join.service.JoinService;
import jakarta.validation.Valid;
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
    public ResponseEntity<String> join(@Valid User user, RedirectAttributes redirect){
        int success = service.insertUser(user);
        if(success == 0){ //존재하는 email
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("emailExist");
        }else if(success == -1){ //하나의 번호에 이메일이 3개 이상 존재
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("userAccountCountOver");
        }else {
            return ResponseEntity.status(HttpStatus.CREATED).body("success");
        }
    }
}
