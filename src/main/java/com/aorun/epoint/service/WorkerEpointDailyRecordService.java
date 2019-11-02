package com.aorun.epoint.service;


import com.aorun.epoint.dto.EpointDailyStatisticsDto;
import com.aorun.epoint.dto.EpointRankDto;
import com.aorun.epoint.model.WorkerEpointDailyRecord;

import java.util.List;

/**
 * 我的成绩
 * <p>
 * Created by bysocket on 07/02/2017.
 */
public interface WorkerEpointDailyRecordService {

    /**
     * 根据 ID,查询信息
     *
     * @param id
     * @return
     */
    WorkerEpointDailyRecord findWorkerEpointDailyRecordById(Long id);

    /**
     * 新增信息
     *
     * @param workerEpointDailyRecord
     * @return
     */
    int saveWorkerEpointDailyRecord(WorkerEpointDailyRecord workerEpointDailyRecord);

    /**
     * 更新信息
     *
     * @param workerEpointDailyRecord
     * @return
     */
    int updateWorkerEpointDailyRecord(WorkerEpointDailyRecord workerEpointDailyRecord);

    /**
     * 根据 ID,删除信息
     *
     * @param id
     * @return
     */
    int deleteWorkerEpointDailyRecord(Long id);



    /**
     * 根据 workerId,statisticsType查询信息
     *
     * @param workerId
     * @param statisticsType
     * @return
     */
    WorkerEpointDailyRecord findTodayRecordByWorkerIdAndStatisticsType(Long workerId,Integer statisticsType);

    //7日积分排行
    List<WorkerEpointDailyRecord> senvenDayTotalEpointRange(Long workerId);

    //近7日获得按分类积分
    Long senvenDayTotalEpointByStatisticsType(Long workerId,Integer statisticsType);

    //累计获得按分类总积分
    Long allTotalEpointByStatisticsType(Long workerId,Integer statisticsType);

    //周排名-所有数据
    List<EpointRankDto> weekRank(String[] idList);

    //总排名-所有数据
    List<EpointRankDto>  totalRank(String[] idList);


    //周排名--分页查询
    List<EpointRankDto> weekRankByPage(Integer pageIndex, Integer pageSize,String[] idList);

    //总排名-分页查询
    List<EpointRankDto>  totalRankByPage(Integer pageIndex, Integer pageSize,String[] idList);

    List<EpointDailyStatisticsDto>  totalEpointDailyStatistics();

    Integer getTotalEpointDailyStatisticByScoreDate(String scoreDate);


}
