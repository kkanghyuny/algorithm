import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {

	static int n, m, k, dir, answer;
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static int[][] score, arr, dice;
	static boolean[][] vis;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		dir = 0;
		answer = 0;

		arr = new int[n][m];
		score = new int[n][m];
		vis = new boolean[n][m];
		dice = new int[4][3];
		dice[0][1] = 2;
		dice[1][0] = 4;
		dice[1][1] = 1;
		dice[1][2] = 3;
		dice[2][1] = 5;
		dice[3][1] = 6;

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		setScoreArr();
		solve();
		System.out.println(answer);
	}

	static void solve() {
		int r = 0;
		int c = 0;
		while(k-- > 0) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			// 반대 방향으로 뒤집기
			if(!checkBoundary(nr, nc)) {
				dir = (dir + 2) % 4;
				nr = r + dr[dir];
				nc = c + dc[dir];
			}

			switch(dir) {
				case 0: {
					int tmp = dice[1][2];
					dice[1][2] = dice[1][1];
					dice[1][1] = dice[1][0];
					dice[1][0] = dice[3][1];
					dice[3][1] = tmp;
					break;
				}
				case 1: {
					int tmp = dice[2][1];
					dice[2][1] = dice[1][1];
					dice[1][1] = dice[0][1];
					dice[0][1] = dice[3][1];
					dice[3][1] = tmp;
					break;
				}
				case 2: {
					int tmp = dice[1][0];
					dice[1][0] = dice[1][1];
					dice[1][1] = dice[1][2];
					dice[1][2] = dice[3][1];
					dice[3][1] = tmp;
					break;
				}
				case 3: {
					int tmp = dice[0][1];
					dice[0][1] = dice[1][1];
					dice[1][1] = dice[2][1];
					dice[2][1] = dice[3][1];
					dice[3][1] = tmp;
					break;
				}
			}

			r = nr;
			c = nc;
			answer += score[r][c];

			if(dice[3][1] > arr[r][c]) {
				dir = (dir + 1) % 4;
			} else if(dice[3][1] < arr[r][c]) {
				dir = (dir + 3) % 4;
			}
		}
	}

	static void setScoreArr() {
		Queue<Point> queue1 = new ArrayDeque<>();
		Queue<Point> queue2 = new ArrayDeque<>();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(vis[i][j]) continue;
				vis[i][j] = true;
				int cnt = 0;
				queue1.offer(new Point(i, j));
				while(!queue1.isEmpty()) {
					cnt++;
					Point p = queue1.poll();
					queue2.offer(p);
					int r = p.x;
					int c = p.y;
					for(int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if(!checkBoundary(nr, nc)) continue;
						if(vis[nr][nc]) continue;
						if(arr[r][c] == arr[nr][nc]) {
							vis[nr][nc] = true;
							queue1.offer(new Point(nr, nc));
						}
					}
				}
				while(!queue2.isEmpty()) {
					Point p = queue2.poll();
					int r = p.x;
					int c = p.y;
					score[r][c] = cnt * arr[r][c];
				}
			}
		}
	}

	static boolean checkBoundary(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < m;
	}
}
