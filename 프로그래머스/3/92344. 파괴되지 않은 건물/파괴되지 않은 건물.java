import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] imos = new int[board.length + 1][board[0].length + 1];
        
        for(int i = 0; i < skill.length; i++) {
            int[] curr = skill[i];
            int type = curr[0];
            int startRow = curr[1];
            int startCol = curr[2];
            int endRow = curr[3];
            int endCol = curr[4];
            int degree = type == 1 ? -curr[5] : curr[5];

            imos[startRow][startCol] += degree;
            imos[endRow + 1][endCol + 1] += degree;
            imos[startRow][endCol + 1] -= degree;
            imos[endRow + 1][startCol] -= degree;
        }
        
        for(int i = 0; i < imos.length; i++) {
            int now = 0;
            for(int j = 0; j < imos[0].length; j++) {
                now += imos[i][j];
                imos[i][j] = now;
            }
        }
        
        for(int j = 0; j < imos[0].length; j++) {
            int now = 0;
            for(int i = 0; i < imos.length; i++) {
                now += imos[i][j];
                imos[i][j] = now;
            }
        }
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] + imos[i][j] > 0) answer++;
            }
        }
        
        // for(int i = 0; i < imos.length; i++) {
        //     System.out.println(Arrays.toString(imos[i]));
        // }
        
        return answer;
    }
}