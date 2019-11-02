package com.aorun.epoint.controller;


import com.aorun.epoint.model.WorkerEpointConfig;
import com.aorun.epoint.model.WorkerGrade;
import com.aorun.epoint.service.WorkerEpointConfigService;
import com.aorun.epoint.service.WorkerGradeService;
import com.aorun.epoint.util.DateFriendlyShow;
import com.aorun.epoint.util.PageConstant;
import com.aorun.epoint.util.biz.ImagePropertiesConfig;
import com.aorun.epoint.util.biz.WorkerMemberUtil;
import com.aorun.epoint.util.jsonp.Jsonp;
import com.aorun.epoint.util.jsonp.Jsonp_data;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * 我的成绩
 * .Created by bysocket on 07/02/2017
 */
@RequestMapping("/epoint")
@RestController
public class WorkerGradeRestController {

    @Autowired
    private WorkerGradeService workerGradeService;

    @Autowired
    private WorkerEpointConfigService workerEpointConfigService;


    //1.成绩类型
    @RequestMapping(value = "/gradeType", method = RequestMethod.GET)
    @ApiOperation("获取成绩类型列表")
    public Object gradeType() {
        List<Map<String, Object>> datamapList = new ArrayList<Map<String, Object>>();
        List<WorkerEpointConfig> certificateTypeWorkerEpointConfigList = workerEpointConfigService.findEpointConfigListByBizType(new Integer(2));
        for(WorkerEpointConfig workerEpointConfig :certificateTypeWorkerEpointConfigList){
            Map<String, Object> datamap = new HashMap<>();
            datamap.put("id", workerEpointConfig.getId());
            datamap.put("code",workerEpointConfig.getCode());
            datamap.put("name", workerEpointConfig.getName());
            datamapList.add(datamap);
        }
        return Jsonp_data.success(datamapList);
    }


    //2.证书类型列表
    @ApiOperation("获取成绩类型列表")
    @RequestMapping(value = "/certificateType", method = RequestMethod.GET)
    public Object certificateType( @RequestParam(name = "id", required = true, defaultValue = "") Integer id) {


        List<Map<String, Object>> datamapList = new ArrayList<Map<String, Object>>();
        List<WorkerEpointConfig> certificateTypeWorkerEpointConfigList = workerEpointConfigService.findEpointConfigListByParentId(id);
        for(WorkerEpointConfig workerEpointConfig :certificateTypeWorkerEpointConfigList){
            Map<String, Object> datamap = new HashMap<>();
            datamap.put("certificateCode", workerEpointConfig.getCode());//证书级别code
            datamap.put("certificateName", workerEpointConfig.getSimpleName());//证书级别名称
            datamap.put("epoint", workerEpointConfig.getScore());// 获得积分
            datamap.put("epointDescribe", workerEpointConfig.getSimpleEpointExplain());
            datamapList.add(datamap);
        }

        return Jsonp_data.success(datamapList);
    }


    //3.列表接口----分页查询
    @RequestMapping(value = "/myGradeList", method = RequestMethod.GET)
    public Object epointConfigList(
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
            @RequestParam(name = "pageIndex", required = true, defaultValue = "1") Integer pageIndex,
            @RequestParam(name = "pageSize", required = false, defaultValue = PageConstant.APP_PAGE_SIZE + "") Integer pageSize,
            @RequestParam(name = "gradeTypeId",required = true) Integer gradeTypeId
    ) {
       Long workerId = WorkerMemberUtil.getWorkerId(sid);
        List<WorkerGrade> workerGradeList = new ArrayList<WorkerGrade>();
        List<Map<String, Object>> datamapList = new ArrayList<Map<String, Object>>();
        workerGradeList = workerGradeService.getWorkerGradeListByWorkerId(workerId,gradeTypeId, pageIndex, pageSize);

        for (WorkerGrade workerGrade : workerGradeList) {
            Map<String, Object> datamap = new HashMap<>();
            datamap.put("id", workerGrade.getId());// ID

            StringBuffer materialsUrlsList = new StringBuffer("");
            String materialsImgUrls = workerGrade.getMaterialsUrls();
            if (materialsImgUrls != null && !materialsImgUrls.equals("")) {
                String _materialsImgUrls[] = materialsImgUrls.split(",");
                for (String _materialsImgUrl : _materialsImgUrls) {
                    materialsUrlsList.append(ImagePropertiesConfig.GRADE_SERVER_PATH + _materialsImgUrl).append(",");
                }
            }
            datamap.put("materialsUrls", materialsUrlsList.toString());
            datamap.put("epoint", workerGrade.getObtainScore());// 获得积分
            datamap.put("certificateName", workerGrade.getCertificateName());//证书级别名称
            datamap.put("createTime", DateFriendlyShow.timeDiffText(workerGrade.getCreateTime(),new Date()));//\
            datamap.put("status", workerGrade.getStatus());//状态  1-审核中，2-审核失败，3-审核成功。
            datamap.put("failReason", workerGrade.getFailReason());//驳回原因
            datamapList.add(datamap);
        }
        return Jsonp_data.success(datamapList);
    }

    //3.详情接口
    @RequestMapping(value = "/myGrade/{id}", method = RequestMethod.GET)
    public Object myGrade(@PathVariable("id") Long id,
                                         @RequestParam(name = "sid", required = true, defaultValue = "") String sid
    ) {

        WorkerGrade workerGrade = workerGradeService.findWorkerGradeById(id);
        StringBuffer materialsList = new StringBuffer("");
        String materialsUrls = workerGrade.getMaterialsUrls();
        if (materialsUrls != null && !materialsUrls.equals("")) {
            String _materialsUrls[] = materialsUrls.split(",");
            for (String materialsUrl : _materialsUrls) {
                materialsList.append(ImagePropertiesConfig.GRADE_SERVER_PATH + materialsUrl).append(",");
            }
        }
        workerGrade.setMaterialsUrls(materialsList.toString());
        Map<String, Object> datamap = new HashMap<>();
        datamap.put("id", workerGrade.getId());// ID
        datamap.put("materialsUrls", workerGrade.getMaterialsUrls());// 图片
        datamap.put("epoint", workerGrade.getObtainScore());// 获得积分
        datamap.put("certificateCode", workerGrade.getCertificateCode());//证书级别Code
        datamap.put("certificateName", workerGrade.getCertificateName());//证书级别名称
        datamap.put("obtainScore", workerGrade.getObtainScore());//获得积分

        datamap.put("companyName", workerGrade.getCompanyName());// 单位名称
        datamap.put("name", workerGrade.getName());// 姓名
        datamap.put("telephone", workerGrade.getTelephone());// 手机号
        datamap.put("idCard", workerGrade.getIdCard());//身份证号
        datamap.put("bankCard", workerGrade.getBankCard());//银行卡号

        datamap.put("status", workerGrade.getStatus());//状态  1-审核中，2-审核失败，3-审核成功。
        datamap.put("failReason", workerGrade.getFailReason());//驳回原因
        return Jsonp_data.success(datamap);
    }


    //2.新增接口
    @RequestMapping(value = "/addMyGrade", method = RequestMethod.POST)
    public Object addMyGrade(@RequestParam(name = "sid", required = true, defaultValue = "") String sid,
                                        @RequestParam(name = "gradeTypeId",required = true) Integer gradeTypeId,
                                        @RequestParam(name = "name", required = true, defaultValue = "") String name,
                                        @RequestParam(name = "telephone", required = true, defaultValue = "") String telephone,
                                        @RequestParam(name = "idCard", required = true, defaultValue = "") String idCard,
                                        @RequestParam(name = "bankCard", required = true, defaultValue = "") String bankCard,
                                        @RequestParam(name = "companyName", required = true, defaultValue = "") String companyName,
                                        @RequestParam(name = "certificateCode", required = true, defaultValue = "") String certificateCode,
                                        @RequestParam(name = "certificateName", required = true, defaultValue = "") String certificateName,
                                        @RequestParam(name = "obtainScore", required = true, defaultValue = "") Integer obtainScore,
                                        @RequestParam("materialsImgFiles") List<MultipartFile> materialsImgFiles
    ) {
        Long workerId = WorkerMemberUtil.getWorkerId(sid);

        WorkerGrade workerGrade = new WorkerGrade();
        workerGrade.setWorkerId(workerId);
        if (materialsImgFiles == null && materialsImgFiles.size() < 0) {
            return Jsonp.error("文件不能为空!");
        }
        try {
            StringBuffer materialsImgUrls = new StringBuffer("");
            for (MultipartFile file : materialsImgFiles) {
                // Get the file and save it somewhere
                byte[] bytes = file.getBytes();
                String uuid = UUID.randomUUID().toString();
                String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
                String fileName = uuid + suffixName;
                Path path = Paths.get(ImagePropertiesConfig.GRADE_PATH + fileName);
                Files.write(path, bytes);
                materialsImgUrls.append(fileName).append(",");
            }
            workerGrade.setMaterialsUrls(materialsImgUrls.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        workerGrade.setName(name);
        workerGrade.setTelephone(telephone);
        workerGrade.setIdCard(idCard);
        workerGrade.setCompanyName(companyName);
        workerGrade.setBankCard(bankCard);
        workerGrade.setCertificateCode(certificateCode);
        workerGrade.setCertificateName(certificateName);
        workerGrade.setObtainScore(obtainScore);
        workerGrade.setGradeTypeId(gradeTypeId);
        workerGradeService.saveWorkerGrade(workerGrade);

        return Jsonp.success();
    }


    // 修改接口
    @RequestMapping(value = "/updateMyGrade", method = RequestMethod.POST)
    public Object updateMyGrade(
            @RequestParam(name = "sid", required = true, defaultValue = "") String sid,
            @RequestParam(value = "id") Long id,
            @RequestParam(name = "name", required = true, defaultValue = "") String name,
            @RequestParam(name = "telephone", required = true, defaultValue = "") String telephone,
            @RequestParam(name = "idCard", required = true, defaultValue = "") String idCard,
            @RequestParam(name = "bankCard", required = true, defaultValue = "") String bankCard,
            @RequestParam(name = "companyName", required = true, defaultValue = "") String companyName,
            @RequestParam(name = "certificateCode", required = true, defaultValue = "") String certificateCode,
            @RequestParam(name = "certificateName", required = true, defaultValue = "") String certificateName,
            @RequestParam(name = "obtainScore", required = true, defaultValue = "") Integer obtainScore,
            @RequestParam(name = "materialsImgUrls", required = false, defaultValue = "") String materialsImgUrls,
            @RequestParam(name = "materialsImgFiles", required = false) List<MultipartFile> materialsImgFiles
    ) {



        WorkerGrade workerGrade = workerGradeService.findWorkerGradeById(id);
        if (workerGrade != null) {

            StringBuffer mymaterialsImgUrls = new StringBuffer("");

            if (materialsImgUrls != null && !materialsImgUrls.equals("")) {
                String[] materialsImgUrl = materialsImgUrls.split(",");
                for (String _materialsImgUrl : materialsImgUrl) {
                    String subMaterialsImgUrl = _materialsImgUrl.substring(_materialsImgUrl.lastIndexOf("/") + 1);
                    mymaterialsImgUrls.append(subMaterialsImgUrl).append(",");
                }
            }

            if (materialsImgFiles != null && materialsImgFiles.size() > 0) {
                try {
                    for (MultipartFile file : materialsImgFiles) {
                        // Get the file and save it somewhere
                        byte[] bytes = file.getBytes();
                        String uuid = UUID.randomUUID().toString();
                        String suffixName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
                        String fileName = uuid + suffixName;
                        Path path = Paths.get(ImagePropertiesConfig.GRADE_PATH + fileName);
                        Files.write(path, bytes);
                        mymaterialsImgUrls.append(fileName).append(",");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            workerGrade.setMaterialsUrls(mymaterialsImgUrls.toString());
            workerGrade.setName(name);
            workerGrade.setTelephone(telephone);
            workerGrade.setIdCard(idCard);
            workerGrade.setCompanyName(companyName);
            workerGrade.setBankCard(bankCard);
            workerGrade.setCertificateCode(certificateCode);
            workerGrade.setCertificateName(certificateName);
            workerGrade.setObtainScore(obtainScore);
            workerGrade.setStatus(1);
            workerGradeService.updateWorkerGrade(workerGrade);
            return Jsonp.success();
        } else {
            return Jsonp.bussiness_tips_code("未找到该数据");
        }
    }

}
