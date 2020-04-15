package com.wy.action.designpattern.visitor;

/**
 * @Author wangyong
 * @Date 2020-04-14
 */
public interface IVisitor {
    void accept(PPTFile file);
    void accept(ExcelFile file);
}
