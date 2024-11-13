package edu.wgu.d387_sample_code.controllers;

import edu.wgu.d387_sample_code.Services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WelcomeController {

    @Autowired
    LanguageService languageService;

    @GetMapping("/welcome")
    public String[] welcome() {
        // This will call a multithreaded method that will return a string array
        return languageService.getWelcomeMessages();
    }
}
