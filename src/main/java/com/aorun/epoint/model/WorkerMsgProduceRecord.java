package com.aorun.epoint.model;

public class WorkerMsgProduceRecord {
    private Long id;

    private Long workerId;

    private String msgUniqueId;

    private Integer msgType;

    private String epointConfigCode;

    private String otherParam;

    private String bizUniqueSignCode;

    private Integer status;

    private String remark;

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

    public String getMsgUniqueId() {
        return msgUniqueId;
    }

    public void setMsgUniqueId(String msgUniqueId) {
        this.msgUniqueId = msgUniqueId == null ? null : msgUniqueId.trim();
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getEpointConfigCode() {
        return epointConfigCode;
    }

    public void setEpointConfigCode(String epointConfigCode) {
        this.epointConfigCode = epointConfigCode == null ? null : epointConfigCode.trim();
    }

    public String getOtherParam() {
        return otherParam;
    }

    public void setOtherParam(String otherParam) {
        this.otherParam = otherParam == null ? null : otherParam.trim();
    }

    public String getBizUniqueSignCode() {
        return bizUniqueSignCode;
    }

    public void setBizUniqueSignCode(String bizUniqueSignCode) {
        this.bizUniqueSignCode = bizUniqueSignCode == null ? null : bizUniqueSignCode.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}