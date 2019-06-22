package com.wy.action.concurrent.threadlocal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 线程安全的DateFormat 使用TheadLocal 保证不同线程不共享SimpleDateFormat
 * @Author wangyong
 * @Date 2019-06-22
 */
public class SafeDataFomat {

    private static final ThreadLocal<DateFormat> tl = ThreadLocal.withInitial(
            () -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    public static DateFormat getDateFormat(){
        return tl.get();
    }

}
