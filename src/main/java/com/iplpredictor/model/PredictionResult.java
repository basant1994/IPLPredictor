package com.iplpredictor.model;

import lombok.Data;

import java.util.List;

@Data
public class PredictionResult {
    List<MatchResult> result;
}
