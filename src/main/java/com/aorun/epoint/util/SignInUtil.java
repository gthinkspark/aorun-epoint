package com.aorun.epoint.util;

import com.aorun.epoint.model.WorkerEpointRecord;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 类描述:
 * Created by lg on 2020/4/15 0015.
 */
public class SignInUtil {
    /**
     * 连续签到天数
     *
     * @return int
     * @Author fangyunhe
     * @Date 2019-08-15 17:16:01
     * @Param
     **/
    public static int getContinuousSignInDay(Date starTime,List<WorkerEpointRecord> workerEpointRecordList) {
        //continuousDay 连续签到数
        int continuousDay = 1;
        boolean todaySignIn = false;
        for (int i = 0; i < workerEpointRecordList.size(); i++) {
            WorkerEpointRecord workerEpointRecord = workerEpointRecordList.get(i);
            int intervalDay = distanceDay(starTime, workerEpointRecord.getObtainTime());
            //当天签到
            if (intervalDay == 0 && i == 0) {
                todaySignIn = true;
            }
            else if (intervalDay == continuousDay) {
                continuousDay++;
            }else {
                //不连续，终止判断
                break;
            }
        }
        if (!todaySignIn) {
            continuousDay--;
        }
        return continuousDay;
    }

    /**
     * 两个日期对比间隔天数
     *
     * @param smallDay
     * @return boolean
     * @Author fangyunhe
     * @Date 2019-08-13 18:42:41
     * @Param largeDay
     **/
    private static int distanceDay(Date largeDay, Date smallDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(largeDay);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        long largeDayTime = calendar.getTimeInMillis();
        calendar.setTime(smallDay);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        long smallDayTime = calendar.getTimeInMillis();
        int day = (int) ((largeDayTime - smallDayTime) / (1000 * 60 * 60 * 24));
        return day;
    }
}
