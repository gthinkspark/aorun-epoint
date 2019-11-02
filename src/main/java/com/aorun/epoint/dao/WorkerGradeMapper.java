package com.aorun.epoint.dao;

import com.aorun.epoint.dto.EpointStatisticsDto;
import com.aorun.epoint.model.WorkerGrade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkerGradeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkerGrade record);

    int insertSelective(WorkerGrade record);

    WorkerGrade selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerGrade record);

    int updateByPrimaryKey(WorkerGrade record);


    List<WorkerGrade> getWorkerGradeListByWorkerId(@Param("workerId") Long workerId,@Param("gradeTypeId") Integer gradeTypeId,  @Param("start") Integer start, @Param("limit") Integer limit);

    EpointStatisticsDto getEpointStatistics(@Param("gradeTypeId") Long gradeTypeId);

    int getTotalByCertificateCode(@Param("certificateCode") String certificateCode);


}