import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[] maps) {
        int n = maps.length;
        int m = maps[0].length();
        char[][] arr = new char[n][m];
        boolean[][][] vis = new boolean[n][m][2];
        Queue<int[]> queue = new ArrayDeque<>();
        
        int startR = 0;
        int startC = 0;
        int levR = 0;
        int levC = 0;
        int endR = 0;
        int endC = 0;
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                arr[i][j] = maps[i].charAt(j);
                if(arr[i][j] == 'S') {
                    startR = i;
                    startC = j;
                }
                if(arr[i][j] == 'L') {
                    levR = i;
                    levC = j;
                }
                if(arr[i][j] == 'E') {
                    endR = i;
                    endC = j;
                }
            }
        }
        
        queue.offer(new int[] {startR, startC, 0, 0});
        vis[startR][startC][0] = true;
        
        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int dist = curr[2];
            int isTurnLever = curr[3];
            if(r == levR && c == levC) isTurnLever = 1;
            
            if (r == endR && c == endC && isTurnLever == 1) {
                return dist;
            }

            for(int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if(nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                if(arr[nr][nc] == 'X') continue;
                if(vis[nr][nc][isTurnLever]) continue;
                
                queue.offer(new int[] {nr, nc, dist + 1, isTurnLever});
                vis[nr][nc][isTurnLever] = true;
            }
        }
        
        return -1;
    }
}