package com.wy.action.concurrent.singleton;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author wangyong
 * @Date 2019-06-22
 */
class AutoSaveEditor{
    // 文件是否被修改过
    boolean changed=false;
    // 定时任务线程池
    ScheduledExecutorService ses =
            Executors.newSingleThreadScheduledExecutor();
    // 定时执行自动保存
    void startAutoSave(){
        ses.scheduleWithFixedDelay(()->{
            autoSave();
        }, 5, 5, TimeUnit.SECONDS);
    }
    // 自动存盘操作
    void autoSave(){
        synchronized (this){
            if (!changed) {
                return;
            }
            changed = false;
        }
        // 执行存盘操作
        // 省略且实现
        this.execSave();
    }

    private void execSave() {
    }

    // 编辑操作
    void edit(){
        // 省略编辑逻辑
        change();
    }

    void change(){
        synchronized (this) {
            changed = true;
        }
    }
}
