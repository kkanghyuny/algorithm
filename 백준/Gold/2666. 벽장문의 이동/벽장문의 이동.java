import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<int[]> queue = new ArrayDeque<>();

		int n = Integer.parseInt(br.readLine());
		int answer = Integer.MAX_VALUE;

		StringTokenizer st = new StringTokenizer(br.readLine());

		int first = Integer.parseInt(st.nextToken());
		int second = Integer.parseInt(st.nextToken());

		int cnt = Integer.parseInt(br.readLine());

		int[] cmd = new int[cnt];

		for (int p = 0; p < cnt; p++) {
			cmd[p] = Integer.parseInt(br.readLine());
		}

		queue.offer(new int[] { first, second, cmd[0], 0, 0 });

		while (!queue.isEmpty()) {
			int[] now = queue.poll();
			int i = now[0];
			int j = now[1];
			int find = now[2];
			int ans = now[3];
			int idx = now[4];

			if (idx == cnt - 1) {
				int firstAns = ans + Math.abs(i - find);
				answer = Math.min(firstAns, answer);
				int secondAns = ans + Math.abs(j - find);
				answer = Math.min(secondAns, answer);
				continue;
			}

			if (find < i) {
				ans += i - find;
				queue.offer(new int[] { find, j, cmd[idx + 1], ans, idx + 1 });
			} else if (j < find) {
				ans += find - j;
				queue.offer(new int[] { i, find, cmd[idx + 1], ans, idx + 1 });
			} else {
				int firstAns = ans + find - i;
				queue.offer(new int[] { find, j, cmd[idx + 1], firstAns, idx + 1 });

				int secondAns = ans + j - find;
				queue.offer(new int[] { i, find, cmd[idx + 1], secondAns, idx + 1 });
			}
		}

		System.out.println(answer);
	}
}
