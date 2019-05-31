package com.aorun.epoint.dao;

import com.aorun.epoint.model.WorkerEpointLevel;
import org.apache.ibatis.annotations.Param;

public interface WorkerEpointLevelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkerEpointLevel record);

    int insertSelective(WorkerEpointLevel record);

    WorkerEpointLevel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerEpointLevel record);

    int updateByPrimaryKey(WorkerEpointLevel record);

    WorkerEpointLevel getRangeEpointLevel(@Param("totalScore") Integer totalScore);
}