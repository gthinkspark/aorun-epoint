package com.aorun.epoint.util.biz;


import com.aorun.epoint.controller.login.UserDto;
import com.aorun.epoint.controller.login.WorkerUnionInfoDto;
import com.aorun.epoint.model.WorkerMember;
import com.aorun.epoint.util.redis.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author 作者 duxihu
 */
public class WorkerMemberUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerMemberUtil.class);

    public static Long getWorkerId(
            String sid) {
        LOGGER.info("sid------>{}",sid);
        UserDto user = (UserDto) RedisCache.get(sid);
        if(LOGGER.isDebugEnabled()){
            if(user==null){
                LOGGER.debug("从缓存中拿sid出错 {}",sid);
            }else {
                LOGGER.debug("从缓存中拿sid---正确{}",sid);
            }
        }
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
