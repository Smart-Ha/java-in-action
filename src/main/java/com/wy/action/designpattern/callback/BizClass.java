package com.wy.action.designpattern.callback;

import com.wy.action.entity.Node;

import java.util.function.Consumer;

/**
 * @Author wangyong
 * @Date 2020-04-09
 */
public class BizClass {
    public void execute(ICallBack callBack) {
        // do something
        callBack.call();
    }


    public void execute(Consumer<Node> callBack) {
        callBack.accept(new Node(1));
    }
}
