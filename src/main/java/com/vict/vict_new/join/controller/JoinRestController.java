package com.vict.vict_new.join.controller;

import com.vict.vict_new.custom.UserRegistrationException;
import com.vict.vict_new.join.dto.User;
import com.vict.vict_new.join.service.JoinService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequiredArgsConstructor
@RequestMapping("/join")
public class JoinRestController {

    private final JoinService service;
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @RequestMapping("/emailExist/{email}")
    public int emailExist(@PathVariable("email") String email){
        int emailExt = service.getEmailExist(email);
        return emailExt;
    }
    @ExceptionHandler(UserRegistrationException.class)
    @PostMapping("/join")
    public ResponseEntity<String> join(@Valid User user, UserRegistrationException exception){
        int success = service.insertUser(user);
        if(success == 1){ //존재하는 email
            log.info("user insert success!");
            return ResponseEntity.status(HttpStatus.CREATED).body("success");
        }else{
            log.error("user insert fail!");
            return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());

        }
    }
}
