package com.wy.action.concurrent.async2sync;

/**
 * 异步转同步
 * @Author wangyong
 * @Date 2019-06-22
 */
public class GuardedObjectApp {
    /**
     * 同步转异步的场景使用
     */

    // 处理浏览器发来的请求
    Message handleWebReq(){
        long id = 1L;
        // 创建一消息
        Message msg1 = new Message(id, "");
        // 发送消息
        send(msg1);
        // 利用 GuardedObject 实现等待
        GuardedObject<Message> go
                = GuardedObject.create(id);
        Message r = go.get( t->t != null);
        return r;
    }

    private void send(Message msg1) {
    }

    void onMessage(Message msg){
        GuardedObject.onFire(msg.getKey(), msg);
    }

}
