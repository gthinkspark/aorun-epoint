package com.aorun.epoint.controller;


import com.aorun.epoint.model.WorkerServiceSite;
import com.aorun.epoint.service.WorkerServiceSiteService;
import com.aorun.epoint.util.jsonp.Jsonp_data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务站点
 */
@RequestMapping("/statistics")
@RestController
public class WorkerServiceSiteRestController {

    @Autowired
    private WorkerServiceSiteService workerServiceSiteService;

    //服务站点
    @RequestMapping(value = "/serviceSite", method = RequestMethod.GET)
    public Object serviceSite(@RequestParam(name = "searchName", required = true, defaultValue = "") String searchName) {

        List<Map<String, List<WorkerServiceSite>>> datamapList = new ArrayList<Map<String, List<WorkerServiceSite>>>();
        List<WorkerServiceSite>  workerServiceSiteList = workerServiceSiteService.getParentSiteList();
        for (int i = 0; i <workerServiceSiteList.size() ; i++) {
            Long id = workerServiceSiteList.get(i).getId();
            String name = workerServiceSiteList.get(i).getName();
            String address = workerServiceSiteList.get(i).getAddress();
            if(searchName!=null&&!searchName.equals("")){
                if(name.contains(searchName)||address.contains(searchName)){

                    int totalSize = 0 ;
                    List<WorkerServiceSite>  workerServiceSiteList2 = workerServiceSiteService.getWorkerServiceSiteListByParentId(id);
                    List<WorkerServiceSite>  newworkerServiceSiteList = new ArrayList<>();
                    for (int j = 0; j < workerServiceSiteList2.size(); j++) {
                        String newname = workerServiceSiteList.get(i).getName();
                        String newaddress = workerServiceSiteList.get(i).getAddress();
                        if(newname.contains(searchName)||newaddress.contains(searchName)){
                            totalSize++;
                            newworkerServiceSiteList.add( workerServiceSiteList.get(i));
                        }
                    }

                    String key = name+"-"+totalSize;
                    Map<String, List<WorkerServiceSite>> datamap = new HashMap<>();

                    datamap.put(key,newworkerServiceSiteList);
                    datamapList.add(datamap);
                }
            }else{
                List<WorkerServiceSite>  workerServiceSiteList2 = workerServiceSiteService.getWorkerServiceSiteListByParentId(id);
                int totalSize = workerServiceSiteList2.size();
                String key = name+"-"+totalSize;
                Map<String, List<WorkerServiceSite>> datamap = new HashMap<>();

                datamap.put(key,workerServiceSiteList2);
                datamapList.add(datamap);
            }

        }

        return Jsonp_data.success(datamapList);
    }


    //服务站点
    @RequestMapping(value = "/totalServiceSite", method = RequestMethod.GET)
    public Object serviceSite() {

       int totalServiceSite =  workerServiceSiteService.totalWorkerServiceSite();
        return Jsonp_data.success(totalServiceSite);
    }

}
