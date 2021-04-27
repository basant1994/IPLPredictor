package com.iplpredictor.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.iplpredictor.dao.PredictionDao;
import com.iplpredictor.model.*;
import com.iplpredictor.util.PredictionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PredictionServiceImpl implements PredictionService {

    @Autowired
    private PredictionDao predictionDao;

    @Autowired
    private PredictionResultCacheManager predictionResultCacheManager;

    @Autowired
    private Jedis jedis;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public PredictionResult predictResult(int teamId) {
        try {
            String key = getTodayKeyWithTeamId(teamId);
            //PredictionResult result = predictionResultCacheManager.getCachedPredictionResult(key);
            String resultStr = jedis.get(key);
            ///PredictionResult result = null;
            if (Objects.nonNull(resultStr)) {
                PredictionResult result = objectMapper.readValue(resultStr, PredictionResult.class);
                return result;
            } else {
                List<Match> allMatches = this.predictionDao.getAllMatches();
                Match[] matchArray = Arrays.copyOf(allMatches.toArray(), 56, Match[].class);
                PredictionUtil predictionUtil = new PredictionUtil(matchArray);
                PredictionResult predictionResult = predictionUtil.predict(teamId);
                this.predictionDao.updatePredictionCount(teamId);
                String str = objectMapper.writeValueAsString(predictionResult);
                jedis.set(key, str);
                //predictionResultCacheManager.cachePredictionResult(key, predictionResult);
                return predictionResult;
            }
        }
        catch (Exception e) {
            log.error("exception ...", e);
        }
        return new PredictionResult();
    }

    @Override
    public Schedule getSchedule(long startTime) {
        return predictionDao.getSchedule(startTime);
    }

    @Override
    public Map<Integer, Object> getPredictionCounts() {
        List<Map<String, Object>> predictionCounts = predictionDao.getPredictionCounts();
        Map<Integer, Object> teamPredictionCount = predictionCounts.stream()
                .collect(Collectors.toMap(e -> (int) e.get("id"), e -> e.get("count")));
        return teamPredictionCount;
    }

    public List<Match> getNextMatch() {
        long millis = System.currentTimeMillis();
        Date startOfIPL = new Date(1617980442000L);

        Date utcTimeStamp = new Date(millis);
        //Date indianTimeStamp = DateUtils.addMinutes(utcTimeStamp, 330);
        long diffInMillies = Math.abs(utcTimeStamp.getTime() - startOfIPL.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) + 1;
        log.info("Diff: " + diff);

        return predictionDao.getNextMatch(diff);
    }

    @Override
    public boolean poll(int matchId, int teamId) {
        return this.predictionDao.updatePoll(matchId, teamId);
    }

    @Override
    public MatchPoll getPollData(int matchId) {
        return this.predictionDao.getPollData(matchId);
    }

    @Override
    public List<Match> getAllMatches() {
        return this.predictionDao.getAllMatches();
    }

    @Override
    public PointsTable getPointsTable() {
        return this.predictionDao.getPointsTable();
    }


    private String getTodayKeyWithTeamId(int teamId) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String date = df.format(new Date());
        return teamId + "_" + date;
    }

}
