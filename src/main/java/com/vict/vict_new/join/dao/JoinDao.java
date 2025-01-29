package com.vict.vict_new.join.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JoinDao {

    int getEmailExist(String email);

}
