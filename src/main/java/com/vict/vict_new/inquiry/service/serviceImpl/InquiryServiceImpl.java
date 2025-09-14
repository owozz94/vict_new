package com.vict.vict_new.inquiry.service.serviceImpl;

import com.vict.vict_new.inquiry.dao.InquiryDao;
import com.vict.vict_new.inquiry.dto.InquiryDto;
import com.vict.vict_new.inquiry.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {

    private final InquiryDao dao;

    @Override
    public String getTest(InquiryDto dto) {
        return dao.getTest(dto);
    }

    @Override
    public List<InquiryDto> selectInquiryList(int userKey) {
        userKey = 1;  //임시 시퀀스
        return dao.selectInquiryList(userKey);
    }

    @Override
    public InquiryDto selectInquiry(int inquirySeq) {
        return dao.selectInquiry(inquirySeq);
    }

    @Override
    public int insertInquiry(InquiryDto dto) {
        //테이블 시퀀스 채번
        int inquirySeq = dao.getInquirySeq();
            inquirySeq++;

        dto.setInquirySeq(inquirySeq);
        String content = dto.getContent();
        content.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");

        return dao.insertInquiry(dto);
    }

    @Override
    public int updateInquiry(InquiryDto dto) {
        int updateResult = dao.updateInquiry(dto);
        return updateResult;
    }
}