package com.wy.action.concurrent.threadlocal;

import com.wy.action.concurrent.ThreadEngine;

import java.security.GuardedObject;
import java.util.concurrent.ExecutorService;

/**
 * @Author wangyong
 * @Date 2019-06-22
 */
public class ThreadLocalApp {
    /**
     * 1.在线程池中使用ThreadLocal可能导致内存泄露
     * 原因： 线程池中的核心线程都是和程序同生共死的，这就意味着Thread所持有的TheadLocalMap一直不会被回收。
     * 在加上ThreadLocalMap中的Entry对ThreadLocal是弱引用，所以只要ThreadLocal结束了自己的生命周期是可以被回收掉的。但是Entry中的Value确实被Entry强引用的
     * 所以即便是Value的生命周期结束了，Value也是无法被回收的，从而导致内存泄露。 但是我们可以手动释放
     *
     * 2.通过ThreadLocal创建的变量子线程是无法继承的
     */

    public static void demo(){
        ExecutorService es = ThreadEngine.createThreadPool(5,10, 200);
        ThreadLocal<Long> tl = new ThreadLocal();
        es.execute(()->{
            //ThreadLocal 增加变量
            tl.set(0L);
            try {
                // 省略业务逻辑代码
            }finally {
                // 手动清理 ThreadLocal
                tl.remove();
            }
        });

    }
}
