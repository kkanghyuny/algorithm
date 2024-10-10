import java.io.*;
import java.util.*;

class Solution {
    
    static int row, col, endR, endC, endDepth;
    static int[] dr = {1, 0, 0, -1};
    static int[] dc = {0, -1, 1, 0};
    static boolean isEnd;
    static boolean[][][] vis;
    static String answer;
    static String[] cmd = {"d", "l", "r", "u"};
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        if(Math.abs(Math.abs(x - r) + Math.abs(y - c) - k) % 2 != 0) return "impossible";
        if(Math.abs(Math.abs(x - r) + Math.abs(y - c)) > k) return "impossible";
        row = n;
        col = m;
        endR = r - 1;
        endC = c - 1;
        endDepth = k;
        isEnd = false;
        answer ="impossible";
        vis = new boolean[n][m][k + 1];
        dfs(x - 1, y - 1, 0, "");
        return answer;
    }
    
    static void dfs(int nowR, int nowC, int depth, String s) {
        if(isEnd) return;
        
        if(depth == endDepth && nowR == endR && nowC == endC) {
            answer = s;
            isEnd = true;
            return;
        } else if(depth == endDepth) {
            return;
        }
        
        vis[nowR][nowC][depth] = true;
        
        for(int d = 0; d < 4; d++) {
            int nr = nowR + dr[d];
            int nc = nowC + dc[d];
            
            if(!checkBoundary(nr, nc)) continue;
            if(vis[nr][nc][depth + 1]) continue;
            dfs(nr, nc, depth + 1, s + cmd[d]);
        }
    }
    
    static boolean checkBoundary(int r, int c) {
        return r >= 0 && r < row && c >= 0 && c < col;
    }
}