package com.aorun.epoint.service.impl;

import com.aorun.epoint.dao.WorkerEpointRecordMapper;
import com.aorun.epoint.model.WorkerEpointRecord;
import com.aorun.epoint.service.WorkerEpointRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 积分记录
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Service
public class WorkerEpointRecordServiceImpl implements WorkerEpointRecordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerEpointRecordServiceImpl.class);

    @Autowired
    private WorkerEpointRecordMapper workerEpointRecordMapper;


    @Override
    public WorkerEpointRecord findWorkerEpointRecordById(Long id) {
        return workerEpointRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveWorkerEpointRecord(WorkerEpointRecord workerEpointRecord) {
        return workerEpointRecordMapper.insert(workerEpointRecord);
    }

    @Override
    public int updateWorkerEpointRecord(WorkerEpointRecord workerEpointRecord) {
        return workerEpointRecordMapper.updateByPrimaryKeySelective(workerEpointRecord);
    }

    @Override
    public int deleteWorkerEpointRecord(Long id) {
        return workerEpointRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public  List<WorkerEpointRecord>  findUniqueRecord(Long workerId, String epointConfigCode) {
        return workerEpointRecordMapper.findUniqueRecord(workerId,epointConfigCode);
    }

    @Override
    public  List<WorkerEpointRecord>  findTodayUniqueRecord(Long workerId, String epointConfigCode) {
        return workerEpointRecordMapper.findTodayUniqueRecord(workerId,epointConfigCode);
    }

    @Override
    public List<WorkerEpointRecord> findworkerEpointRecordByPage(Long workerId, Integer pageIndex, Integer pageSize) {
        ///** 启始页-位置 */
        Integer start = (pageIndex - 1) * pageSize;
        /** 每页大小  */
        Integer limit = pageSize;
        return workerEpointRecordMapper.findworkerEpointRecordByPage(workerId,start,limit);
    }

    @Override
    public  List<WorkerEpointRecord>  findUniqueRecordByBizUniqueSignCode(Long workerId, String epointConfigCode, String bizUniqueSignCode) {
        return workerEpointRecordMapper.findUniqueRecordByBizUniqueSignCode(workerId,epointConfigCode,bizUniqueSignCode);
    }

    @Override
    public Integer statisticsTotalByType(Integer type, String epointConfigCode) {
        return workerEpointRecordMapper.statisticsTotalByType(type,epointConfigCode);
    }


}
