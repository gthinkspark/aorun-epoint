package com.aorun.epoint.model;

import java.util.Date;

public class WorkerEpointConfig {
    private Long id;

    private String code;

    private String name;

    private Integer parentId;

    private Integer score;

    private String configExplain;

    private Integer bizType;

    private Integer obtainScoreRate;

    private Integer statisticsType;

    private String isShow;

    private String otherSysDecideEpoint;

    private String isDeleted;

    private String epointRecordTitle;

    private String simpleName;

    private String simpleEpointExplain;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getConfigExplain() {
        return configExplain;
    }

    public void setConfigExplain(String configExplain) {
        this.configExplain = configExplain == null ? null : configExplain.trim();
    }

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public Integer getObtainScoreRate() {
        return obtainScoreRate;
    }

    public void setObtainScoreRate(Integer obtainScoreRate) {
        this.obtainScoreRate = obtainScoreRate;
    }

    public Integer getStatisticsType() {
        return statisticsType;
    }

    public void setStatisticsType(Integer statisticsType) {
        this.statisticsType = statisticsType;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow == null ? null : isShow.trim();
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    public String getEpointRecordTitle() {
        return epointRecordTitle;
    }

    public void setEpointRecordTitle(String epointRecordTitle) {
        this.epointRecordTitle = epointRecordTitle == null ? null : epointRecordTitle.trim();
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName == null ? null : simpleName.trim();
    }

    public String getSimpleEpointExplain() {
        return simpleEpointExplain;
    }

    public void setSimpleEpointExplain(String simpleEpointExplain) {
        this.simpleEpointExplain = simpleEpointExplain == null ? null : simpleEpointExplain.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOtherSysDecideEpoint() {
        return otherSysDecideEpoint;
    }

    public void setOtherSysDecideEpoint(String otherSysDecideEpoint) {
        this.otherSysDecideEpoint = otherSysDecideEpoint;
    }
}