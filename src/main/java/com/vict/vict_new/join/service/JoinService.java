package com.vict.vict_new.join.service;

import com.vict.vict_new.join.dto.User;

public interface JoinService {

    int getEmailExist(String email);

    int insertUser(User user);

}
