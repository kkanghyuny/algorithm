import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static boolean left;
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static boolean[][] vis;
	static char[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// true면 왼쪽부터 false면 오른쪽부터
		left = true;

		vis = new boolean[n][m];
		arr = new char[n][m];

		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = s.charAt(j);
			}
		}

		int cnt = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < cnt; i++) {
			int row = n - Integer.parseInt(st.nextToken());

			// 턴 돌아가면서 높이 받아서 x 있으면 부수고
			if (left) {
				for (int col = 0; col < m; col++) {
					if (arr[row][col] == 'x') {
						arr[row][col] = '.';
						break;
					}
				}
				left = !left;
			} else {
				for (int col = m - 1; col >= 0; col--) {
					if (arr[row][col] == 'x') {
						arr[row][col] = '.';
						break;
					}
				}
				left = !left;
			}

			// 미네랄 떨어뜨리기
			drop();

			// vis 배열 초기화
			for (int j = 0; j < n; j++) {
				Arrays.fill(vis[j], false);
			}
		}

		// 배열 다 받아서 출력
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(arr[i][j]);
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}

	static void drop() {
		Queue<Point> queue = new ArrayDeque<>();

		int row = n - 1;

		// 1층(맨 아래)에 있는 x들을 다 큐에 받고 vis = true
		for (int col = 0; col < m; col++) {
			if (arr[row][col] == 'x') {
				queue.add(new Point(row, col));
				vis[row][col] = true;
			}
		}

		// 1층과 연결된 애들(안 떨어질 애들) 을 돌며 vis = true
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			int r = now.x;
			int c = now.y;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nr >= n || nc < 0 || nc >= m)
					continue;
				if (arr[nr][nc] == '.')
					continue;
				if (vis[nr][nc])
					continue;
				queue.add(new Point(nr, nc));
				vis[nr][nc] = true;
			}
		}

		// 떨어질 높이를 구해줄 minHeight
		int minHeight = Integer.MAX_VALUE;

		// 아래서부터(위에서부터 하면 이미 중복되는 자리가 있을 수 있음)
		for (int i = n - 2; i >= 0; i--) {
			for (int j = m - 1; j >= 0; j--) {
				// vis이 false라면(떨어질 미네랄이라면)
				if (arr[i][j] == 'x' && (!vis[i][j])) {
					// 큐에 다 넣고
					queue.add(new Point(i, j));

					// 거기서부터 가장 가까운 떨어질 위치를 구해주고 minHeight에 저장
					int r = i + 1;
					int height = 0;
					
					// 이 조건을 되게 고민했는데 arr[r][j] == '.'로 하면 안되고 !vis[r][j]여야 함
					while (r < n && !vis[r][j]) {
						r++;
						height++;
					}
					minHeight = Math.min(minHeight, height);
				}
			}
		}

		// 떨어질 미네랄들이 모두 큐에 들어있으니 뽑아서 minHeight 만큼 떨어뜨려줌
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			int r = now.x;
			int c = now.y;
			arr[r][c] = '.';
			r += minHeight;
			arr[r][c] = 'x';
		}
	}
}
