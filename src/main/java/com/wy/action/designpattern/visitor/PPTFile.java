package com.wy.action.designpattern.visitor;

/**
 * @Author wangyong
 * @Date 2020-04-14
 */
public class PPTFile extends ResourceFile {
    public PPTFile(String filePath) {
        super(filePath);
    }

    @Override
    void accept(IVisitor visitor) {
        visitor.accept(this);
    }
}
