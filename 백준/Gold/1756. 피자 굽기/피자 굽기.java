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
		Queue<Integer> queue = new ArrayDeque<>();

		int d = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int answerIdx = 0;

		if (d < n) {
			System.out.println(0);
			return;
		}

		int[] arr = new int[d];

		st = new StringTokenizer(br.readLine());

		arr[0] = Integer.parseInt(st.nextToken());

		for (int i = 1; i < d; i++) {
			int val = Integer.parseInt(st.nextToken());
			if (val >= arr[i - 1]) {
				arr[i] = arr[i - 1];
			} else {
				arr[i] = val;
			}
		}

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			queue.offer(Integer.parseInt(st.nextToken()));
		}

		queue.offer(Integer.MAX_VALUE);

		int curr = queue.poll();

		for (int i = d - 1; i >= 0; i--) {
			if (curr <= arr[i]) {
				curr = queue.poll();
				answerIdx = i + 1;
			}
		}

		if (queue.isEmpty()) {
			System.out.println(answerIdx);
		} else {
			System.out.println(0);
		}

	}
}
