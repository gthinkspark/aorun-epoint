package com.aorun.epoint.dto;

import io.swagger.annotations.ApiModelProperty;

public class EpointDailyStatisticsDto {

    //日期
    private String scoreDate;

    @ApiModelProperty("总分数")
    private Integer totalScore;


    public String getScoreDate() {
        return scoreDate;
    }

    public void setScoreDate(String scoreDate) {
        this.scoreDate = scoreDate;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }
}