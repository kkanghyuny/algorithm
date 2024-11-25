import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int r, c, h;
		public Node(int r, int c, int h) {
			this.r = r;
			this.c = c;
			this.h = h;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int[] dr = {1, -1, 0, 0, 0, 0};
		int[] dc = {0, 0, 1, -1, 0, 0};
		int[] dh = {0, 0, 0, 0, 1, -1};
		a: while(true) {
			st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(h == 0 && r == 0 && c == 0) break;
			char[][][] arr = new char[r][c][h];
			boolean[][][] vis = new boolean[r][c][h];
			Queue<Node> queue = new ArrayDeque<>();
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < r; j++) {
					String s = br.readLine();
					for(int k = 0; k < c; k++) {
						arr[j][k][i] = s.charAt(k);
						if(arr[j][k][i] == 'S') {
							queue.offer(new Node(j, k, i));
							vis[j][k][i] = true;
						}
					}
				}
				br.readLine();
			}
			int time = 0;
			while(!queue.isEmpty()) {
				int size = queue.size();
				while(size-- > 0) {
					Node curr = queue.poll();
					if (arr[curr.r][curr.c][curr.h] == 'E') {
						sb.append("Escaped in ").append(time).append(" minute(s).").append('\n');
						continue a;
					}
					for(int d = 0; d < 6; d++) {
						int nr = curr.r + dr[d];
						int nc = curr.c + dc[d];
						int nh = curr.h + dh[d];
						if(nr < 0 || nr >= r || nc < 0 || nc >= c || nh < 0 || nh >= h) continue;
						if(vis[nr][nc][nh] || arr[nr][nc][nh] == '#') continue;
						vis[nr][nc][nh] = true;
						queue.offer(new Node(nr, nc, nh));
					}
				}
				time++;
			}
			sb.append("Trapped!\n");
		}
		System.out.println(sb);
	}
}
