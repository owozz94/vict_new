package com.vict.vict_new.inquiry.service;

import com.vict.vict_new.inquiry.dto.InquiryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InquiryService {
    String getTest(InquiryDto dto);

    List<InquiryDto> selectInquiryList(int userKey);

    InquiryDto selectInquiry(int InquirySeq);

    int insertInquiry(InquiryDto dto);

    int updateInquiry(InquiryDto dto);

}
