package com.aorun.epoint.service.impl;

import com.aorun.epoint.dao.WorkerEpointLevelMapper;
import com.aorun.epoint.model.WorkerEpointLevel;
import com.aorun.epoint.service.WorkerEpointLevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 积分等级
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Service
public class WorkerEpointLevelServiceImpl implements WorkerEpointLevelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerEpointLevelServiceImpl.class);

    @Autowired
    private WorkerEpointLevelMapper workerEpointLevelMapper;


    @Override
    public WorkerEpointLevel findWorkerEpointLevelById(Long id) {
        return workerEpointLevelMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveWorkerEpointLevel(WorkerEpointLevel workerEpointLevel) {
        return workerEpointLevelMapper.insert(workerEpointLevel);
    }

    @Override
    public int updateWorkerEpointLevel(WorkerEpointLevel workerEpointLevel) {
        return workerEpointLevelMapper.updateByPrimaryKeySelective(workerEpointLevel);
    }

    @Override
    public int deleteWorkerEpointLevel(Long id) {
        return workerEpointLevelMapper.deleteByPrimaryKey(id);
    }

    @Override
    public WorkerEpointLevel getRangeEpointLevel(Integer totalScore) {
        return workerEpointLevelMapper.getRangeEpointLevel(totalScore);
    }


}
