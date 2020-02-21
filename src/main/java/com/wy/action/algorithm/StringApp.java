package com.wy.action.algorithm;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author wangyong
 * @Date 2020-02-18
 */
public class StringApp {

    /**
     * 判断是否为回文数
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        String one = x+"";
        StringBuilder two = new StringBuilder(one).reverse();
        System.out.println(two.toString());
        return  one.equals(two.toString());
    }

    @Test
    public void isPalindromeTest() {
        Assert.assertEquals(true, isPalindrome(121));
        Assert.assertEquals(false, isPalindrome(-121));
        Assert.assertEquals(false, isPalindrome(212221));
    }

    /**
     * int转罗马数字，4是IV(5-1) 9是IX(10-1)
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * Input: 1994
     * Output: "MCMXCIV"
     */
    public String intToRoman(int num) {
        String result = "";
        int m = num/1000;
        while (m-->0){
            result+="M";
        }
        m = (num %1000)/100;
        result = add(result,m,"D", "M", "C");
        m =  (num%100)/10;
        result = add(result,m,"L", "C", "X");
        m = num%10;
        result = add(result,m,"V", "X", "I");
        return result;
    }

    private String add(String result, int m,String five, String ten, String one) {
        if(m>0) {
            if(m==5) {
                result+= five;
            } else if(m<5){
                if (m == 4) {
                    result += one + five;
                }else {
                    while (m-->0) {
                        result += one;
                    }
                }
            } else {
                if (m == 9) {
                    result += one + ten;
                } else {
                    result += five;
                    m -= 5;
                    while (m-->0) {
                        result += one;
                    }
                }
            }
        }
        return result;
    }

    @Test
    public void intToRomanTest() {
        Assert.assertEquals("MCMXCIV",intToRoman(1994));
        Assert.assertEquals("III",intToRoman(3));
        Assert.assertEquals("IV",intToRoman(4));
        Assert.assertEquals("IX",intToRoman(9));
        Assert.assertEquals("LVIII",intToRoman(58));
    }

    /**
     * 判断是否为数独
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Set<Character>> rows = new HashMap<>();
        Map<Integer, Set<Character>> cols = new HashMap<>();
        Map<String, Set<Character>> subBoxes = new HashMap<>();

        for (int row = 0; row < board.length; row++)
        {
            for (int col = 0; col < board[0].length; col++)
            {
                if (board[row][col] != '.')
                {
                    Character c = board[row][col];
                    if (rows.get(row) != null && rows.get(row).contains(c))
                        return false;
                    rows.putIfAbsent(row, new HashSet<>());
                    rows.get(row).add(c);

                    if (cols.get(col) != null && cols.get(col).contains(c))
                        return false;
                    cols.putIfAbsent(col, new HashSet<>());
                    cols.get(col).add(c);

                    String keySubBox = String.valueOf(row / 3) + String.valueOf(col / 3);
                    if (subBoxes.get(keySubBox) != null && subBoxes.get(keySubBox).contains(c))
                        return false;
                    subBoxes.putIfAbsent(keySubBox, new HashSet<>());
                    subBoxes.get(keySubBox).add(c);
                }
            }
        }

        return true;
    }

    @Test
    public void isValidSudokuTest() {
         char[][] one =    {
                 {'5','3','.','.','7','.','.','.','.'},
                 {'6','.','.','1','9','5','.','.','.'},
                 {'.','9','8','.','.','.','.','6','.'},
                 {'8','.','.','.','6','.','.','.','3'},
                 {'4','.','.','8','.','3','.','.','1'},
                 {'7','.','.','.','2','.','.','.','6'},
                 {'.','6','.','.','.','.','2','8','.'},
                 {'.','.','.','4','1','9','.','.','5'},
                 {'.','.','.','.','8','.','.','7','9'}
         };

         char[][] two = {
                 {'8','3','.','.','7','.','.','.','.'},
                 {'6','.','.','1','9','5','.','.','.'},
                 {'.','9','8','.','.','.','.','6','.'},
                 {'8','.','.','.','6','.','.','.','3'},
                 {'4','.','.','8','.','3','.','.','1'},
                 {'7','.','.','.','2','.','.','.','6'},
                 {'.','6','.','.','.','.','2','8','.'},
                 {'.','.','.','4','1','9','.','.','5'},
                 {'.','.','.','.','8','.','.','7','9'}
         };

         Assert.assertEquals(true, isValidSudoku(one));
         Assert.assertEquals(false, isValidSudoku(two));
    }

}
