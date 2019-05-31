package com.aorun.epoint.model;

import java.util.Date;

public class WorkerGrade {
    private Long id;

    private Long workerId;

    private String name;

    private String telephone;

    private String idCard;

    private String companyName;

    private String bankCard;

    private String certificateCode;

    private String certificateName;

    private Integer obtainScore;

    private String materialsUrls;

    private Integer status;

    private String failReason;

    private Date createTime;

    private Date updateTime;

    private Integer isReaded;

    private Integer gradeTypeId;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard == null ? null : bankCard.trim();
    }

    public String getCertificateCode() {
        return certificateCode;
    }

    public void setCertificateCode(String certificateCode) {
        this.certificateCode = certificateCode == null ? null : certificateCode.trim();
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName == null ? null : certificateName.trim();
    }

    public Integer getObtainScore() {
        return obtainScore;
    }

    public void setObtainScore(Integer obtainScore) {
        this.obtainScore = obtainScore;
    }

    public String getMaterialsUrls() {
        return materialsUrls;
    }

    public void setMaterialsUrls(String materialsUrls) {
        this.materialsUrls = materialsUrls;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason == null ? null : failReason.trim();
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

    public Integer getIsReaded() {
        return isReaded;
    }

    public void setIsReaded(Integer isReaded) {
        this.isReaded = isReaded;
    }

    public Integer getGradeTypeId() {
        return gradeTypeId;
    }

    public void setGradeTypeId(Integer gradeTypeId) {
        this.gradeTypeId = gradeTypeId;
    }
}