import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
	static int n, m, answer;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		answer = 0;
		arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		backtracking(0, 0, 0);
		System.out.println(answer);
	}

	static void backtracking(int depth, int r, int c) {
		if(depth == 3) {
			check();
			return;
		}
		if(c >= m) {
			if(r == n - 1) return;
			r++;
			c = 0;
		}
		for(int j = c; j < m; j++) {
			if(arr[r][j] == 0) {
				arr[r][j] = 1;
				backtracking(depth + 1, r, j + 1);
				arr[r][j] = 0;
			}
		}
		for(int i = r + 1; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(arr[i][j] == 0) {
					arr[i][j] = 1;
					backtracking(depth + 1, i, j + 1);
					arr[i][j] = 0;
				}
			}
		}
	}

	static void check() {
		boolean[][] vis = new boolean[n][m];
		Queue<Point> queue = new ArrayDeque<>();
		int cnt = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(arr[i][j] == 2 && !vis[i][j]) {
					queue.offer(new Point(i, j));
					vis[i][j] = true;
					while(!queue.isEmpty()) {
						Point p = queue.poll();
						int r = p.x, c = p.y;
						for(int d = 0; d < 4; d++) {
							int nr = r + dr[d];
							int nc = c + dc[d];
							if(!checkBoundary(nr, nc)) continue;
							if(vis[nr][nc] || arr[nr][nc] == 1) continue;
							vis[nr][nc] = true;
							queue.offer(new Point(nr, nc));
						}
					}
				}
			}
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(!vis[i][j] && arr[i][j] == 0) cnt++;
			}
		}
		answer = Math.max(cnt, answer);
	}

	static boolean checkBoundary(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < m;
	}
}
