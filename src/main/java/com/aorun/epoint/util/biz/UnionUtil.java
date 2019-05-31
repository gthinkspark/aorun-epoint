package com.aorun.epoint.util.biz;


import com.aorun.epoint.controller.login.UserDto;

/**
 * @author 作者 LG
 * @data 创建时间：2019年3月19日 上午10:43:01
 * 类说明
 */
public class UnionUtil {
    private static final String unionSidPrefix = "GH_";

    public static String generateUnionSid(
            UserDto userDto) {
        return unionSidPrefix + userDto.getSid();
    }

    public static String generateWorkrtUnionExtraInfoCode(String workerId){
        return unionSidPrefix+"worker_union_info_dto_"+workerId;
    }

}
