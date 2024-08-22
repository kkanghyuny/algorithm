import java.util.*;
import java.io.*;
import java.awt.*;

class Solution {
    public int solution(int[][] land) {
        int answer = 0;
        int r = land.length;
        int c = land[0].length;
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        boolean[][] vis = new boolean[r][c];
        int[][] group = new int[r][c];
        Map<Integer, Integer> map = new HashMap<>();
        
        Queue<Point> queue = new ArrayDeque<>();
        int groupNum = 1;
        
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(land[i][j] == 1 && !vis[i][j]) {
                    vis[i][j] = true;
                    Point p = new Point(i, j);
                    queue.offer(p);
                    int cnt = 1;
                    groupNum++;
                
                    while(!queue.isEmpty()) {
                        Point curr = queue.poll();
                        int x = curr.x;
                        int y = curr.y;
                        land[x][y] = groupNum;

                        for(int d = 0; d < 4; d++) {
                            int nr = x + dr[d];
                            int nc = y + dc[d];

                            if(nr < 0 || nr >= r || nc < 0 || nc >= c || vis[nr][nc]) continue;
                            if(land[nr][nc] == 1) {
                                vis[nr][nc] = true;
                                Point np = new Point(nr, nc);
                                queue.offer(np);
                                cnt++;
                            }
                        }
                    }
                    map.put(groupNum, cnt);
                }
            }
        }
        
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < c; i++) {
            int count = 0;
            set.clear();
            for(int j = 0; j < r; j++) {
                if(land[j][i] != 0 && !set.contains(land[j][i])) {
                    set.add(land[j][i]);
                    count += map.get(land[j][i]);
                }
            }
            answer = Math.max(count, answer);
        }
    
        
        return answer;
    }
}