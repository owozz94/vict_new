package com.vict.vict_new.terms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/terms")
public class TermsController {

    @GetMapping("/event")
    public String eventTerms(){

        return "terms/event-terms";
    }

    @GetMapping("/privacy")
    public String privacyPolicyTerms(){

        return "terms/privacy-policy-terms";
    }

    @GetMapping("/service")
    public String serviceTerms(){

        return "terms/service-terms";
    }
}
