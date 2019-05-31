package com.aorun.epoint.service;

import com.aorun.epoint.model.WorkerEpointConfig;

import java.util.List;

/**
 * 积分规则
 * Created by duxihu on 2019/5/14 0014.
 */
public interface WorkerEpointConfigService {

    List<WorkerEpointConfig> findEpointConfigListByBizType(Integer bizType);

    List<WorkerEpointConfig> findEpointConfigListByParentId(Integer parentId);

    /**
     * 根据 code,查询信息
     *
     * @param code
     * @return
     */
    WorkerEpointConfig findEpointConfigByCode(String code);

}
