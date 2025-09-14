package com.vict.vict_new.join.controller;

import com.vict.vict_new.exception.UserRegistrationException;
import com.vict.vict_new.join.dto.User;
import com.vict.vict_new.join.dto.UserManage;
import com.vict.vict_new.join.service.JoinService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/join")
public class JoinRestController {

    private final JoinService service;
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @GetMapping("/phoneExists")
    public boolean phoneNumExists(@RequestParam(value = "phoneNum", required = false) String phoneNum){
        int phoneExists = service.getPhoneExists(phoneNum);
        if (phoneExists <= 0) {
            return true;
        }else{
            return false;
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<String> join(@ModelAttribute("form") @Valid User user, UserManage userManage, UserRegistrationException exception){
        int success = service.insertUser(user, userManage);
        if(success == 1){
            log.info("user insert success!");
            return ResponseEntity.status(HttpStatus.CREATED).body("success");
        }else{
            log.error("user insert fail!");
            return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());

        }
    }
}
