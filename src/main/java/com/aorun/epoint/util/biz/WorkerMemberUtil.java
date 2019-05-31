package com.aorun.epoint.util.biz;


import com.aorun.epoint.controller.login.UserDto;
import com.aorun.epoint.controller.login.WorkerUnionInfoDto;
import com.aorun.epoint.model.WorkerMember;
import com.aorun.epoint.util.redis.RedisCache;


/**
 * @author 作者 duxihu
 */
public class WorkerMemberUtil {

    public static Long getWorkerId(
            String sid) {
        UserDto user = (UserDto) RedisCache.get(sid);
        WorkerMember workerMember = RedisCache.getObj(UnionUtil.generateUnionSid(user), WorkerMember.class);
        return workerMember.getId();
    }

    public static WorkerMember getWorkerMember(
            String sid) {
        UserDto user = (UserDto) RedisCache.get(sid);
        WorkerMember workerMember = RedisCache.getObj(UnionUtil.generateUnionSid(user), WorkerMember.class);
        return workerMember;
    }


    public static WorkerUnionInfoDto getWorkerMember(
            Long  workerId) {
        WorkerUnionInfoDto workerUnionInfoDto = RedisCache.getObj(UnionUtil.generateWorkrtUnionExtraInfoCode(workerId+""),WorkerUnionInfoDto.class);
        return workerUnionInfoDto;
    }


}
