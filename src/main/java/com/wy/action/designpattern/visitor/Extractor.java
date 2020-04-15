package com.wy.action.designpattern.visitor;

/**
 * 转换
 * @Author wangyong
 * @Date 2020-04-14
 */
public class Extractor implements IVisitor {

    @Override
    public void accept(PPTFile file) {
        System.out.println("ppt trans to txt");
    }

    @Override
    public void accept(ExcelFile file) {
        System.out.println("excel trans to txt");
    }
}
