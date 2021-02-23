package com.wy.action.algorithm;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

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

    /**
     * 对包含相同字符的字符串分组
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(int i=0;i<strs.length;i++) {
            int[] arr = new int[26];
            for( int j=0;j<strs[i].length(); j++) {
                arr[strs[i].charAt(j)-'a'] ++;
            }
            String key = Arrays.toString(arr);
            List<String> one = map.get(key);
            if (one == null) {
                one = new ArrayList<>();
            }
            one.add(strs[i]);
            map.put(key, one);
        }
        return new ArrayList<>(map.values());
    }

    @Test
    public void test() {
        String[] arr = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(arr));
    }

    /**
     * 求最后一个单词的长度
     *https://leetcode.com/problems/length-of-last-word/
     */
    public int lengthOfLastWord(String str) {
        int len = str.length();
        for(int i=len-1; i>=0;i--) {
            if (str.charAt(i)==' ') {
                len--;
            } else {
                break;
            }
        }
        str = str.substring(0, len);
        int index = str.lastIndexOf(" ");
        return str.length()-index-1;
    }

    @Test
    public void lengthOfLastWordTest() {
//        Assert.assertEquals(5, lengthOfLastWord("hello world"));
//        Assert.assertEquals(1, lengthOfLastWord("a "));
//        Assert.assertEquals(1, lengthOfLastWord("b   a    "));
        Random random = new Random();
        for(int i=0; i<100; i++) {

            System.out.println(random.nextFloat());;
        }
    }

    /**
     * 必要时请添加多余的空格''，以使每行都具有完全maxWidth个字符。
     * https://leetcode.com/problems/text-justification/
     * @param words
     * @param maxWidth
     * @return
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        int start=0,  end=0;
        int sum = 0;
        int blank, extra ,addB;
        List<String> result = new ArrayList<>();
        for(int i=0; i< words.length;i++){
            end = i;
            if (sum + words[i].length() + (end-start) > maxWidth) {
                //超过了长度, maxWidth-单词的长度，然后在均分空格
                end--;
                if (end-start == 0) {
                    blank = maxWidth - words[i].length();
                    extra = 0;
                } else {
                    blank = (maxWidth - sum)/(end-start);
                    extra = (maxWidth - sum)%(end-start);
                }

                StringBuilder sb = new StringBuilder();
                for(int j=start; j<=end; j++) {
                    sb.append(words[j]);
                    if (j== end) {
                        extra = 0;
                        addB = maxWidth-sb.length();
                    } else {
                        addB = blank;
                    }

                    if(extra>0) {
                        addB++;
                        extra--;
                    }
                    while (addB-- > 0 ){
                        sb.append(" ");
                    }
                }
                result.add(sb.toString());
                start = i;
                end = i;
                sum = words[i].length();
            } else {
                sum += words[i].length();

            }
        }
        if (end ==words.length-1) {
            StringBuilder sb = new StringBuilder();
            for(int j=start; j<=end; j++) {
                sb.append(words[j]);
                if (j== end) continue;
                sb.append(" ");
            }
            while (maxWidth - sb.length()>0) {
                sb.append(" ");
            }
            result.add(sb.toString());
        }
        return result;
    }
    @Test
    public void fullJustifyTest() {
        System.out.println(fullJustify(new String[]{
                "This", "is", "an", "example", "of", "text", "justification."}, 16));
        System.out.println(fullJustify(new String[]{
                "What","must","be","acknowledgment","shall","be"}, 16));
        System.out.println(fullJustify(new String[]{
                "Science","is","what","we","understand","well","enough","to","explain",
                "to","a","computer.","Art","is","everything","else","we","do"}, 20));
    }

    /**
     * 求包含字符串t的，s的最小子串
     * https://leetcode.com/problems/minimum-window-substring/
     * @param s
     * @param t
     * @retu
     */
    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>(t.length());
        char c;
        for(int i=0; i<t.length(); i++){
            c = t.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c)+1);
            }else {
                map.put(c, 1);
            }
        }
        int left=0, minLeft=0;
        int count=0, minLen = s.length()+1;
        for (int i=0; i<s.length(); i++) {
            c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c)-1);

                if (map.get(c) >= 0 ) {
                    count++;
                }

                while (count == t.length()) {// 窗口移动，比较最小值
                    if (i -left +1 < minLen) {
                        minLeft = left;
                        minLen =  i - left+1;
                    }
                    if (map.containsKey(s.charAt(left))) {// 修改left的值
                        map.put(s.charAt(left), map.get(s.charAt(left))+1);//使移动抛弃的left+1>=0, 使count可以增加
                        if (map.get(s.charAt(left))>0) {// 去掉重复的值
                            count--;
                        }
                    }
                    left++;

                }

            }
        }
        if (minLen > s.length()) {
            return "";
        }
        return s.substring(minLeft, minLeft+minLen);
    }

    @Test
    public void minWindowTest() {
        Assert.assertEquals("BANC", minWindow("ADOBECODEBANC","ABC"));
    }

    /**
     * 检查单词在数组中是否存在（必须是连续的）
     * https://leetcode.com/problems/word-search/
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        for( int i=0; i< board.length; i++) {
            for (int j=0; j< board[0].length; j++) {
                if (dfs(board, i,j,word, 0)) {
                    return true;
                }

            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, String word, int count) {
        if (count == word.length()) {
            return true;
        }

        if (i<0 || i>= board.length || j<0 || j>=board[0].length || board[i][j] != word.charAt(count)) {
            return false;
        }
        board[i][j] = '-';//
        boolean wordExist = dfs(board,i-1,j, word, count+1) ||
            dfs(board, i+1, j, word, count+1) ||
            dfs(board, i, j-1, word, count+1) ||
            dfs(board, i, j+1, word, count+1);
        board[i][j] = word.charAt(count);
        return wordExist;
    }

    @Test
    public void existTest() {
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        Assert.assertEquals(true, exist(board,"ABCCED"));
        Assert.assertEquals(true, exist(board,"SEE"));
        Assert.assertEquals(false, exist(board,"ABCB"));
    }

    /**
     * 判断是为回文字符串
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int low = 0;
        int high = s.length()-1;
        while (low < high) {
            char lowC = s.charAt(low);
            char highC = s.charAt(high);
            if (lowC == highC) {
                low++;
                high--;
                continue;
            }
            if(!isValidChar(lowC)) {
                low++;
            } else if(!isValidChar(highC)) {
                high--;
            } else {
                return false;
            }
        }

        return true;


    }

    private boolean isValidChar(char lowC) {
        return (lowC>='0' && lowC<='9') || lowC>='a' && lowC<='z';
    }

    @Test
    public void isPalindromeTest2() {
        Assert.assertEquals(true, isPalindrome("A man, a plan, a canal: Panama"));
    }

    /**
     * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
     * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     * https://leetcode-cn.com/problems/surrounded-regions/
     * @param board
     */
    public void solve(char[][] board) {
        /**
         * 找出所有与边缘O直接或者间接关联的O，并标记。
         * 将没有标记的O置为X
         */
        int n = board.length;
        if (n == 0) {
            return;
        }
        int m = board[0].length;

        for(int i=0; i<m; i++) {
            //第0行
            dfsSolve(board, n,m,0,i);
            // 第n-1行
            dfsSolve(board, n,m,n-1,i);
        }
        for(int i=0; i<n; i++) {
            //第0行
            dfsSolve(board, n,m,i,0);
            // 第n-1行
            dfsSolve(board, n,m,i,m-1);
        }

        for(int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (board[i][j] =='O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                }
            }
        }

    }

    private void dfsSolve(char[][] board, int n, int m, int row, int col) {
        if (row>=n || row<0 || col>=m || col<0 || board[row][col]!='O') {
            return;
        }
        board[row][col] = 'A';
        dfsSolve(board,n,m, row-1, col);
        dfsSolve(board,n,m, row+1, col);
        dfsSolve(board,n,m, row, col-1);
        dfsSolve(board,n,m, row, col+1);

    }
}
