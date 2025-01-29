package com.vict.vict_new.join.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {
    String userSeq; /** 유저 시퀀스 */
    String userType; /** 회원:1, 비회원:2 */
    String email; /** 이메일 */
    String nickname; /** 닉네임 */
    String password; /** 비밀번호 */
    String userName; /** 이름  */
    String birthday; /**생년월일 */
    int gender; /**성별 1:남 2:여 */
    String phoneNum; /** 핸드폰번호 */
    String blogUrl; /** 블로그 주소 */
    Date regDate; /** 회원가입 날짜 */
    Date endDate; /** 탈퇴 날짜 */
    Date lastLoginTime; /** 마지막 로그인 시간 */
    String useYn; /** 사용 여부 */
    int loginType; /** 로그인 타입 1:일반가입 2:네이버가입 */
    String socialKey; /** 소셜 로그인 키 */
    String salt; /** ID SALT 값 */
}
