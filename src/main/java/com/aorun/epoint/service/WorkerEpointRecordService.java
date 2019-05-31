package com.aorun.epoint.service;


import com.aorun.epoint.model.WorkerEpointRecord;

import java.util.List;

/**
 * 我的成绩
 * <p>
 * Created by bysocket on 07/02/2017.
 */
public interface WorkerEpointRecordService {

    /**
     * 根据 ID,查询信息
     *
     * @param id
     * @return
     */
    WorkerEpointRecord findWorkerEpointRecordById(Long id);

    /**
     * 新增信息
     *
     * @param workerEpointRecord
     * @return
     */
    int saveWorkerEpointRecord(WorkerEpointRecord workerEpointRecord);

    /**
     * 更新信息
     *
     * @param workerEpointRecord
     * @return
     */
    int updateWorkerEpointRecord(WorkerEpointRecord workerEpointRecord);

    /**
     * 根据 ID,删除信息
     *
     * @param id
     * @return
     */
    int deleteWorkerEpointRecord(Long id);


    //查找工会会员ID某个epointConfigCode是否添加过
    List<WorkerEpointRecord>  findUniqueRecord(Long workerId,String epointConfigCode);

    //查找工会会员ID某个epointConfigCode当天是否添加过
    List<WorkerEpointRecord> findTodayUniqueRecord(Long workerId,String epointConfigCode);


    List<WorkerEpointRecord> findworkerEpointRecordByPage(Long workerId, Integer pageIndex, Integer pageSize);

    //查找工会会员ID,epointConfigCode,bizUniqueSignCode查找唯一记录
    List<WorkerEpointRecord>  findUniqueRecordByBizUniqueSignCode(Long workerId,String epointConfigCode,String bizUniqueSignCode);



}
