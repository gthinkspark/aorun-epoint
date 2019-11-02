package com.aorun.epoint.service.impl;

import com.aorun.epoint.dao.WorkerEpointDailyRecordMapper;
import com.aorun.epoint.dto.EpointDailyStatisticsDto;
import com.aorun.epoint.dto.EpointRankDto;
import com.aorun.epoint.model.WorkerEpointDailyRecord;
import com.aorun.epoint.service.WorkerEpointDailyRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 每日积分汇总记录
 * <p>
 * Created by bysocket on 07/02/2017.
 */
@Service
public class WorkerEpointDailyRecordServiceImpl implements WorkerEpointDailyRecordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerEpointDailyRecordServiceImpl.class);

    @Autowired
    private WorkerEpointDailyRecordMapper workerEpointDailyRecordMapper;


    @Override
    public WorkerEpointDailyRecord findWorkerEpointDailyRecordById(Long id) {
        return workerEpointDailyRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int saveWorkerEpointDailyRecord(WorkerEpointDailyRecord workerEpointDailyRecord) {
        return workerEpointDailyRecordMapper.insert(workerEpointDailyRecord);
    }

    @Override
    public int updateWorkerEpointDailyRecord(WorkerEpointDailyRecord workerEpointDailyRecord) {
        return workerEpointDailyRecordMapper.updateByPrimaryKeySelective(workerEpointDailyRecord);
    }

    @Override
    public int deleteWorkerEpointDailyRecord(Long id) {
        return workerEpointDailyRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public WorkerEpointDailyRecord findTodayRecordByWorkerIdAndStatisticsType(Long workerId, Integer statisticsType) {
        return workerEpointDailyRecordMapper.findTodayRecordByWorkerIdAndStatisticsType(workerId,statisticsType);
    }

    @Override
    public List<WorkerEpointDailyRecord> senvenDayTotalEpointRange(Long workerId) {
        return workerEpointDailyRecordMapper.senvenDayTotalEpointRange(workerId);
    }

    @Override
    public Long senvenDayTotalEpointByStatisticsType(Long workerId, Integer statisticsType) {
        return workerEpointDailyRecordMapper.senvenDayTotalEpointByStatisticsType(workerId,statisticsType);
    }

    @Override
    public Long allTotalEpointByStatisticsType(Long workerId, Integer statisticsType) {
        return workerEpointDailyRecordMapper.allTotalEpointByStatisticsType(workerId,statisticsType);
    }

    @Override
    public List<EpointRankDto> weekRank(String[] idList) {
        return workerEpointDailyRecordMapper.weekRank(idList);
    }

    @Override
    public List<EpointRankDto> totalRank(String[] idList) {
        return workerEpointDailyRecordMapper.totalRank(idList);
    }

    @Override
    public List<EpointRankDto> weekRankByPage(Integer pageIndex, Integer pageSize,String[] idList) {
        ///** 启始页-位置 */
        Integer start = (pageIndex - 1) * pageSize;
        /** 每页大小  */
        Integer limit = pageSize;
        return workerEpointDailyRecordMapper.weekRankByPage(start,limit,idList);
    }

    @Override
    public List<EpointRankDto> totalRankByPage(Integer pageIndex, Integer pageSize,String[] idList) {
        ///** 启始页-位置 */
        Integer start = (pageIndex - 1) * pageSize;
        /** 每页大小  */
        Integer limit = pageSize;
        return workerEpointDailyRecordMapper.totalRankByPage(start,limit,idList);
    }

    @Override
    public List<EpointDailyStatisticsDto> totalEpointDailyStatistics() {
        return workerEpointDailyRecordMapper.totalEpointDailyStatistics();
    }

    @Override
    public Integer getTotalEpointDailyStatisticByScoreDate(String scoreDate) {
        return workerEpointDailyRecordMapper.getTotalEpointDailyStatisticByScoreDate(scoreDate);
    }


}
