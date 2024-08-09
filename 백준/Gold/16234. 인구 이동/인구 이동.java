import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main {
	static int n, l, r;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
    
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		int[][] arr = new int[n][n];
		boolean[][] vis = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int day = 0;
		boolean isConnect = true;

		while(isConnect){
			isConnect = false;
			boolean[][] localVis = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!localVis[i][j]) {
						List<Point> union = new ArrayList<>();
						Queue<Point> queue = new ArrayDeque<>();
						queue.add(new Point(i, j));
						localVis[i][j] = true;
						union.add(new Point(i, j));
						int sum = arr[i][j];
						int cnt = 1;

						while (!queue.isEmpty()) {
							Point p = queue.poll();
							int x = p.x;
							int y = p.y;

							for (int d = 0; d < 4; d++) {
								int nx = x + dr[d];
								int ny = y + dc[d];

								if (nx >= 0 && nx < n && ny >= 0 && ny < n && !localVis[nx][ny] && check(arr[x][y], arr[nx][ny])) {
									localVis[nx][ny] = true;
									queue.add(new Point(nx, ny));
									union.add(new Point(nx, ny));
									sum += arr[nx][ny];
									cnt++;
								}
							}
						}

						if (union.size() > 1) {
							isConnect = true;
							int newValue = sum / cnt;
							for (Point p : union) {
								arr[p.x][p.y] = newValue;
							}
						}
					}
				}
			}
			if (isConnect) day++;
		}

		System.out.println(day);
	}

	static boolean check(int a, int b) {
		return Math.abs(a - b) >= l && Math.abs(a - b) <= r;
	}
}
