package com.aorun.epoint.dto;

import io.swagger.annotations.ApiModelProperty;

public class EpointRankDto {

    private Long workerId;

    @ApiModelProperty("总分数")
    private Integer totalScore;

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }
}