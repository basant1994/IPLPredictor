package com.iplpredictor.controller;

import com.iplpredictor.model.*;
import com.iplpredictor.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        return predictionService.getSchedule(System.currentTimeMillis());

    }

    @RequestMapping(method = RequestMethod.POST, path = "/predictPlayOff")
    public PredictionResult predict(@RequestBody Map<String, Object> team) {
        String teamId = team.get("teamId").toString();
        PredictionResult result = predictionService.predictResult(teamId);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/teamPredictionCounts")
    public Map<String, Object> getPredictionCounts() {
        return predictionService.getPredictionCounts();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/nextMatch")
    public List<Match> getNextMatch() {
        return predictionService.getNextMatch();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/poll")
    public boolean poll(@RequestBody Map<String, Object> pollData) {
        int matchId = (int) pollData.get("matchId");
        int teamId = (int) pollData.get("teamId");

        return this.predictionService.poll(matchId, teamId);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/poll")
    public MatchPoll getMatchPoll(@RequestBody Map<String, Object> pollData) {
        int matchId = (int) pollData.get("matchId");
        return this.predictionService.getPollData(matchId);
    }
}
