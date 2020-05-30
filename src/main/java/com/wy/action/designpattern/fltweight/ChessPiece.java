package com.wy.action.designpattern.fltweight;

/**
 * @Author wangyong
 * @Date 2020-04-15
 */
public class ChessPiece {
    private ChessPieceUnit chessPieceUnit;
    private int positionX;
    private int positionY;

    public ChessPiece(ChessPieceUnit unit, int positionX, int positionY) {
        this.chessPieceUnit = unit;
        this.positionX = positionX;
        this.positionY = positionY;
    } // 省略getter、setter方法}
}
