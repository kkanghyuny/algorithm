import java.io.*;
import java.util.*;

class Solution {
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        boolean[][] vis;
        Queue<int[]> queue = new ArrayDeque<>();
        
        for(int t = 0; t < 5; t++) {
            char[][] arr = new char[5][5];
            int ans = 1;
            
            for(int i = 0; i < 5; i++) {
                String s = places[t][i];
                for(int j = 0; j < 5; j++) {
                    arr[i][j] = s.charAt(j);
                }
            }
            
            a: for(int i = 0; i < 5; i++) {
                for(int j = 0; j < 5; j++) {
                    if(arr[i][j] == 'P'){
                        vis = new boolean[5][5];
                        queue.clear();
                        queue.offer(new int[] {i, j, 0});
                        
                        while(!queue.isEmpty()) {
                            int[] curr = queue.poll();
                            int r = curr[0];
                            int c = curr[1];
                            int cnt = curr[2];
                            vis[r][c] = true;
                            
                            for(int d = 0; d < 4; d++) {
                                int nr = r + dr[d];
                                int nc = c + dc[d];
                                
                                if(nr < 0 || nr >= 5 || nc < 0 || nc >= 5) continue;
                                if(vis[nr][nc]) continue;
                                
                                if(cnt == 0 && arr[nr][nc] == 'O') {
                                    queue.offer(new int[] {nr, nc, 1});
                                } else if(cnt <= 1 && arr[nr][nc] == 'P') {
                                    ans = 0;
                                    break a;
                                }
                            }
                        }
                    }
                }
            }
            answer[t] = ans;
        }
        return answer;
    }
}