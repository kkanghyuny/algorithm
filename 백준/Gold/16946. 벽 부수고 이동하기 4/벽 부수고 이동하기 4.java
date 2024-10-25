import java.io.*;
import java.util.*;
import java.awt.*;

public class Main {
	static int n, m, groupNum;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] arr, record, group, answer;
	static boolean[][] vis;
	static Queue<Point> queue;
	static Set<Integer> havingGroup;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		groupNum = 1;
		arr = new int[n][m];
		record = new int[n][m];
		group = new int[n][m];
		answer = new int[n][m];
		queue = new ArrayDeque<>();

		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			for(int j = 0; j < m; j++) {
				arr[i][j] = s.charAt(j) - '0';
				record[i][j] = -1;
			}
		}
		vis = new boolean[n][m];

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(!vis[i][j] && arr[i][j] == 0) {
					recordBfs(i, j);
				}
			}
		}

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(arr[i][j] == 1) {
					havingGroup = new HashSet<>();
					int cnt = 1;
					for(int d = 0; d < 4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						if(!checkBoundary(nr, nc)) continue;
						if(group[nr][nc] > 0 && !havingGroup.contains(group[nr][nc])) {
							havingGroup.add(group[nr][nc]);
							cnt += record[nr][nc];
						}
					}
					answer[i][j] = cnt % 10;
				}
			}
		}

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				sb.append(answer[i][j]);
			}
			if(i != n- 1) sb.append('\n');
		}
		System.out.println(sb);
	}

	static void recordBfs(int row, int col) {
		Queue<Point> recordQueue = new ArrayDeque<>();
		queue.offer(new Point(row, col));
		vis[row][col] = true;
		int cnt = 0;
		// 0으로 이어진 길 개수 세기
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			int r = p.x, c = p.y;
			recordQueue.offer(new Point(r, c));
			cnt++;
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if(!checkBoundary(nr, nc)) continue;
				if(vis[nr][nc] || arr[nr][nc] == 1) continue;
				vis[nr][nc] = true;
				queue.offer(new Point(nr, nc));
			}
		}
		// 지나온 길 0의 개수 세서 기록해두기
		while(!recordQueue.isEmpty()) {
			Point p = recordQueue.poll();
			int r = p.x, c = p.y;
			record[r][c] = cnt;
			group[r][c] = groupNum;
		}
		groupNum++;
		queue.clear();
	}

	static boolean checkBoundary(int r, int c) {
		return r >= 0 && r < n && c >= 0 && c < m;
	}
}
