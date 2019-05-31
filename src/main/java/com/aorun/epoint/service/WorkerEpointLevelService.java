package com.aorun.epoint.service;


import com.aorun.epoint.model.WorkerEpointLevel;

/**
 * 我的成绩
 * <p>
 * Created by bysocket on 07/02/2017.
 */
public interface WorkerEpointLevelService {

    /**
     * 根据 ID,查询信息
     *
     * @param id
     * @return
     */
    WorkerEpointLevel findWorkerEpointLevelById(Long id);

    /**
     * 新增信息
     *
     * @param workerEpointLevel
     * @return
     */
    int saveWorkerEpointLevel(WorkerEpointLevel workerEpointLevel);

    /**
     * 更新信息
     *
     * @param workerEpointLevel
     * @return
     */
    int updateWorkerEpointLevel(WorkerEpointLevel workerEpointLevel);

    /**
     * 根据 ID,删除信息
     *
     * @param id
     * @return
     */
    int deleteWorkerEpointLevel(Long id);


    WorkerEpointLevel getRangeEpointLevel(Integer totalScore);
    
}
