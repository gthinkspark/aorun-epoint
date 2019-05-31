package com.aorun.epoint.dao;

import com.aorun.epoint.model.WorkerEpointConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkerEpointConfigMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkerEpointConfig record);

    int insertSelective(WorkerEpointConfig record);

    WorkerEpointConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerEpointConfig record);

    int updateByPrimaryKey(WorkerEpointConfig record);

    List<WorkerEpointConfig> findEpointConfigListByBizType(@Param("bizType") Integer bizType);

    List<WorkerEpointConfig> findEpointConfigListByParentId(@Param("parentId")Integer parentId);

    WorkerEpointConfig findEpointConfigByCode(String code);

}