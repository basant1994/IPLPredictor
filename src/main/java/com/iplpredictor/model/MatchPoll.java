package com.iplpredictor.model;

import lombok.Data;

@Data
public class MatchPoll {
    private int matchId;
    private long oppositionACount;
    private long oppositionBCount;
}
