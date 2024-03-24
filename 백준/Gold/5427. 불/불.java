import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 테케 개수 받고 그만큼 for문
		int tc = Integer.parseInt(br.readLine());
		for (int t = 0; t < tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());

			// 한글자인 char로 만들어질 arr, 불이 방문한곳 fireVis, 상근이 방문한곳 sgVis, 시간을 입력받을 answer
			char[][] arr = new char[n][m];
			boolean[][] fireVis = new boolean[n][m];
			boolean[][] sgVis = new boolean[n][m];
			int answer = 0;

			// queue의 int[] = {행, 열, 사람(1)인지 불(0)인지, 걸린 시간(s)}
			Queue<int[]> queue = new LinkedList<>();

			// 델타 행렬
			int[] dr = { 1, -1, 0, 0 };
			int[] dc = { 0, 0, 1, -1 };

			// 입력 받으면서 사람 먼저 큐에 넣어주고 본인 시작 위치도 vis = true
			for (int r = 0; r < n; r++) {
				String s = br.readLine();
				for (int c = 0; c < m; c++) {
					char val = s.charAt(c);
					arr[r][c] = val;
					if (val == '@') {
						queue.add(new int[] { r, c, 1, 0 });
						sgVis[r][c] = true;
					}
				}
			}

			// 사람 받은 이후에 한 번 더 돌면서 불 위치 받아주고
			for (int r = 0; r < n; r++) {
				for (int c = 0; c < m; c++) {
					if (arr[r][c] == '*') {
						queue.add(new int[] { r, c, 0, 0 });
						fireVis[r][c] = true;
						// 벽들도 그냥 다 vis = true
					} else if (arr[r][c] == '#') {
						fireVis[r][c] = true;
						sgVis[r][c] = true;
					}
				}
			}

			// 큐가 빌 때까지 while
			a: while (!queue.isEmpty()) {
				int[] cur = queue.poll();
				int r = cur[0];
				int c = cur[1];
				int isHuman = cur[2];
				int time = cur[3];

				// 사람인데 arr의 가장자리에 도달하면 answer에 걸린 시간 + 1 (탈출 뿅 하는 것도 시간이니) 하고 break
				if (isHuman == 1 && (r == 0 || r == n - 1 || c == 0 || c == m - 1)) {
					answer = time + 1;
					break;
				}

				// 사람이라면
				if (isHuman == 1) {
					for (int d = 0; d < 4; d++) {
						// 현재 위치는 지나간 곳이니 다시 빈 공간으로 바꿔주고
						arr[r][c] = '.';
						int nr = r + dr[d];
						int nc = c + dc[d];

						// 인덱스 넘는건 패스
						if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
							continue;
						}

						// 불이라면 continue
						if (arr[nr][nc] == '*') {
							continue;
							// 나머지 중 상근이가 간 곳 아니라면 (벽도 방문 처리 되어있음)
						} else if (!sgVis[nr][nc]) {
							// 방문했다고 표시해주고 @표시 해주고, 큐에 위치와 사람표시, 시간+1한거 넣어주고
							sgVis[nr][nc] = true;
							arr[nr][nc] = '@';
							queue.add(new int[] { nr, nc, 1, time + 1 });
						}
					}

					// 불이라면
				} else if (isHuman == 0) {
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];

						// 역시 인덱스 넘는건 패스
						if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
							continue;
						}

						// 잡았다 요놈 했는데 다른 @가 살아있으면 이어서 진행, 마지막 @이었다면 while 탈출
						if (arr[nr][nc] == '@') {
							boolean alive = false;
							arr[nr][nc] = '*';
							for (int i = 0; i < n; i++) {
								for (int j = 0; j < m; j++) {
									if (arr[i][j] == '@') {
										alive = true;
									}
								}
							}
							if (!alive) {
								break a;
							}
						}

						// 불이 간 곳이 아니라면(벽도 갔다는 처리 되어있음)
						if (!fireVis[nr][nc]) {
							// 갔다는 표시 해주고 그 위치 *로 바꿔주고 큐에 위치, 불이라는 표시, 시간+1(시간은 사실 필요 없어보임)
							fireVis[nr][nc] = true;
							arr[nr][nc] = '*';
							queue.add(new int[] { nr, nc, 0, time + 1 });
						}
					}
				}
			}
			// break를 하지 못하고 큐를 다 비우고 나왔다면 answer = 0
			if (answer == 0) {
				System.out.println("IMPOSSIBLE");
				// answer를 입력받고 break 해서 나왔으면 0이 아니므로
			} else {
				System.out.println(answer);
			}

		}

	}
}