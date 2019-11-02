package com.aorun.epoint.service.impl;

import com.aorun.epoint.dao.WorkerGradeMapper;
import com.aorun.epoint.dto.EpointStatisticsDto;
import com.aorun.epoint.model.WorkerGrade;
import com.aorun.epoint.service.WorkerGradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 我的成绩
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Service
public class WorkerGradeServiceImpl implements WorkerGradeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerGradeServiceImpl.class);

    @Autowired
    private WorkerGradeMapper workerGradeMapper;


    @Override
    public WorkerGrade findWorkerGradeById(Long id) {
        return workerGradeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveWorkerGrade(WorkerGrade workerGrade) {
        return workerGradeMapper.insert(workerGrade);
    }

    @Override
    public int updateWorkerGrade(WorkerGrade workerGrade) {
        return workerGradeMapper.updateByPrimaryKeySelective(workerGrade);
    }

    @Override
    public int deleteWorkerGrade(Long id) {
        return workerGradeMapper.deleteByPrimaryKey(id);
    }



    @Override
    public List<WorkerGrade> getWorkerGradeListByWorkerId(Long workerId, Integer gradeTypeId, Integer pageIndex, Integer pageSize) {
        ///** 启始页-位置 */
        Integer start = (pageIndex - 1) * pageSize;
        /** 每页大小  */
        Integer limit = pageSize;
        return workerGradeMapper.getWorkerGradeListByWorkerId(workerId,gradeTypeId, start, limit);
    }

    @Override
    public EpointStatisticsDto getEpointStatistics(Long gradeTypeId) {
        return workerGradeMapper.getEpointStatistics(gradeTypeId);
    }

    @Override
    public int getTotalByCertificateCode(String certificateCode) {
        return workerGradeMapper.getTotalByCertificateCode(certificateCode);
    }

}
