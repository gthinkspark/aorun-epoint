package com.aorun.epoint.service;


import com.aorun.epoint.model.WorkerServiceSite;

import java.util.List;

/**
 * 服务站点
 */
public interface WorkerServiceSiteService {


    /**
     * 新增信息
     *
     * @param workerServiceSite
     * @return
     */
    int saveWorkerServiceSite(WorkerServiceSite workerServiceSite);

    /**
     * 更新信息
     *
     * @param workerServiceSite
     * @return
     */
    int updateWorkerServiceSite(WorkerServiceSite workerServiceSite);

    /**
     * 根据 ID,删除信息
     *
     * @param id
     * @return
     */
    int deleteWorkerServiceSite(Long id);


    List<WorkerServiceSite> getParentSiteList();

    List<WorkerServiceSite> getWorkerServiceSiteListByParentId(Long parentId);

    int totalWorkerServiceSite();



}
