package com.aorun.epoint.util.biz.unioninfo;

import java.util.List;

/**
 * 类描述:
 * Created by duxihu on 2019/6/3 0003.
 */
public class UnionInfo {

    private String workerName;
    private String checkTime;
    private String unionName;
    private List<WorkerId> workerUnionList;

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getUnionName() {
        return unionName;
    }

    public void setUnionName(String unionName) {
        this.unionName = unionName;
    }

    public List<WorkerId> getWorkerUnionList() {
        return workerUnionList;
    }

    public void setWorkerUnionList(List<WorkerId> workerUnionList) {
        this.workerUnionList = workerUnionList;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }


}
