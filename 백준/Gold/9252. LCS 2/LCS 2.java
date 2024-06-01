import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String s1 = br.readLine();
		String s2 = br.readLine();

		int[][] lcs = new int[s1.length() + 1][s2.length() + 1];

		int cnt = 0;

		for (int i = 1; i <= s1.length(); i++) {
			char c1 = s1.charAt(i - 1);
			for (int j = 1; j <= s2.length(); j++) {
				char c2 = s2.charAt(j - 1);
				if (c1 == c2) {
					lcs[i][j] = lcs[i - 1][j - 1] + 1;
				} else {
					lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
				}
				cnt = Math.max(lcs[i][j], cnt);
			}
		}

		System.out.println(cnt);

		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] { s1.length(), s2.length() });

		while (cnt > 0) {
			int[] now = queue.poll();
			int r = now[0];
			int c = now[1];

			if (lcs[r][c - 1] == cnt) {
				queue.offer(new int[] { r, c - 1 });
			} else if (lcs[r - 1][c] == cnt) {
				queue.offer(new int[] { r - 1, c });
			} else {
				sb.append(s1.charAt(r - 1));
				cnt--;
				queue.offer(new int[] { r - 1, c - 1 });
			}
		}
		
		System.out.println(sb.reverse());

	}
}
