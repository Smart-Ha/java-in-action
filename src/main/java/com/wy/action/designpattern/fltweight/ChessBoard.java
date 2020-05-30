package com.wy.action.designpattern.fltweight;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author wangyong
 * @Date 2020-04-15
 */
public class ChessBoard {
    private Map chessPieces = new HashMap<>();

    public ChessBoard() {
        init();
    }

    private void init() {
        chessPieces.put(1, new ChessPiece(ChessPieceUnitFactory.getChessPiece(1), 0, 0));
        chessPieces.put(1, new ChessPiece(ChessPieceUnitFactory.getChessPiece(2), 1, 0)); //...省略摆放其他棋子的代码...
    }

    public void move(int chessPieceId, int toPositionX, int toPositionY) { //...省略... }}

    }

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        System.out.println(String.join(",",set));
        set.add("1");
        System.out.println(String.join(",",set));
        set.add("2");
        System.out.println(String.join(",",set));
    }
}