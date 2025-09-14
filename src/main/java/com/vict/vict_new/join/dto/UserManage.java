package com.vict.vict_new.join.dto;

import com.vict.vict_new.join.user.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserManage {
    String userKey; /** 유저 시퀀스 */
    UserRole role; /** 사용자 권한*/
    String marketingAgreed; /** 마케팅 동의 여부*/
    int accountCount; /** 가입 횟수*/
}
