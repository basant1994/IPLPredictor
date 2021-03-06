package com.iplpredictor.mapper;

import com.iplpredictor.model.Match;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MatchMapper implements RowMapper<Match> {

    @Override
    public Match mapRow(ResultSet rs, int arg1) throws SQLException {
        Match match = new Match();
        match.setId(rs.getInt("id"));
        match.setDayNumber(rs.getInt("dayNumber"));
        match.setOppositionA(rs.getInt("oppositionA"));
        match.setOppositionB(rs.getInt("oppositionB"));
        match.setWinnerId(rs.getInt("winnerId"));
        match.setEndResultAvailable(rs.getBoolean("endResultAvailable"));
        match.setMatchOver(rs.getBoolean("matchOver"));
        return match;
    }
}