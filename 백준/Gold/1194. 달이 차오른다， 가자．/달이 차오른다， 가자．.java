import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, answer;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static char[][] arr;
	static boolean[][][] vis;
	static Queue<int[]> queue = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		answer = Integer.MAX_VALUE;

		int roww = 0;
		int coll = 0;

		arr = new char[n][m];
		vis = new boolean[n][m][64];

		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = s.charAt(j);
				if (arr[i][j] == '0') {
					roww = i;
					coll = j;
				}
			}
		}

		vis[roww][coll][0] = true;
		queue.add(new int[] {roww, coll, 0, 0});
		bfs();

		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}

	}

	static void bfs() {
		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int r = now[0];
			int c = now[1];
			int dist = now[2];
			int haveKey = now[3];
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nr >= n || nc < 0 || nc >= m)
					continue;
				if (vis[nr][nc][haveKey])
					continue;

				if (arr[nr][nc] == '.' || arr[nr][nc] == '0') {
					queue.add(new int[] { nr, nc, dist + 1, haveKey });
					vis[nr][nc][haveKey] = true;
				} else if (arr[nr][nc] >= 97 && arr[nr][nc] <= 102) {
					int val = 1 << (arr[nr][nc] - 97);
					int nowKey = haveKey | val;
					vis[nr][nc][nowKey] = true;
					queue.add(new int[] {nr, nc, dist + 1, nowKey});
				} else if (arr[nr][nc] >= 65 && arr[nr][nc] <= 70) {
					int val = 1 << (arr[nr][nc] - 65);
					if ((val & haveKey) > 0) {
						queue.add(new int[] { nr, nc, dist + 1, haveKey });
						vis[nr][nc][haveKey] = true;
					}
				} else if (arr[nr][nc] == '1') {
					answer = Math.min(answer, dist + 1);
				}
			}
		}
	}
}