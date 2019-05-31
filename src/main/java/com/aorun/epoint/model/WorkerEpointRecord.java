package com.aorun.epoint.model;

import java.util.Date;

public class WorkerEpointRecord {
    private Long id;

    private Long workerId;

    private String title;

    private Integer score;

    private Integer statisticsType;

    private Date obtainTime;

    private Date createTime;

    private String epointConfigCode;

    private String bizUniqueSignCode;

    private String msgId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getObtainTime() {
        return obtainTime;
    }

    public void setObtainTime(Date obtainTime) {
        this.obtainTime = obtainTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEpointConfigCode() {
        return epointConfigCode;
    }

    public void setEpointConfigCode(String epointConfigCode) {
        this.epointConfigCode = epointConfigCode == null ? null : epointConfigCode.trim();
    }

    public String getBizUniqueSignCode() {
        return bizUniqueSignCode;
    }

    public void setBizUniqueSignCode(String bizUniqueSignCode) {
        this.bizUniqueSignCode = bizUniqueSignCode == null ? null : bizUniqueSignCode.trim();
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Integer getStatisticsType() {
        return statisticsType;
    }

    public void setStatisticsType(Integer statisticsType) {
        this.statisticsType = statisticsType;
    }
}