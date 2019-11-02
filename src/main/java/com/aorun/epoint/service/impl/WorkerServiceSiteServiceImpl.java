package com.aorun.epoint.service.impl;

import com.aorun.epoint.dao.WorkerServiceSiteMapper;
import com.aorun.epoint.model.WorkerServiceSite;
import com.aorun.epoint.service.WorkerServiceSiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务站点
 */
@Service
public class WorkerServiceSiteServiceImpl implements WorkerServiceSiteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerServiceSiteServiceImpl.class);

    @Autowired
    private WorkerServiceSiteMapper workerServiceSiteMapper;

    @Override
    public int saveWorkerServiceSite(WorkerServiceSite workerServiceSite) {
        return workerServiceSiteMapper.insert(workerServiceSite);
    }

    @Override
    public int updateWorkerServiceSite(WorkerServiceSite workerServiceSite) {
        return workerServiceSiteMapper.updateByPrimaryKeySelective(workerServiceSite);
    }

    @Override
    public int deleteWorkerServiceSite(Long id) {
        return workerServiceSiteMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<WorkerServiceSite> getParentSiteList() {
        return workerServiceSiteMapper.getParentSiteList();
    }

    @Override
    public List<WorkerServiceSite> getWorkerServiceSiteListByParentId(Long parentId) {
        return workerServiceSiteMapper.getWorkerServiceSiteListByParentId(parentId);
    }

    @Override
    public int totalWorkerServiceSite() {
        return workerServiceSiteMapper.totalWorkerServiceSite();
    }

}
