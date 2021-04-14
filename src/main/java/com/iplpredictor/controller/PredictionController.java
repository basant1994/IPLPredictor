package com.iplpredictor.controller;

import com.iplpredictor.model.MatchResult;
import com.iplpredictor.model.Schedule;
import com.iplpredictor.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PredictionController {

    @Autowired
    private PredictionService predictionService;

    @RequestMapping(method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/schedule")
    public Schedule getMatches() {
        return predictionService.getSchedule(   System.currentTimeMillis());

    }

    @RequestMapping(method = RequestMethod.GET, path = "/predict")
    public MatchResult predict(@RequestParam String team) {
        return null;
    }
}
