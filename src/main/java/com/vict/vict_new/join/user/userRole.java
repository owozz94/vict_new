package com.vict.vict_new.join.user;

public enum userRole {
    ADMIN,
    USER;

    public String getDisplayName(){
        switch (this){
            case ADMIN -> {
                return "관리자";
            }
            case USER -> {
                return "일반 사용자";
            }
            default -> {
                return "알 수 없음";
            }
        }
    }
}
