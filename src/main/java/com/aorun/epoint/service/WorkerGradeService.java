package com.aorun.epoint.service;


import com.aorun.epoint.dto.EpointStatisticsDto;
import com.aorun.epoint.model.WorkerGrade;

import java.util.List;

/**
 * 我的成绩
 * <p>
 * Created by bysocket on 07/02/2017.
 */
public interface WorkerGradeService {

    /**
     * 根据 ID,查询信息
     *
     * @param id
     * @return
     */
    WorkerGrade findWorkerGradeById(Long id);

    /**
     * 新增信息
     *
     * @param workerGrade
     * @return
     */
    int saveWorkerGrade(WorkerGrade workerGrade);

    /**
     * 更新信息
     *
     * @param workerGrade
     * @return
     */
    int updateWorkerGrade(WorkerGrade workerGrade);

    /**
     * 根据 ID,删除信息
     *
     * @param id
     * @return
     */
    int deleteWorkerGrade(Long id);


    List<WorkerGrade> getWorkerGradeListByWorkerId(Long workerId, Integer gradeTypeId, Integer pageIndex, Integer pageSize);


    EpointStatisticsDto getEpointStatistics(Long gradeTypeId);

    int getTotalByCertificateCode(String certificateCode);



}
