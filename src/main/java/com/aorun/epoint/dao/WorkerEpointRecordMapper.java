package com.aorun.epoint.dao;

import com.aorun.epoint.model.WorkerEpointRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface WorkerEpointRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkerEpointRecord record);

    int insertSelective(WorkerEpointRecord record);

    WorkerEpointRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerEpointRecord record);

    int updateByPrimaryKey(WorkerEpointRecord record);

    List<WorkerEpointRecord>  findUniqueRecord(@Param("workerId") Long workerId, @Param("epointConfigCode")String epointConfigCode);

    List<WorkerEpointRecord>  findUniqueRecord30Day(@Param("workerId") Long workerId, @Param("epointConfigCode")String epointConfigCode, @Param("starTime") Date starTime);

    List<WorkerEpointRecord>  findTodayUniqueRecord(@Param("workerId") Long workerId, @Param("epointConfigCode")String epointConfigCode);

    List<WorkerEpointRecord> findworkerEpointRecordByPage(@Param("workerId") Long workerId,@Param("start") Integer start, @Param("limit") Integer limit);


    List<WorkerEpointRecord>  findUniqueRecordByBizUniqueSignCode(@Param("workerId") Long workerId, @Param("epointConfigCode")String epointConfigCode,@Param("bizUniqueSignCode") String bizUniqueSignCode);

    Integer statisticsTotalByType(@Param("type")Integer type,@Param("epointConfigCode") String epointConfigCode);


}