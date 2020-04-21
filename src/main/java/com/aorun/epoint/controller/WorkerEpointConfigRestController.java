package com.aorun.epoint.controller;


import com.aorun.epoint.model.WorkerEpointConfig;
import com.aorun.epoint.model.WorkerEpointRecord;
import com.aorun.epoint.service.WorkerEpointConfigService;
import com.aorun.epoint.service.WorkerEpointRecordService;
import com.aorun.epoint.util.SignInUtil;
import com.aorun.epoint.util.biz.WorkerMemberUtil;
import com.aorun.epoint.util.jsonp.Jsonp_data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 积分规则
 * Created by bysocket on 07/02/2017.
 */
@RequestMapping("/epoint")
@RestController
public class WorkerEpointConfigRestController {

    private static final Log LOGGER = LogFactory.getLog(WorkerEpointConfigRestController.class);
    @Autowired
    private WorkerEpointConfigService workerEpointConfigService;

    @Autowired
    private WorkerEpointRecordService workerEpointRecordService;
    @RequestMapping(value = "/getTaskInfo", method = RequestMethod.GET)
    public Object getTaskInfo(@RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                  @RequestParam(name = "taskCode", required = true, defaultValue = "") String taskCode) {
        Long workerId = WorkerMemberUtil.getWorkerId(sid);
        Map<String, Object> datamap = new HashMap<>();
        List<WorkerEpointRecord>  workerEpointRecordList =  workerEpointRecordService.findTodayUniqueRecord(workerId,taskCode);//查找工会会员ID某个epointConfigCode是否添加过

        if(workerEpointRecordList!=null&&workerEpointRecordList.size()>0){//可以添加
            datamap.put("isComplete","1");//是否完成任务  1 完成，2未完成 3不处理任务

        }else{
            datamap.put("isComplete","2");//是否完成任务  1 完成，2未完成 3不处理任务
        }
        Calendar calendar  = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,999);
        datamap.put("evenDay",getEvenDay(calendar.getTime(),workerId,taskCode));//连续签到天数
        return Jsonp_data.success(datamap);
    }

    private int getEvenDay(Date starTime,Long workerId,String taskCode){
        List<WorkerEpointRecord> workerEpointRecordList = workerEpointRecordService.findUniqueRecord30Day(workerId, taskCode, starTime);
        int continuousSignInDay = SignInUtil.getContinuousSignInDay(starTime,workerEpointRecordList);
        if(continuousSignInDay>=29){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(starTime);
            calendar.add(Calendar.DAY_OF_YEAR,-30);
            continuousSignInDay+=getEvenDay(calendar.getTime(),workerId,taskCode);
        }
        return continuousSignInDay;
    }

    //产业工人激励办法H5链接接口
    @RequestMapping(value = "/getAwardRuleUrl", method = RequestMethod.GET)
    public Object getAwardRuleUrl() {

        Map<String, Object> datamap = new HashMap<>();

        WorkerEpointConfig workerEpointConfig = workerEpointConfigService.findEpointConfigByCode("TASK_0");
        String configExplain = workerEpointConfig.getConfigExplain();
        datamap.put("awardRuleUrl",configExplain);

        return Jsonp_data.success(datamap);
    }

    //积分奖励办法接口
    @RequestMapping(value = "/epointAwardRuleList", method = RequestMethod.GET)
    public Object epointAwardRuleList(@RequestParam(name = "sid", required = true, defaultValue = "") String sid) {


        //findEpointConfigListByBizType();1-每日任务,2-认证类型，3-一次性任务,4-第三方决定分数
        Long workerId = WorkerMemberUtil.getWorkerId(sid);
        Map<String, Object> datamap = new HashMap<>();

        List<Map<String, Object>> dailyTypeTaskDatamapList = new ArrayList<Map<String, Object>>();
        //每日任务
        List<WorkerEpointConfig> dailyTypeWorkerEpointConfigList = workerEpointConfigService.findEpointConfigListByBizType(new Integer(1));
        for(WorkerEpointConfig workerEpointConfig :dailyTypeWorkerEpointConfigList){
            Map<String, Object> mydatamap = new HashMap<>();
            mydatamap.put("id",workerEpointConfig.getId());
            mydatamap.put("code",workerEpointConfig.getCode());
            mydatamap.put("title", workerEpointConfig.getSimpleName());
            mydatamap.put("epointDescribe", workerEpointConfig.getSimpleEpointExplain());
            mydatamap.put("detailExplain",workerEpointConfig.getConfigExplain());
            //判断是否添加过
            List<WorkerEpointRecord>  workerEpointRecordList =  workerEpointRecordService.findTodayUniqueRecord(workerId,workerEpointConfig.getCode());//查找工会会员ID某个epointConfigCode是否添加过
            if(workerEpointRecordList!=null&&workerEpointRecordList.size()>0){//可以添加
                mydatamap.put("isComplete","1");//是否完成任务  1 完成，2未完成 3不处理任务
            }else{
                mydatamap.put("isComplete","2");//是否完成任务  1 完成，2未完成 3不处理任务
            }

            mydatamap.put("typeName","项");
            dailyTypeTaskDatamapList.add(mydatamap);
        }
        datamap.put("dailyTypeTaskDatamapList",dailyTypeTaskDatamapList);

        //2-证书类型任务
        List<Map<String, Object>> certificateTypeTaskDatamapList = new ArrayList<Map<String, Object>>();
        List<WorkerEpointConfig> certificateTypeWorkerEpointConfigList = workerEpointConfigService.findEpointConfigListByBizType(new Integer(2));
        for(WorkerEpointConfig workerEpointConfig :certificateTypeWorkerEpointConfigList){
            Map<String, Object> mydatamap = new HashMap<>();
            mydatamap.put("id",workerEpointConfig.getId());
            mydatamap.put("code",workerEpointConfig.getCode());
            mydatamap.put("title", workerEpointConfig.getSimpleName());
            mydatamap.put("epointDescribe", workerEpointConfig.getSimpleEpointExplain());
            mydatamap.put("detailExplain",workerEpointConfig.getConfigExplain());
            mydatamap.put("isComplete","3");//是否完成任务  1 完成，2未完成 3不处理任务
            mydatamap.put("typeName","项");
            certificateTypeTaskDatamapList.add(mydatamap);
        }
        datamap.put("certificateTypeTaskDatamapList",certificateTypeTaskDatamapList);


        //3-一次性任务
        List<Map<String, Object>> onceTypeTaskDatamapList = new ArrayList<Map<String, Object>>();
        List<WorkerEpointConfig> onceTypeWorkerEpointConfigList = workerEpointConfigService.findEpointConfigListByBizType(new Integer(3));
        for(WorkerEpointConfig workerEpointConfig :onceTypeWorkerEpointConfigList){
            Map<String, Object> mydatamap = new HashMap<>();
            mydatamap.put("id",workerEpointConfig.getId());
            mydatamap.put("code",workerEpointConfig.getCode());
            mydatamap.put("title", workerEpointConfig.getSimpleName());
            mydatamap.put("epointDescribe", workerEpointConfig.getSimpleEpointExplain());
            mydatamap.put("detailExplain",workerEpointConfig.getConfigExplain());

            //判断是否添加过
            List<WorkerEpointRecord>  workerEpointRecordList =  workerEpointRecordService.findUniqueRecord(workerId,workerEpointConfig.getCode());//查找工会会员ID某个epointConfigCode是否添加过
            if(workerEpointRecordList!=null&&workerEpointRecordList.size()>0){//可以添加
                mydatamap.put("isComplete","1");//是否完成任务  1 完成，2未完成 3不处理任务
            }else{
                mydatamap.put("isComplete","2");//是否完成任务  1 完成，2未完成 3不处理任务
            }
            mydatamap.put("typeName","项");
            onceTypeTaskDatamapList.add(mydatamap);
        }
        datamap.put("onceTypeTaskDatamapList",onceTypeTaskDatamapList);


        //4-第三方决定分数任务
        List<Map<String, Object>> otherSystemDecideTaskDatamapList = new ArrayList<Map<String, Object>>();
        List<WorkerEpointConfig> otherSystemDecideWorkerEpointConfigList = workerEpointConfigService.findEpointConfigListByBizType(new Integer(4));
        for(WorkerEpointConfig workerEpointConfig :otherSystemDecideWorkerEpointConfigList){
            Map<String, Object> mydatamap = new HashMap<>();
            mydatamap.put("id",workerEpointConfig.getId());
            mydatamap.put("code",workerEpointConfig.getCode());
            mydatamap.put("title", workerEpointConfig.getSimpleName());
            mydatamap.put("epointDescribe", workerEpointConfig.getSimpleEpointExplain());
            mydatamap.put("detailExplain",workerEpointConfig.getConfigExplain());
            mydatamap.put("isComplete","3");//是否完成任务  1 完成，2未完成 3不处理任务
            mydatamap.put("typeName","项");
            otherSystemDecideTaskDatamapList.add(mydatamap);
        }
        datamap.put("otherSystemDecideTaskDatamapList",otherSystemDecideTaskDatamapList);


        return Jsonp_data.success(datamap);
    }


}
