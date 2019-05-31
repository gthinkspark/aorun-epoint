package com.aorun.epoint.dao;

import com.aorun.epoint.dto.EpointRankDto;
import com.aorun.epoint.model.WorkerEpointDailyRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkerEpointDailyRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WorkerEpointDailyRecord record);

    int insertSelective(WorkerEpointDailyRecord record);

    WorkerEpointDailyRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WorkerEpointDailyRecord record);

    int updateByPrimaryKey(WorkerEpointDailyRecord record);

    //查询当天有无积分统计信息
    WorkerEpointDailyRecord findTodayRecordByWorkerIdAndStatisticsType(@Param("workerId") Long workerId,@Param("statisticsType") Integer statisticsType);

    //7日积分排行
    List<WorkerEpointDailyRecord> senvenDayTotalEpointRange(@Param("workerId") Long workerId);

    //近7日获得按分类积分
    Long senvenDayTotalEpointByStatisticsType(@Param("workerId") Long workerId,@Param("statisticsType") Integer statisticsType);

    //累计获得按分类总积分
    Long allTotalEpointByStatisticsType(@Param("workerId") Long workerId,@Param("statisticsType") Integer statisticsType);

    //周排名
    List<EpointRankDto> weekRank();

    //总排名
    List<EpointRankDto>  totalRank();

    List<EpointRankDto> weekRankByPage(@Param("start") Integer start, @Param("limit") Integer limit);

    List<EpointRankDto> totalRankByPage(@Param("start") Integer start, @Param("limit") Integer limit);

}