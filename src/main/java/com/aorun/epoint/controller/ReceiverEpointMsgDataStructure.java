package com.aorun.epoint.controller;

import com.aorun.EpointMsgDataStructure;
import com.aorun.epoint.model.*;
import com.aorun.epoint.rabbitmq_direct.RabbitConfig;
import com.aorun.epoint.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RabbitListener(queues = RabbitConfig.epointMsgDataStructureQueue)
public class ReceiverEpointMsgDataStructure {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiverEpointMsgDataStructure.class);

    @Autowired
    private WorkerEpointConfigService workerEpointConfigService;

    @Autowired
    private WorkerMemberService workerMemberService;

    @Autowired
    private WorkerEpointLevelService workerEpointLevelService;

    @Autowired
    private WorkerEpointDailyRecordService workerEpointDailyRecordService;

    @Autowired
    private WorkerEpointRecordService workerEpointRecordService;

    @RabbitHandler
    public void process(EpointMsgDataStructure epointMsgDataStructure) throws Exception{
        try{
            LOGGER.info("ReceiverObject111  : " + epointMsgDataStructure.toString());
        String bizUniqueSignCode = epointMsgDataStructure.getBizUniqueSignCode();//业务唯一标识码
        Integer epoint = epointMsgDataStructure.getEpoint();//选填(默认为0)
        String epointConfigCode = epointMsgDataStructure.getEpointConfigCode();//积分配置编码
        String msgId = epointMsgDataStructure.getMsgId();//随机唯一ID
        Long workerId =  epointMsgDataStructure.getWorkerId();//工会会员ID

        //1.通过  epointConfigCode 拿到配置记录，
        WorkerEpointConfig  workerEpointConfig = workerEpointConfigService.findEpointConfigByCode(epointConfigCode);
         //Integer bizType = workerEpointConfig.getBizType();//1-每日任务,2-认证类型，3-一次性任务,4-答题任务
         String otherSysDecideEpoint = workerEpointConfig.getOtherSysDecideEpoint();//y-第三方系统决定积分，n-积分配置平台决定积分
         Integer score = workerEpointConfig.getScore();//获得积分
         Integer obtainScoreRate = workerEpointConfig.getObtainScoreRate();//获取积分频率(1-只有一次，2-每日一次，3-不限次数)
         Integer statisticsType = workerEpointConfig.getStatisticsType();//1-阅读,2-评论，3-答题，4-其他
         String title = workerEpointConfig.getEpointRecordTitle();
        if(otherSysDecideEpoint!=null && otherSysDecideEpoint.equals("y")){
            score = epoint;
        }
        if(obtainScoreRate==1){//1-只能添加有一次
            //判断是否添加过
            List<WorkerEpointRecord> workerEpointRecordList =  workerEpointRecordService.findUniqueRecord(workerId,epointConfigCode);//查找工会会员ID某个epointConfigCode是否添加过
            if(workerEpointRecordList!=null &&workerEpointRecordList.size()==0){//可以添加
                this.saveEpointInfo(title,workerId,epointConfigCode,score,bizUniqueSignCode,msgId,statisticsType);
            }
        }else if(obtainScoreRate==2){//2-每日添加一次
            //判断是否添加过
            List<WorkerEpointRecord> workerEpointRecordList = workerEpointRecordService.findTodayUniqueRecord(workerId,epointConfigCode);//查找工会会员ID某个epointConfigCode当天是否添加过
            if(workerEpointRecordList!=null &&workerEpointRecordList.size()==0){//可以添加
                this.saveEpointInfo(title,workerId,epointConfigCode,score,bizUniqueSignCode,msgId,statisticsType);
            }
        }else if(obtainScoreRate==3){//每日不限次数添加一次
            //直接添加
            this.saveEpointInfo(title,workerId,epointConfigCode,score,bizUniqueSignCode,msgId,statisticsType);
        }
        }catch(Exception ex){
            ex.printStackTrace();
            LOGGER.error("==========================================");
            LOGGER.error(ex.toString());
        }
    }

    public void saveEpointInfo(String title,Long workerId,String epointConfigCode,Integer score ,String bizUniqueSignCode, String msgId,Integer statisticsType) {

        //判断  workerId,epointConfigCode,bizUniqueSignCode
        if (bizUniqueSignCode != null && !bizUniqueSignCode.equals("")) {
            List<WorkerEpointRecord> workerEpointRecordList = workerEpointRecordService.findUniqueRecordByBizUniqueSignCode(workerId, epointConfigCode, bizUniqueSignCode);
            if (workerEpointRecordList != null &&workerEpointRecordList.size()==0) {
                this.save(title,workerId,epointConfigCode,score,bizUniqueSignCode,msgId,statisticsType);
            }
        }else{
            this.save(title,workerId,epointConfigCode,score,bizUniqueSignCode,msgId,statisticsType);
        }
    }
        public void save(String title,Long workerId,String epointConfigCode,Integer score ,String bizUniqueSignCode, String msgId,Integer statisticsType){
            //1.保存t_worker_epoint_record  积分记录
            WorkerEpointRecord workerEpointRecord  = new WorkerEpointRecord();
            workerEpointRecord.setTitle(title);
            workerEpointRecord.setWorkerId(workerId);
            workerEpointRecord.setEpointConfigCode(epointConfigCode);
            workerEpointRecord.setScore(score);
            workerEpointRecord.setStatisticsType(statisticsType);
            workerEpointRecord.setBizUniqueSignCode(bizUniqueSignCode);
            workerEpointRecord.setMsgId(msgId);
            workerEpointRecordService.saveWorkerEpointRecord(workerEpointRecord);

            // 2.保存t_worker_epoint_daily_record   算出每日积分统计信息
            //按类型查询当天积分统计信息
            WorkerEpointDailyRecord  workerEpointDailyRecord = workerEpointDailyRecordService.findTodayRecordByWorkerIdAndStatisticsType(workerId,statisticsType);
            if(workerEpointDailyRecord!=null){//存在则更新
                workerEpointDailyRecord.setTotalScore(workerEpointDailyRecord.getTotalScore()+score);
                workerEpointDailyRecordService.updateWorkerEpointDailyRecord(workerEpointDailyRecord);
            }else{//add
                workerEpointDailyRecord = new WorkerEpointDailyRecord();
                workerEpointDailyRecord.setWorkerId(workerId);
                workerEpointDailyRecord.setStatisticsType(statisticsType);
                workerEpointDailyRecord.setTotalScore(score);
                workerEpointDailyRecord.setScoreDate(new Date());
                workerEpointDailyRecordService.saveWorkerEpointDailyRecord(workerEpointDailyRecord);
            }
            //查询当天总积分
            WorkerEpointDailyRecord  totalWorkerEpointDailyRecord = workerEpointDailyRecordService.findTodayRecordByWorkerIdAndStatisticsType(workerId,5);
            if(totalWorkerEpointDailyRecord!=null){//存在则更新
                totalWorkerEpointDailyRecord.setTotalScore(totalWorkerEpointDailyRecord.getTotalScore()+score);
                workerEpointDailyRecordService.updateWorkerEpointDailyRecord(totalWorkerEpointDailyRecord);
            }else{//add
                totalWorkerEpointDailyRecord = new WorkerEpointDailyRecord();
                totalWorkerEpointDailyRecord.setWorkerId(workerId);
                totalWorkerEpointDailyRecord.setStatisticsType(5);
                totalWorkerEpointDailyRecord.setTotalScore(score);
                totalWorkerEpointDailyRecord.setScoreDate(new Date());
                workerEpointDailyRecordService.saveWorkerEpointDailyRecord(totalWorkerEpointDailyRecord);
            }



            //3.更新用户等级&积分
            WorkerMember workerMember = workerMemberService.findByWorkerId(workerId);
            //TODO:加积分线程同步控制
            Integer totalScore = workerMember.getEpoints()+score;
            //4.判断积分级别
            WorkerEpointLevel workerEpointLevel = workerEpointLevelService.getRangeEpointLevel(totalScore);
            if(workerEpointLevel!=null){
                workerMember.setEpointLevel(workerEpointLevel.getLevelLabel());
            }else {
                workerMember.setEpointLevel("1");
            }
            workerMember.setEpoints(totalScore);
            //5.更新积分&等级
            workerMemberService.updateWorkerMember(workerMember);
        }



        }


