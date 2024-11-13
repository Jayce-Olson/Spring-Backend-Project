package edu.wgu.d387_sample_code.controllers;

import edu.wgu.d387_sample_code.Services.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TimeController {
    @Autowired
    private TimeService timeService;

    @GetMapping("/times")
    public String[] times(){
        return new String[] {timeService.getEastern(), timeService.getMountain(), timeService.getUTC()};
    }
}
