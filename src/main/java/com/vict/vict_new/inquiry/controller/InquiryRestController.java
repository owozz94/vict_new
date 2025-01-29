package com.vict.vict_new.inquiry.controller;

import com.vict.vict_new.inquiry.dto.InquiryDto;
import com.vict.vict_new.inquiry.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/inquiry")
public class InquiryRestController {
    private final InquiryService service;

    @PostMapping("/test")
    public String Test(InquiryDto dto){
        String test = service.getTest(dto);
        System.out.println(test);
        return test;
    }

    @PostMapping("/insert")
    public String Inquiry(@ModelAttribute("form") InquiryDto dto, RedirectAttributes redirectAttributes){
        int result = service.insertInquiry(dto);
        if(result < 0){
            redirectAttributes.addFlashAttribute("message", "등록에 실패하였습니다.");
            return "redirect:inquiry/inquiryForm";
        }
        return "inquiry/InquiryList";
    }

    @PostMapping("/update")
    public String Update(InquiryDto dto, RedirectAttributes redirectAttributes){
        int result = service.updateInquiry(dto);

        return "redirect:inquiry/inquiryList";
    }
}
