package com.vict.vict_new.inquiry.dao;

import com.vict.vict_new.inquiry.dto.InquiryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InquiryDao {
    String getTest(InquiryDto dto);

    List<InquiryDto> selectInquiryList(int userKey);

    InquiryDto selectInquiry(int inquirySeq);

    int insertInquiry(InquiryDto dto);

    int updateInquiry(InquiryDto dto);

    int getInquirySeq();
}
