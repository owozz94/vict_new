package com.vict.vict_new.inquiry.dto;

import com.vict.vict_new.join.dto.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class InquiryDto extends User {
    int inquirySeq; //시퀀스
    int inquiryType; //문의 유형
    String title; //제목
    String content; //내용
    String regtDate; //등록일
    String useYn;  //사용여부
    String questionYn; //답변여부
    String questionDate; //답변일
}
