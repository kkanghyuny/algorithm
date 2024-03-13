import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		int[][][] arr = new int[n][m][h];
		boolean[][][] vis = new boolean[n][m][h];
		int[] dr = {1, -1, 0, 0, 0, 0};
		int[] dc = {0, 0, 1, -1, 0, 0};
		int[] dh = {0, 0, 0, 0, 1, -1};
		int lastDay = 0;
		
		Queue<int[]> queue = new ArrayDeque<>();
		
		for(int k = 0; k < h; k++) {
		for(int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < m; c++) {
					arr[r][c][k] = Integer.parseInt(st.nextToken());
					if(arr[r][c][k] == 1) {
						queue.add(new int[] {r, c, k ,0});
						vis[r][c][k] = true;
					
				}
				}
			}
		}
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			int k = cur[2];
			int day = cur[3];
			lastDay = day;
			
			for(int d = 0; d < 6; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				int nh = k + dh[d];
				
				if(nr < 0 || nr >= n || nc < 0 || nc >= m || nh < 0 || nh >= h) {
					continue;
				}
				
				if(arr[nr][nc][nh] == -1) {
					continue;
				}
				
				if(!vis[nr][nc][nh]) {
					vis[nr][nc][nh] = true;
					arr[nr][nc][nh] = 1;
					queue.add(new int[] {nr, nc, nh, day + 1});
				}
			}
		}
		
		a: for(int l = 0; l < h; l++) {
			for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(arr[i][j][l] == 0) {
					lastDay = -1;
					break a;
				}
			}
			}
		}
		System.out.println(lastDay);
	}
}
