package com.athome;

import com.sun.xml.internal.ws.spi.db.DatabindingException;
import netscape.javascript.JSObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/11/22 15:29
 * @Version 1.0
 */
public class Demo {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {

        Map<String,Object> map = new HashMap<>();
        ThreadLocal<Map> local = new InheritableThreadLocal<>();
        local.set(map);

        local.get().put("zhangsan","lisi");

        System.out.println(local.get());


        Date endTime = new Date();
        Date startTime = DateUtils.addHours(endTime, -3);
        System.out.println("开始时间："+startTime+"  结束时间："+endTime);

        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");
        instance.setTimeZone(timeZone);
        instance2.setTimeZone(timeZone);


        instance.setTime(startTime);
        instance2.setTime(endTime);
        System.out.println("开始时间："+instance+"  结束时间："+instance2);
        System.out.println(instance.get(Calendar.YEAR));
        System.out.println(instance.get(Calendar.MONTH)+1);
        System.out.println(instance.get(Calendar.DATE));


        System.out.println(instance2.get(Calendar.YEAR));
        System.out.println(instance2.get(Calendar.MONTH)+1);
        System.out.println(instance2.get(Calendar.DATE));

        List<Date[]> dates = splitDates(startTime, endTime);

        for (Date[] date : dates) {
            for (int i = 0; i < date.length; i++) {
                System.out.println(dateFormat.format(date[i]));
            }

            System.out.println("结束");
        }
    }

    /**
     * 将时间段按天拆分
     *
     * @param start
     * @param end
     * @return
     */
    public static List<Date[]> splitDates(Date start, Date end) {
        List<Date[]> dateList = new ArrayList<Date[]>();
        if (start == null || end == null) {
            return dateList;
        }
        Calendar calDate = Calendar.getInstance();
        Date timeStart = null;
        Date tempTime = start;
        while (tempTime.getTime() < end.getTime()) {
            timeStart = tempTime;
            calDate.setTime(tempTime);
            calDate.set(Calendar.HOUR_OF_DAY, 24);
            calDate.set(Calendar.MINUTE, 0);
            calDate.set(Calendar.SECOND, 0);
            tempTime = calDate.getTime();
            if (tempTime.getTime() > end.getTime()) {
                dateList.add(new Date[] {timeStart, end});
            } else {
                dateList.add(new Date[] {timeStart, tempTime});
            }
        }
        return dateList;
    }


}
