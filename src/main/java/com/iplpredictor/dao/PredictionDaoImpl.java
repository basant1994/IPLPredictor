package com.iplpredictor.dao;

import com.iplpredictor.mapper.MatchMapper;
import com.iplpredictor.model.Match;
import com.iplpredictor.model.PredictionResult;
import com.iplpredictor.model.Schedule;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PredictionDaoImpl implements PredictionDao {

    private NamedParameterJdbcTemplate template;

    public PredictionDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public PredictionResult predictResult(String teamName) {
        return null;
    }

    @Override
    public Schedule getSchedule(long startTime) {
        Map<String, Object> params  = new HashMap<>();
        params.put("dayNumber", 2);
        List<Match> matches = template.query("select * from matches where dayNumber > :dayNumber", params, new MatchMapper());
        Schedule sch = new Schedule();
        sch.setMatchList(matches);
        return sch;
    }
}
