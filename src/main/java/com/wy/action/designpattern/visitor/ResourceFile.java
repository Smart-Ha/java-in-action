package com.wy.action.designpattern.visitor;

/**
 * 资源抽奖类
 * @Author wangyong
 * @Date 2020-04-14
 */
public abstract class ResourceFile {
    protected String filePath;

    public ResourceFile(String filePath) {
        this.filePath = filePath;
    }

    abstract void accept(IVisitor visitor);
}
