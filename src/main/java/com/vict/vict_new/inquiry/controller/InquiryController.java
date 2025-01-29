package com.vict.vict_new.inquiry.controller;

import com.vict.vict_new.inquiry.dto.InquiryDto;
import com.vict.vict_new.inquiry.service.InquiryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/inquiry")
public class InquiryController {
    
    private final InquiryService service;

    @GetMapping("/{seq}")
    public String Inquiry(@PathVariable("seq") int inquirySeq, Model model){
        InquiryDto inquiryDto = service.selectInquiry(inquirySeq);
        model.addAttribute("items", inquiryDto);
        return "inquiry/Inquiry";
    }

    @GetMapping("/write")
    public String InquiryForm(@ModelAttribute("form") InquiryDto dto){
        return "inquiry/InquiryForm";
    }

    @GetMapping("/{seq}/edit")
    public String UpdateForm(@PathVariable("seq") int inquirySeq, Model model){
        InquiryDto inquiryDto = service.selectInquiry(inquirySeq);
        model.addAttribute("items", inquiryDto);
        return "inquiry/InquiryUpdate";
    }

    @GetMapping("/list")
    public String InquiryList(Model model){
        List<InquiryDto> inquiryList = service.selectInquiryList(1);
        model.addAttribute("items", inquiryList);
        return "inquiry/InquiryList";
    }
}
