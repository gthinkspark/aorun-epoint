package com.aorun.epoint.dao;

import com.aorun.epoint.model.WorkerMsgProduceRecord;

public interface WorkerMsgProduceRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkerMsgProduceRecord record);

    int insertSelective(WorkerMsgProduceRecord record);

    WorkerMsgProduceRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerMsgProduceRecord record);

    int updateByPrimaryKey(WorkerMsgProduceRecord record);
}