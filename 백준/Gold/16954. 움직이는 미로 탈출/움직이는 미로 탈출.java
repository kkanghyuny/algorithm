import java.awt.*;
import java.util.*;
import java.io.*;

public class Main {

	static class Human implements Comparable<Human> {
		int r;
		int c;

		public Human(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public int compareTo(Human o) {
			if(this.r == o.r) return o.c - this.c;
			return this.r - o.r;
		}
	}

	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1, 0};
	static int[] dc = {1, 0, -1, 1, -1, 1, 0, -1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Human> queue = new PriorityQueue<>();
		Queue<Point> wall = new ArrayDeque<>();
		int answer = 0;
		char[][] arr = new char[8][8];
		boolean[][] vis = new boolean[8][8];

		for(int i = 0; i < 8; i++) {
			String s = br.readLine();
			for(int j = 0; j < 8; j++) {
				arr[i][j] = s.charAt(j);
			}
		}

		for(int i = 7; i >= 0; i--) {
			for(int j = 0; j < 8; j++) {
				if(arr[i][j] == '#') {
					wall.offer(new Point(i, j));
					vis[i][j] = true;
				}
			}
		}

		queue.offer(new Human(7, 0));
		a: while(!queue.isEmpty()) {
			int size = queue.size();
			while(size-- > 0) {
				Human curr = queue.poll();
				int r = curr.r;
				int c = curr.c;
				if (r == 0 && c == 7) {
					answer = 1;
					break a;
				}

				for (int d = 0; d < 9; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (!checkBoundary(nr, nc))
						continue;
					if (vis[nr][nc])
						continue;
					if (nr >= 1 && vis[nr - 1][nc])
						continue;
					queue.offer(new Human(nr, nc));
				}
			}

			size = wall.size();
			while(size-- > 0) {
				Point curr = wall.poll();
				int r = curr.x;
				int c = curr.y;
				vis[r][c] = false;
				r++;
				if(r < 8) {
					vis[r][c] = true;
					wall.offer(new Point(r, c));
				}
			}
		}
		System.out.println(answer);
	}

	static boolean checkBoundary(int r, int c) {
		return r >= 0 && r < 8 && c >= 0 && c < 8;
	}
}
