package com.vict.vict_new.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserRegistrationException.class)
    public ResponseEntity<String> handlerUserRegistrationException(UserRegistrationException ex){
        return ResponseEntity
                .status(ex.getStatus())
                .body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex){
        return ResponseEntity
                .status(500) //일반 예외는 500 Internal Server Error 처리
                .body("An unexpected error occurred"+ ex.getMessage()); //예기치 않은 오류가 발생하였습니다.
    }
}
