package com.vict.vict_new.join.service.serviceImpl;

import com.vict.vict_new.join.dao.JoinDao;
import com.vict.vict_new.join.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService {

    private final JoinDao dao;

    @Override
    public int getEmailExist(String email) {
        return dao.getEmailExist(email);
    }
}
