package com.wy.action.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 分治算法实例
 * @Author wangyong
 * @Date 2019-12-07
 */
public class MapReduceApp {

    public static class Point {
        public int x;
        public int y;
    }





    /**
     * 1.找出二维平面中距离最近的两个点
     * @param points
     * @return
     */
    public List<Point> getClosestPoint(List<Point> points) {
        if (points == null || points.size()<2) {
            return points;
        }
        List<Point> result = new ArrayList<>();
        mergeSort(points,result, 0,points.size()-1);
        return result;
    }

    private void mergeSort(List<Point> points, List<Point> result, int low,  int high) {
        if (low >= high) {
            return;
        }
        int mid = (low + high) / 2;
        mergeSort(points, result, low, mid);
        mergeSort(points, result, mid+1, high);
//        merge();
    }

    /**
     * 2.有两个 n*n 的矩阵 A，B，如何快速求解两个矩阵的乘积 C=A*B？
     */
}
