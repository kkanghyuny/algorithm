import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    static class Point implements Comparable<Point>{
        int r;
        int c;
        int change;
        int dir;

        public Point(int r, int c, int change, int dir) {
            this.r = r;
            this.c = c;
            this.change = change;
            this.dir = dir;
        }

        public int compareTo(Point p) {
            return this.change - p.change;
        }
    }

    // 북 동 남 서
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int c = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        char[][] arr = new char[r][c];
        boolean check = false;
        int[][] vis = new int[r][c];
        int[][] visCnt = new int[r][c];
        int startR = 0;
        int startC = 0;
        int goalR = 0;
        int goalC = 0;
        int answer = 0;

        for(int i = 0; i < r; i++){
            Arrays.fill(vis[i], 987654321);
        }

        for(int i = 0; i < r; i++){
            String s = br.readLine();
            for(int j = 0; j < c; j++){
                arr[i][j] = s.charAt(j);
                if(arr[i][j] == 'C' && !check){
                    check = true;
                    startR = i;
                    startC = j;
                } else if(arr[i][j] == 'C' && check){
                    goalR = i;
                    goalC = j;
                } else if(arr[i][j] == '*') {
                    vis[i][j] = -1;
                }
            }
        }

        PriorityQueue<Point> pq = new PriorityQueue<>();
        vis[startR][startC] = -1;

        for(int d = 0; d < 4; d++){
            int nr = startR + dr[d];
            int nc = startC + dc[d];

            if(nr < 0 || nr >= r || nc < 0 || nc >= c) continue;

            pq.offer(new Point(nr, nc, 0, d));
        }

        while(!pq.isEmpty()){
            Point curr = pq.poll();
            if(vis[curr.r][curr.c] < curr.change) continue;
            if(visCnt[curr.r][curr.c] >= 4) continue;

            vis[curr.r][curr.c] = curr.change;
            visCnt[curr.r][curr.c]++;

            if(curr.r == goalR && curr.c == goalC) {
                answer = curr.change;
                break;
            }

            for(int d = 0; d < 4; d++){
                int nr = curr.r + dr[d];
                int nc = curr.c + dc[d];

                if(nr < 0 || nr >= r || nc < 0 || nc >= c) continue;

                // 뒤로 가는거
                if(d == (curr.dir + 2) % 4) continue;

                if(d == curr.dir) {
                    if(vis[nr][nc] < curr.change) continue;
                    pq.offer(new Point(nr, nc, curr.change, d));
                } else {
                    if(vis[nr][nc] < curr.change + 1) continue;
                    pq.offer(new Point(nr, nc, curr.change + 1, d));
                }
            }
        }

        System.out.println(answer);


    }
}
