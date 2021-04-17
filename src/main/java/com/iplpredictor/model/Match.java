package com.iplpredictor.model;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
public class Match {
    private int matchId;
    private int oppositionA;
    private int oppositionB;
    private int dayNumber;
    private int result;
    private boolean matchOver;
}
