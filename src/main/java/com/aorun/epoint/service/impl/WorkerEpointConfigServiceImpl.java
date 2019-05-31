package com.aorun.epoint.service.impl;

import com.aorun.epoint.dao.WorkerEpointConfigMapper;
import com.aorun.epoint.model.WorkerEpointConfig;
import com.aorun.epoint.service.WorkerEpointConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 积分规则
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Service
public class WorkerEpointConfigServiceImpl implements WorkerEpointConfigService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerEpointConfigServiceImpl.class);

    @Autowired
    private WorkerEpointConfigMapper workerEpointConfigMapper;


    @Override
    public List<WorkerEpointConfig> findEpointConfigListByBizType(Integer bizType) {
        return workerEpointConfigMapper.findEpointConfigListByBizType(bizType);
    }

    @Override
    public List<WorkerEpointConfig> findEpointConfigListByParentId(Integer parentId) {
        return workerEpointConfigMapper.findEpointConfigListByParentId(parentId);
    }

    @Override
    public WorkerEpointConfig findEpointConfigByCode(String code) {
        return workerEpointConfigMapper.findEpointConfigByCode(code);
    }


}
