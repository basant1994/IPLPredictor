package com.iplpredictor.mapper;

import com.iplpredictor.model.MatchPoll;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MatchPollMapper implements RowMapper<MatchPoll> {

    @Override
    public MatchPoll mapRow(ResultSet rs, int arg1) throws SQLException {
        MatchPoll matchPoll = new MatchPoll();
        matchPoll.setMatchId(rs.getInt("matchId"));
        matchPoll.setOppositionACount(rs.getLong("opponentA_count"));
        matchPoll.setOppositionBCount(rs.getLong("opponentB_count"));
        return matchPoll;
    }
}
