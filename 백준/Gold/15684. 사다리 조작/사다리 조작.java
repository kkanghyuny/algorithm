import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int c, m, r, ans;
	static boolean[][] ladder;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		c = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		ladder = new boolean[r + 1][c * 2 + 1];

		for (int j = 1; j < c * 2; j += 2) {
			for (int i = 1; i <= r; i++) {
				ladder[i][j] = true;
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()) * 2;

			// 사다리 설치
			ladder[a][b] = true;
		}

		ans = -1;

		if (check()) {
			ans = 0;
		}

		if (ans == -1) {
			perm(1, 0);
		}

		if (ans == -1) {
			perm(2, 0);
		}

		if (ans == -1) {
			perm(3, 0);
		}

		System.out.println(ans);
	}

	static boolean check() {
		boolean isOk = true;

		int i = 1;
		int j = 1;
		for (int k = 1; k <= c * 2; k += 2) {
			i = 1;
			j = k;

			while (i <= r) {
				if (ladder[i][j - 1]) {
					j -= 2;
				} else if (ladder[i][j + 1]) {
					j += 2;
				}
				i++;
			}

			if (j != k) {
				isOk = false;
				break;
			}
		}

		return isOk;
	}

	static void perm(int depth, int nowDepth) {
		if (nowDepth == depth) {
			if (check()) {
				ans = depth;
			}
			return;
		}

		for (int i = 1; i <= r; i++) {
			for (int j = 2; j < c * 2; j += 2) {
				if (!ladder[i][j - 2] && !ladder[i][j + 2] && !ladder[i][j]) {
					ladder[i][j] = true;
					perm(depth, nowDepth + 1);
					ladder[i][j] = false;
				}
			}
		}
	}
}
