import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<int[]> queue = new ArrayDeque<>();

		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int answer = 0;
		queue.offer(new int[] { a, 1 });

		while (!queue.isEmpty()) {
			int[] now = queue.poll();

			if (now[0] == b) {
				answer = now[1];
				break;
			} else if (now[0] > b) {
				continue;
			}

			int p = now[0] * 2;
			queue.offer(new int[] { p, now[1] + 1 });

			if (now[0] <= 100000000) {
				int q = Integer.parseInt(now[0] + "1");
				queue.offer(new int[] { q, now[1] + 1 });
			}
		}

		if (answer == 0) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}

	}
}
