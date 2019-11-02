package com.aorun.epoint.dto;

import io.swagger.annotations.ApiModelProperty;

public class EpointStatisticsDto {

    //申请数量
    private Long applyNum;

    @ApiModelProperty("总分数")
    private Integer totalScore;


    public Long getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(Long applyNum) {
        this.applyNum = applyNum;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }
}