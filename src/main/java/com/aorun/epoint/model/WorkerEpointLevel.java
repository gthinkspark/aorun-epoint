package com.aorun.epoint.model;

import java.util.Date;

public class WorkerEpointLevel {
    private Long id;

    private String levelImg;

    private String levelName;

    private String levelLabel;

    private Integer scoreBegin;

    private Integer scoreEnd;

    private String levelExplain;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevelImg() {
        return levelImg;
    }

    public void setLevelImg(String levelImg) {
        this.levelImg = levelImg == null ? null : levelImg.trim();
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    public String getLevelLabel() {
        return levelLabel;
    }

    public void setLevelLabel(String levelLabel) {
        this.levelLabel = levelLabel == null ? null : levelLabel.trim();
    }

    public Integer getScoreBegin() {
        return scoreBegin;
    }

    public void setScoreBegin(Integer scoreBegin) {
        this.scoreBegin = scoreBegin;
    }

    public Integer getScoreEnd() {
        return scoreEnd;
    }

    public void setScoreEnd(Integer scoreEnd) {
        this.scoreEnd = scoreEnd;
    }

    public String getLevelExplain() {
        return levelExplain;
    }

    public void setLevelExplain(String levelExplain) {
        this.levelExplain = levelExplain == null ? null : levelExplain.trim();
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
}