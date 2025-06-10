package com.vict.vict_new.exception;

import org.springframework.http.HttpStatus;

public class UserRegistrationException extends RuntimeException{
    private final HttpStatus status;

    private UserRegistrationException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }
    public HttpStatus getStatus(){
        return status;
    }
    //예외 유형별 팩토리 매서드
    public static UserRegistrationException invalidPasswordFormat(String password){
        return new UserRegistrationException(
                "Password" + password + "is in an invalid format", HttpStatus.BAD_REQUEST
        );
    }
    public static UserRegistrationException emailAlreadyExists(String email){
        return new UserRegistrationException(
                "Email"+email + "already exists.", HttpStatus.CONFLICT
        );
    }
    public static UserRegistrationException certificationFailed(){
        return new UserRegistrationException(
                "SMS certification failed.", HttpStatus.UNAUTHORIZED
        );
    }
    public static UserRegistrationException sendSMSCodeFailed(){
        return new UserRegistrationException(
                "SMS code send failed.", HttpStatus.UNAUTHORIZED
        );
    }
    public static UserRegistrationException userAlreadyExists() {
        return new UserRegistrationException(
                "User already exists.", HttpStatus.CONFLICT
        );
    }
    public static UserRegistrationException accountLimitExceeded(){
        return new UserRegistrationException(
                "No more accounts can be registered", HttpStatus.CONFLICT
        );
    }
    public static UserRegistrationException signupFailed(){
        return new UserRegistrationException(
                "signup Failed", HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
