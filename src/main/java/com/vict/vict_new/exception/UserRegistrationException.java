package com.vict.vict_new.exception;

import com.vict.vict_new.join.dto.User;
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
                "Email already exists. => "+email, HttpStatus.CONFLICT
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
    public static UserRegistrationException userPhoneNumAlreadyExists(String phoneNum){
        return new UserRegistrationException(
                "PhoneNum already exists. =>"+phoneNum, HttpStatus.CONFLICT
        );
    }
    public static UserRegistrationException userEmailAlreadyExists(String email) {
        return new UserRegistrationException(
                "Email already exists. => "+email, HttpStatus.CONFLICT
        );
    }

    public static UserRegistrationException userAlreadyExists(User user) {
        return new UserRegistrationException(
                "User already exists. user seq : "+user.getUserKey()
                +"\n name :"+user.getUserName()+"\n email :" + user.getEmail(), HttpStatus.CONFLICT
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
