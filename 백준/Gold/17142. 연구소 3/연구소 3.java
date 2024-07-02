import java.awt.*;
import java.util.*;
import java.io.*;

public class Main {

    static class Virus {
        int r;
        int c;
        int time;
        boolean inactive;

        public Virus(int r, int c, int time, boolean inactive){
            this.r = r;
            this.c = c;
            this.time = time;
            this.inactive = inactive;
        }
    }

    static int n, m, count, virusCnt, answer;
    static Point[] virus;
    static boolean[] vis;
    static boolean[][] visited;
    static Queue<Virus> queue;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        count = 0;
        answer = Integer.MAX_VALUE;

        visited = new boolean[n][n];
        queue = new ArrayDeque<>();
        virusCnt = 0;
        int virusIdx = 0;

        arr = new int[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 2) {
                    virusCnt++;
                    count++;
                }

                if(arr[i][j] == 0) count++;
            }
        }
        virus = new Point[virusCnt];
        vis = new boolean[virusCnt];
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 2) {
                    virus[virusIdx++] = new Point(i, j);
                }
            }
        }

        for(int i = 0; i < virusCnt; i++){
            vis[i] = true;
            perm(i + 1, 1);
            vis[i] = false;
        }

        if(answer == Integer.MAX_VALUE){
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    static void perm(int idx, int depth){
        if(depth == m){
            answer = Math.min(answer, bfs());
            return;
        }

        for(int i = idx; i < virusCnt; i++){
            if(vis[i]) continue;
            vis[i] = true;
            perm(i+1, depth+1);
            vis[i] = false;
        }
    }

    static int bfs(){
        int maxTime = 0;
        visited = new boolean[n][n];
        int cnt = 0;

        for(int i = 0; i < virusCnt; i++){
            if(vis[i]) {
                queue.offer(new Virus(virus[i].x, virus[i].y, 0, false));
                visited[virus[i].x][virus[i].y] = true;
            }
            cnt++;
        }

        while(!queue.isEmpty()){
            Virus curr = queue.poll();
            int r = curr.r;
            int c = curr.c;
            int time = curr.time;
            boolean inactive = curr.inactive;
            if(!inactive) {
                maxTime = Math.max(maxTime, time);
            }

            if(arr[r][c] != 2) cnt++;

            for(int d = 0; d < 4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
                if(arr[nr][nc] == 1) continue;
                if(visited[nr][nc]) continue;
                visited[nr][nc] = true;

                if(arr[nr][nc] == 2){
                    queue.offer(new Virus(nr, nc, time + 1, true));
                } else {
                    queue.offer(new Virus(nr, nc, time + 1, false));
                }
            }
        }

        if(cnt != count){
            return answer;
        }
        return maxTime;
    }
}
