package com.vict.vict_new.join.dao;

import com.vict.vict_new.join.dto.User;
import com.vict.vict_new.join.dto.UserManage;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JoinDao {

    int getEmailExist(String email);

    int insertUser(User user);

    int getUserSeq();

    int getAccountCount(int userSeq);

    int insertUserManage(UserManage userManage);
}
