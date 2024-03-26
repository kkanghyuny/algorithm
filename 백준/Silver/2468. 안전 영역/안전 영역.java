import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
//		int t = Integer.parseInt(br.readLine());
		int[] dr = new int[] {1, -1, 0, 0};
		int[] dc = new int[] {0, 0, 1, -1};
		
//		for(int tc = 1; tc <= t; tc++) {
			int n = Integer.parseInt(br.readLine());
			int[][] arr = new int[n][n];
			boolean[][] vis;
			int answer = 0;
			Queue<Point> queue;
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int day = 0; day <= 100; day++) {
				vis = new boolean[n][n];
				queue = new ArrayDeque<>();
				int group = 0;
				
				for(int i = 0; i < n; i++) {
					for(int j = 0; j < n; j++) {
						if(!vis[i][j] && arr[i][j] > day) {
							vis[i][j] = true;
							queue.add(new Point(i, j));
							group++;
							
							while(!queue.isEmpty()) {
								Point poll = queue.poll();
								int r = poll.x;
								int c = poll.y;
								
								for(int d = 0; d < 4; d++) {
									int nr = r + dr[d];
									int nc = c + dc[d];
									
									if(nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
									if(!vis[nr][nc] && arr[nr][nc] > day) {
										queue.add(new Point(nr, nc));
										vis[nr][nc] = true;
									}
								}
							}
						}
					}
				}
				
				answer = Math.max(group, answer);
			}
			
//			sb.append("#").append(tc).append(" ").append(answer).append('\n');
//		}
		
		System.out.println(answer);
	}
}
