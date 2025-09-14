package com.vict.vict_new.join.service;

import com.vict.vict_new.join.dto.User;
import com.vict.vict_new.join.dto.UserManage;

public interface JoinService {

    int getEmailExist(String email);

    int getPhoneExists(String phoneNum);

    int insertUser(User user, UserManage userManage);

}
