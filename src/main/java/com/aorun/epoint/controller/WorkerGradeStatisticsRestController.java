package com.aorun.epoint.controller;


import com.aorun.epoint.dto.EpointStatisticsDto;
import com.aorun.epoint.model.WorkerEpointConfig;
import com.aorun.epoint.service.WorkerEpointConfigService;
import com.aorun.epoint.service.WorkerEpointDailyRecordService;
import com.aorun.epoint.service.WorkerEpointRecordService;
import com.aorun.epoint.service.WorkerGradeService;
import com.aorun.epoint.util.jsonp.Jsonp_data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 我的成绩
 * .Created by bysocket on 07/02/2017
 */
@RequestMapping("/statistics")
@RestController
public class WorkerGradeStatisticsRestController {

    @Autowired
    private WorkerGradeService workerGradeService;

    @Autowired
    private WorkerEpointConfigService workerEpointConfigService;

    @Autowired
    private WorkerEpointDailyRecordService workerEpointDailyRecordService;


    @Autowired
    private WorkerEpointRecordService workerEpointRecordService;

    //1.成绩积分
    @RequestMapping(value = "/gradeEpointStatistics", method = RequestMethod.GET)
    //@ApiOperation("获取成绩类型列表")
    public Object gradeEpointStatistics() {
        List<Map<String, Object>> datamapList = new ArrayList<Map<String, Object>>();
        List<WorkerEpointConfig> certificateTypeWorkerEpointConfigList = workerEpointConfigService.findEpointConfigListByBizType(new Integer(2));
        for(WorkerEpointConfig workerEpointConfig :certificateTypeWorkerEpointConfigList){
            Map<String, Object> datamap = new HashMap<>();
            //datamap.put("id", workerEpointConfig.getId());
            //datamap.put("code",workerEpointConfig.getCode());
            datamap.put("name", workerEpointConfig.getName());
            EpointStatisticsDto epointStatisticsDto = workerGradeService.getEpointStatistics(workerEpointConfig.getId());
            datamap.put("applyNum", epointStatisticsDto.getApplyNum());
            datamap.put("totalScore",epointStatisticsDto.getTotalScore()!=null?epointStatisticsDto.getTotalScore():0);
            datamapList.add(datamap);
        }

        return Jsonp_data.success(datamapList);
    }

    //2.证书认证-统计
    @RequestMapping(value = "/gradeEpointRateStatistics", method = RequestMethod.GET)
    public Object gradeEpointRateStatistics() {

        List<Map<String, Object>> datamapList = new ArrayList<Map<String, Object>>();
        List<WorkerEpointConfig> certificateTypeWorkerEpointConfigList = workerEpointConfigService.findEpointConfigListByBizType(new Integer(2));

        int totalSocre = 0;
        for(WorkerEpointConfig workerEpointConfig :certificateTypeWorkerEpointConfigList){
            EpointStatisticsDto epointStatisticsDto = workerGradeService.getEpointStatistics(workerEpointConfig.getId());
            if(epointStatisticsDto.getTotalScore()!=null){
                totalSocre = totalSocre+ epointStatisticsDto.getTotalScore().intValue();
            }
        }

        DecimalFormat df=new DecimalFormat("0.00");//设置保留位数
        for(WorkerEpointConfig workerEpointConfig :certificateTypeWorkerEpointConfigList){
            Map<String, Object> datamap = new HashMap<>();
            //datamap.put("id", workerEpointConfig.getId());
            //datamap.put("code",workerEpointConfig.getCode());
            datamap.put("name", workerEpointConfig.getName());
            EpointStatisticsDto epointStatisticsDto = workerGradeService.getEpointStatistics(workerEpointConfig.getId());
            //datamap.put("applyNum", epointStatisticsDto.getApplyNum());
            if(epointStatisticsDto.getTotalScore()!=null){
                //datamap.put("epointRate",epointStatisticsDto.getTotalScore()/totalSocre);
                if(totalSocre==0){
                    datamap.put("epointRate", 0);
                }else{
                    datamap.put("epointRate", df.format((float)100*epointStatisticsDto.getTotalScore().intValue()/totalSocre));
                }

            }else{
                datamap.put("epointRate",0);
            }
            datamapList.add(datamap);
        }

        return Jsonp_data.success(datamapList);
    }



    //3.人才孵化-统计
    @RequestMapping(value = "/talentIncubatorStatistics", method = RequestMethod.GET)
    public Object talentIncubatorStatistics() {

        List<Map<String, Object>> datamapList = new ArrayList<Map<String, Object>>();
        Integer parentId = 5;//劳模工匠
        List<WorkerEpointConfig> certificateTypeWorkerEpointConfigList = workerEpointConfigService.findEpointConfigListByParentId(parentId);

        int totalSocre = 0;
        for(WorkerEpointConfig workerEpointConfig :certificateTypeWorkerEpointConfigList){
            int num = workerGradeService.getTotalByCertificateCode(workerEpointConfig.getCode());
            totalSocre = totalSocre+ num;
        }

        DecimalFormat df=new DecimalFormat("0.00");//设置保留位数
        for(WorkerEpointConfig workerEpointConfig :certificateTypeWorkerEpointConfigList){
            Map<String, Object> datamap = new HashMap<>();
            //datamap.put("id", workerEpointConfig.getId());
            //datamap.put("code",workerEpointConfig.getCode());
            datamap.put("name", workerEpointConfig.getName());
            int num = workerGradeService.getTotalByCertificateCode(workerEpointConfig.getCode());
            datamap.put("num", num);
            if(totalSocre==0){
                datamap.put("epointRate", 0);
            }else{
                datamap.put("epointRate", df.format((float)100*num/totalSocre));
            }

            datamapList.add(datamap);
        }

        return Jsonp_data.success(datamapList);
    }


    //4.统计学历-统计
    @RequestMapping(value = "/educationStatistics", method = RequestMethod.GET)
    public Object educationStatistics() {

        List<Map<String, Object>> datamapList = new ArrayList<Map<String, Object>>();
        Integer parentId = 8;//学历提升
        List<WorkerEpointConfig> certificateTypeWorkerEpointConfigList = workerEpointConfigService.findEpointConfigListByParentId(parentId);

        int totalSocre = 0;
        for(WorkerEpointConfig workerEpointConfig :certificateTypeWorkerEpointConfigList){
            int num = workerGradeService.getTotalByCertificateCode(workerEpointConfig.getCode());
            totalSocre = totalSocre+ num;
        }

        DecimalFormat df=new DecimalFormat("0.00");//设置保留位数
        for(WorkerEpointConfig workerEpointConfig :certificateTypeWorkerEpointConfigList){
            Map<String, Object> datamap = new HashMap<>();
            //datamap.put("id", workerEpointConfig.getId());
            //datamap.put("code",workerEpointConfig.getCode());
            datamap.put("name", workerEpointConfig.getName());
            int num = workerGradeService.getTotalByCertificateCode(workerEpointConfig.getCode());
            datamap.put("num", num);
            datamapList.add(datamap);
        }

        return Jsonp_data.success(datamapList);
    }




    //5.签到统计-接口-统计
    @RequestMapping(value = "/signInStatistics", method = RequestMethod.GET)
    public Object signInStatistics() {

        Map<String, Object> datamap = new HashMap<>();
        Integer num1 = workerEpointRecordService.statisticsTotalByType(1,"TASK_9");
        datamap.put("todayNum", num1);
        Integer num2 = workerEpointRecordService.statisticsTotalByType(2,"TASK_9");
        datamap.put("weekNum", num2);
        Integer num3 = workerEpointRecordService.statisticsTotalByType(3,"TASK_9");
        datamap.put("monthNum", num3);
        Integer num4 = workerEpointRecordService.statisticsTotalByType(4,"TASK_9");
        datamap.put("quarterNum", num4);

        return Jsonp_data.success(datamap);
    }


    //5.学习报表-接口
    @RequestMapping(value = "/learnReportStatistics", method = RequestMethod.GET)
    public Object learnReportStatistics() {
        List<Map<String, Object>> datamapList = new ArrayList<Map<String, Object>>();
//        List<EpointDailyStatisticsDto> epointDailyStatisticsDtoList = workerEpointDailyRecordService.totalEpointDailyStatistics();
        for(int i=0;i<=6;i++){
            String scoreDate = this.getPastDate(i);
            Integer totalScore = workerEpointDailyRecordService.getTotalEpointDailyStatisticByScoreDate(scoreDate);
            Map<String, Object> datamap = new HashMap<>();
            datamap.put("scoreDate", scoreDate);//证书级别code
            datamap.put("totalScore", null==totalScore?0:totalScore);//证书级别名称
            datamapList.add(datamap);
        }
        return Jsonp_data.success(datamapList);
    }


    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    private String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }
}
