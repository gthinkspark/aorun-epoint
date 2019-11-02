package com.aorun.epoint.dao;

import com.aorun.epoint.model.WorkerServiceSite;

import java.util.List;

public interface WorkerServiceSiteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkerServiceSite record);

    WorkerServiceSite selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerServiceSite record);

    List<WorkerServiceSite> getParentSiteList();

    List<WorkerServiceSite> getWorkerServiceSiteListByParentId(Long parentId);

    int totalWorkerServiceSite();
}