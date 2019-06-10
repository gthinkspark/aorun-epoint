package com.aorun.epoint.controller;


import com.aorun.EpointMsgDataStructure;
import com.aorun.epoint.config.ThirdSysProperty;
import com.aorun.epoint.controller.login.WorkerUnionInfoDto;
import com.aorun.epoint.dto.EpointRankDto;
import com.aorun.epoint.model.WorkerEpointDailyRecord;
import com.aorun.epoint.model.WorkerEpointRecord;
import com.aorun.epoint.model.WorkerMember;
import com.aorun.epoint.rabbitmq_direct.SenderEpointMsgDataStructure;
import com.aorun.epoint.service.WorkerEpointDailyRecordService;
import com.aorun.epoint.service.WorkerEpointRecordService;
import com.aorun.epoint.service.WorkerMemberService;
import com.aorun.epoint.util.DateFormat;
import com.aorun.epoint.util.DateFriendlyShow;
import com.aorun.epoint.util.PageConstant;
import com.aorun.epoint.util.biz.ImagePropertiesConfig;
import com.aorun.epoint.util.biz.WorkerMemberUtil;
import com.aorun.epoint.util.biz.unioninfo.TESTOKHttp;
import com.aorun.epoint.util.biz.unioninfo.UnionInfo;
import com.aorun.epoint.util.biz.unioninfo.WorkerId;
import com.aorun.epoint.util.jsonp.Jsonp;
import com.aorun.epoint.util.jsonp.Jsonp_data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * 积分报表
 * Created by bysocket on 07/02/2017.
 */
@RequestMapping("/epoint")
@RestController
public class WorkerGradeReportRestController {

    @Autowired
    private WorkerMemberService workerMemberService;

    @Autowired
    private WorkerEpointDailyRecordService workerEpointDailyRecordService;

    @Autowired
    private WorkerEpointRecordService workerEpointRecordService;


    @Autowired
    private SenderEpointMsgDataStructure senderEpointMsgDataStructure;

    @Resource
    private ThirdSysProperty thirdSysProperty;



    //客户端上传任务积分
    @RequestMapping(value = "/uploadTaskEpoint", method = RequestMethod.GET)
    public Object uploadTaskEpoint(@RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                  @RequestParam(name = "taskCode", required = true, defaultValue = "") String taskCode,
                                   @RequestParam(name = "epoint", required = false, defaultValue = "0") Integer epoint,
                                   @RequestParam(name = "bizUniqueSignCode", required = false, defaultValue = "") String bizUniqueSignCode
    ) {

        Long workerId = WorkerMemberUtil.getWorkerId(sid);
        EpointMsgDataStructure epointMsgDataStructure = new EpointMsgDataStructure();

        epointMsgDataStructure.setBizUniqueSignCode(bizUniqueSignCode);
        epointMsgDataStructure.setEpoint(epoint);
        epointMsgDataStructure.setEpointConfigCode(taskCode);
        epointMsgDataStructure.setMsgId(UUID.randomUUID().toString());
        epointMsgDataStructure.setWorkerId(workerId);

        System.out.println("epointMsgDataStructure:"+epointMsgDataStructure.toString());
        senderEpointMsgDataStructure.sendObject(epointMsgDataStructure);
        return Jsonp.success();
    }


    //1.学习报表接口
    @RequestMapping(value = "/learnReport", method = RequestMethod.GET)
    public Object learnReport(@RequestParam(name = "sid", required = true, defaultValue = "") String sid) {


        Long workerId = WorkerMemberUtil.getWorkerId(sid);
        //7日积分排行
        List<WorkerEpointDailyRecord>  workerEpointDailyRecordList = workerEpointDailyRecordService.senvenDayTotalEpointRange(workerId);
        Map<String, Object> datamap = new HashMap<>();
        List<Map<String, Object>> everyDayDatamapList = new ArrayList<Map<String, Object>>();
        for(WorkerEpointDailyRecord workerEpointDailyRecord:workerEpointDailyRecordList){
            Map<String, Object> everyDataMap = new HashMap<>();
            everyDataMap.put("date", DateFormat.dateToMMddString2(workerEpointDailyRecord.getScoreDate()));
            everyDataMap.put("epoint", workerEpointDailyRecord.getTotalScore());
            everyDayDatamapList.add(everyDataMap);
        }
        datamap.put("everyDayDatamapList",everyDayDatamapList);


        //近7日获得按分类积分
        int statisticsType =1;//1-阅读,2-评论，3-答题，4-其他,,5-总积分
        Map<String, Object> sevenDayEpointDateMap = new HashMap<>();
        Long read = workerEpointDailyRecordService.senvenDayTotalEpointByStatisticsType(workerId,statisticsType);
        sevenDayEpointDateMap.put("read", read!=null?read:0);//阅读
        statisticsType =2;
        Long comment = workerEpointDailyRecordService.senvenDayTotalEpointByStatisticsType(workerId,statisticsType);
        sevenDayEpointDateMap.put("comment", comment!=null?comment:0);//评论
        statisticsType =3;
        Long answer = workerEpointDailyRecordService.senvenDayTotalEpointByStatisticsType(workerId,statisticsType);
        sevenDayEpointDateMap.put("answer", answer!=null?answer:0);//答题
        statisticsType =4;
        Long other = workerEpointDailyRecordService.senvenDayTotalEpointByStatisticsType(workerId,statisticsType);
        sevenDayEpointDateMap.put("other", other!=null?other:0);//其他
        statisticsType =5;
        Long total = workerEpointDailyRecordService.senvenDayTotalEpointByStatisticsType(workerId,statisticsType);
        sevenDayEpointDateMap.put("total", total!=null?total:0);//总数;
        datamap.put("sevenDayEpointDateMap",sevenDayEpointDateMap);


        //累计获得总积分
        statisticsType =1;//1-阅读,2-评论，3-答题，4-其他,,5-总积分
        Map<String, Object> totalEpointDateMap = new HashMap<>();
        Long totalRead = workerEpointDailyRecordService.allTotalEpointByStatisticsType(workerId,statisticsType);
        totalEpointDateMap.put("read", totalRead!=null?totalRead:0);//阅读
        statisticsType =2;
        Long totalComment = workerEpointDailyRecordService.allTotalEpointByStatisticsType(workerId,statisticsType);
        totalEpointDateMap.put("comment", totalComment!=null?totalComment:0);//评论
        statisticsType =3;
        Long totalAnswer = workerEpointDailyRecordService.allTotalEpointByStatisticsType(workerId,statisticsType);
        totalEpointDateMap.put("answer", totalAnswer!=null?totalAnswer:0);//答题
        statisticsType =4;
        Long totalOther = workerEpointDailyRecordService.allTotalEpointByStatisticsType(workerId,statisticsType);
        totalEpointDateMap.put("other", totalOther!=null?totalOther:0);//其他
        statisticsType =5;
        Long totalAll = workerEpointDailyRecordService.allTotalEpointByStatisticsType(workerId,statisticsType);
        totalEpointDateMap.put("total", totalAll!=null?totalAll:0);//总数;
        datamap.put("totalEpointDateMap",totalEpointDateMap);


        return Jsonp_data.success(datamap);
    }

    //2.工会积分汇总接口
    @RequestMapping(value = "/epointCollectStatistics", method = RequestMethod.GET)
    public Object rankingTotalCollect(@RequestParam(name = "sid", required = true, defaultValue = "") String sid) {


        Long myworkerId = WorkerMemberUtil.getWorkerId(sid);
        System.out.println("workerId---->"+myworkerId);
        WorkerMember myWorkerMember = WorkerMemberUtil.getWorkerMember(sid);

       // WorkerUnionInfoDto workerUnionInfoDto = WorkerMemberUtil.getWorkerMember(workerId);

        //汇总接口
        Map<String, Object> datamap = new HashMap<>();
        datamap.put("imgPath", myWorkerMember.getImgPath());


        //TODO:获取个人工会信息&个人入会会员列表
        StringBuffer ids = new StringBuffer("");
        try{
            String unionbaseurl = thirdSysProperty.getUnionbaseurl();
            //sid = "ZxB2vz4vB87SN2NT69259365wY7iumD5";
            System.out.println("unionbaseurl+sid===="+unionbaseurl+sid);
            UnionInfo unionInfo = TESTOKHttp.interfaceUtil(unionbaseurl+sid,"");
            if(unionInfo!=null){
                String workerMemberName = unionInfo.getWorkerName();
                String workerName = unionInfo.getUnionName();

                long lt = new Long(unionInfo.getCheckTime());
                Date date = new Date(lt);
                String comeInWorkertime = DateFormat.dateTimeToDateString2(date);
                datamap.put("workerMemberName", workerMemberName);//个人名字
                datamap.put("workerName", workerName);//工会名字
                datamap.put("comeInWorkertime",comeInWorkertime);//入会时间

                List<WorkerId> workerIds = unionInfo.getWorkerUnionList();
                for(int i=0;i<workerIds.size();i++){
                    WorkerId workerId = workerIds.get(i);
                    ids.append(workerId.getWorkerId());
                    if(i!=workerIds.size()-1){
                        ids.append(",");
                    }
                }
                System.out.println(ids.toString());

            }else{
                datamap.put("workerMemberName","");
                datamap.put("workerName", "");
                datamap.put("comeInWorkertime","");
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("unionInfo ----error"+e.getMessage());
        }


        String[] idList = new String[]{ids.toString()};
        //周排名
        List<EpointRankDto> epointWeekRankDtoList = workerEpointDailyRecordService.weekRank(idList);
        System.out.println("epointWeekRankDtoList.size()-----"+epointWeekRankDtoList.size());
        int weeknum =1;
        for(EpointRankDto epointRankDto:epointWeekRankDtoList){
            if(myworkerId==epointRankDto.getWorkerId()){
                break;
            }
            weeknum++;
        }
        datamap.put("weekRank", weeknum);//周排名
        //总排名
        List<EpointRankDto> epointTotalRankDtoList = workerEpointDailyRecordService.totalRank(idList);
        System.out.println("epointWeekRankDtoList.size()-----"+epointWeekRankDtoList.size());
        int totalnum =1;
        for(EpointRankDto epointRankDto:epointTotalRankDtoList){
            if(myworkerId==epointRankDto.getWorkerId()){
                break;
            }
            totalnum++;
        }
        datamap.put("totalRank", totalnum);//总排名
        WorkerMember workerMember = workerMemberService.findByWorkerId(myworkerId);
        datamap.put("epoint", workerMember.getEpoints());//我的积分

        datamap.put("epointLevel",workerMember.getEpointLevel());//积分等级
        return Jsonp_data.success(datamap);
    }


    //3.工会排名-周排名
    @RequestMapping(value = "/weekRankingList", method = RequestMethod.GET)
    public Object weekRankingList(@RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                  @RequestParam(name = "pageIndex", required = true, defaultValue = "1") Integer pageIndex,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = PageConstant.APP_PAGE_SIZE + "") Integer pageSize
    ) {
        Long myworkerId = WorkerMemberUtil.getWorkerId(sid);
        System.out.println("workerId---->"+myworkerId);

        //TODO:获取个人工会信息&个人入会会员列表
        StringBuffer ids = new StringBuffer("");
        try{
            String unionbaseurl = thirdSysProperty.getUnionbaseurl();
            //sid = "ZxB2vz4vB87SN2NT69259365wY7iumD5";
            System.out.println("unionbaseurl+sid===="+unionbaseurl+sid);
            UnionInfo unionInfo = TESTOKHttp.interfaceUtil(unionbaseurl+sid,"");

            if(unionInfo!=null){
                List<WorkerId> workerIds = unionInfo.getWorkerUnionList();
                for(int i=0;i<workerIds.size();i++){
                    WorkerId workerId = workerIds.get(i);
                    ids.append(workerId.getWorkerId());
                    if(i!=workerIds.size()-1){
                        ids.append(",");
                    }
                }
                System.out.println(ids.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("workerIds  exception"+e.getMessage());
        }

        String[] idList = new String[]{ids.toString()};
        System.out.println("ids."+ids.toString());
        //WorkerMember workerMember = WorkerMemberUtil.getWorkerMember(sid);
        List<Map<String, Object>> datamapList = new ArrayList<>();
        List<EpointRankDto> epointWeekRankDtoList = workerEpointDailyRecordService.weekRankByPage(pageIndex,pageSize,idList);
        System.out.println("epointWeekRankDtoList.size---"+epointWeekRankDtoList.size());
        for(EpointRankDto epointRankDto:epointWeekRankDtoList){
            Map<String, Object> datamap = new HashMap<>();
            WorkerMember workerMember = workerMemberService.findByWorkerId(epointRankDto.getWorkerId());
            if(workerMember!=null){
                datamap.put("imgPath", ImagePropertiesConfig.WORKERMEMBER_HEADIMG_SERVER_PATH+workerMember.getImgPath());
                WorkerUnionInfoDto workerUnionInfoDto = WorkerMemberUtil.getWorkerMember(workerMember.getId());
                if(workerUnionInfoDto!=null){
                    datamap.put("workerMemberName", workerUnionInfoDto.getName());//名字
                }else {
                    datamap.put("workerMemberName", "");//名字
                }

            }else {
                datamap.put("workerMemberName", "");//名字
                datamap.put("imgPath", "");
            }
            datamap.put("epoint", epointRankDto.getTotalScore());//我的积分
            datamapList.add(datamap);
        }

        return Jsonp_data.success(datamapList);
    }



    //4.工会排名-总排名    epointRankingList   需要分页
    @RequestMapping(value = "/epointRankingList", method = RequestMethod.GET)
    public Object epointRankingList(@RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                    @RequestParam(name = "pageIndex", required = true, defaultValue = "1") Integer pageIndex,
                                    @RequestParam(name = "pageSize", required = false, defaultValue = PageConstant.APP_PAGE_SIZE + "") Integer pageSize
    ) {

        Long myworkerId = WorkerMemberUtil.getWorkerId(sid);
        System.out.println("workerId---->"+myworkerId);

        //TODO:获取个人工会信息&个人入会会员列表
        StringBuffer ids = new StringBuffer("");
        try{
            String unionbaseurl = thirdSysProperty.getUnionbaseurl();
            //sid = "ZxB2vz4vB87SN2NT69259365wY7iumD5";
            System.out.println("unionbaseurl+sid===="+unionbaseurl+sid);
            UnionInfo unionInfo = TESTOKHttp.interfaceUtil(unionbaseurl+sid,"");

            if(unionInfo!=null){
                List<WorkerId> workerIds = unionInfo.getWorkerUnionList();
                for(int i=0;i<workerIds.size();i++){
                    WorkerId workerId = workerIds.get(i);
                    ids.append(workerId.getWorkerId());
                    if(i!=workerIds.size()-1){
                        ids.append(",");
                    }
                }
                System.out.println(ids.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("workerIds  exception"+e.getMessage());
        }
        String[] idList = new String[]{ids.toString()};
        System.out.println("ids."+ids.toString());


        List<Map<String, Object>> datamapList = new ArrayList<>();
        List<EpointRankDto> epointTotalRankDtoList = workerEpointDailyRecordService.totalRankByPage(pageIndex,pageSize,idList);
        System.out.println("epointTotalRankDtoList.size---"+epointTotalRankDtoList.size());
        for(EpointRankDto epointRankDto:epointTotalRankDtoList){
            Map<String, Object> datamap = new HashMap<>();
            WorkerMember workerMember = workerMemberService.findByWorkerId(epointRankDto.getWorkerId());
            if(workerMember!=null){
                datamap.put("imgPath", ImagePropertiesConfig.WORKERMEMBER_HEADIMG_SERVER_PATH+workerMember.getImgPath());
                WorkerUnionInfoDto workerUnionInfoDto = WorkerMemberUtil.getWorkerMember(workerMember.getId());
                if(workerUnionInfoDto!=null){
                    datamap.put("workerMemberName", workerUnionInfoDto.getName());//名字
                }else {
                    datamap.put("workerMemberName", "");//名字
                }
            }else {
                datamap.put("workerMemberName", "");//名字
                datamap.put("imgPath", "");
            }
            datamap.put("epoint", epointRankDto.getTotalScore());//我的积分
            datamapList.add(datamap);
        }

        return Jsonp_data.success(datamapList);
    }


    //5.工会排名-积分列表   epointRecordList   需要分页
    @RequestMapping(value = "/epointRecordList", method = RequestMethod.GET)
    public Object epointRecordList(@RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                   @RequestParam(name = "pageIndex", required = true, defaultValue = "1") Integer pageIndex,
                                   @RequestParam(name = "pageSize", required = false, defaultValue = PageConstant.APP_PAGE_SIZE + "") Integer pageSize
    ) {
        Long workerId = WorkerMemberUtil.getWorkerId(sid);
        List<WorkerEpointRecord> workerEpointRecordList = workerEpointRecordService.findworkerEpointRecordByPage(workerId,pageIndex,pageSize);
        List<Map<String, Object>> datamapList = new ArrayList<>();
        for(WorkerEpointRecord workerEpointRecord:workerEpointRecordList){
            Map<String, Object> datamap = new HashMap<>();
            datamap.put("title",workerEpointRecord.getTitle());//标题
            datamap.put("obtainTime", DateFriendlyShow.timeDiffText(workerEpointRecord.getObtainTime(),new Date()));//获得时间
            datamap.put("epoint", workerEpointRecord.getScore());//我的积分
            datamapList.add(datamap);
        }
        return Jsonp_data.success(datamapList);
    }

}
