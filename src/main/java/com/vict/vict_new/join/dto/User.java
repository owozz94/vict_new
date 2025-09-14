package com.vict.vict_new.join.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {
    String userKey; /** 유저 시퀀스 */
    @NotBlank(message = "이메일은 필수입니다.")
    String email; /** 이메일 */
    @NotBlank(message = "닉네임은 필수입니다.")
    String nickname; /** 닉네임 */
    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min=8, max =20, message = "비밀전호는 8자 이상, 20자 이하이어야 합니다.")
    String password; /** 비밀번호 */
    String userName; /** 이름  */
    String birthday; /**생년월일 */
    int gender; /**성별 1:남 2:여 */
    @NotBlank(message = "핸드폰번호는 필수입니다.")
    String phoneNum; /** 핸드폰번호 */
    String blogUrl; /** 블로그 주소 */
    Date regtDate; /** 회원가입 날짜 */
    Date endDate; /** 탈퇴 날짜 */
    Date lastUpdateTime; /** 마지막 수정 시간 */
    Date lastLoginTime; /** 마지막 로그인 시간 */
    String useYn; /** 사용 여부 */
    int loginType; /** 로그인 타입 1:일반가입 2:네이버가입 */
//    String socialKey; /** 소셜 로그인 키 */
}
