package com.wy.action.algorithm.graph;

public class RegionsBySlashes {
    /**
     * 求斜线把小方格化成的区域
     */
    public int regionsBySlashes(String[] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int n = grid.length;
        UnionFind unionFind = new UnionFind(4*n*n);

        for(int i=0; i< n; i++) {
            char[] ch = grid[i].toCharArray();

            for(int j=0; j< n; j++) {
                char c =  ch[j];
                int index = 4*(i*n+j);
                if (c == '\\') {
                    unionFind.union(index,index+1);
                    unionFind.union(index+2,index+3);
                } else if(c == '/') {
                    unionFind.union(index,index+3);
                    unionFind.union(index+1,index+2);
                } else if (c == ' ') {
                    unionFind.union(index,index+1);
                    unionFind.union(index+1,index+2);
                    unionFind.union(index+2,index+3);
                }
                // 向右合并
                if (j+1 <n) {
                    unionFind.union(index+1, 4*(i*n+j+1)+3);
                }
                if (i+1 < n) {
                    unionFind.union(index+2, 4*n*(i+1));
                }
            }

        }
        return unionFind.count;

    }

    public static class UnionFind {
        /**
         * 并查集， 用来解决元素分类的问题
         */
        int[] parent ;
        int count ;
        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            for(int i=0; i<n; i++) {
                parent[i] = i;
            }
        }

        /**
         * 查找元素的祖先
         * @param x
         * @return
         */
        int find(int x) {
            if(parent[x] == x)
                return x;
            else
                return find(parent[x]);
        }

        public int findV2(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y) {
           int findX = findV2(x);
           int findY = findV2(y);


           if (findX == findY) {
                return;
           }
            // 如果两个值不相等，表示处于同一区域内
            parent[findX] = findY;
           count--;
        }

    }
}
