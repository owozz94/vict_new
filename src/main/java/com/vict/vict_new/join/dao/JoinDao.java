package com.vict.vict_new.join.dao;

import com.vict.vict_new.join.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JoinDao {

    int getEmailExist(String email);

    int insertUser(User user);

    int getUserSeq();
}
