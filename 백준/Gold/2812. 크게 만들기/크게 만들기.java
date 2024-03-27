import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int cnt = 0;

		String s = br.readLine();

		Deque<Integer> deque = new ArrayDeque<>();

		for (int i = 0; i < s.length(); i++) {

			int now = s.charAt(i) - '0';
			if (cnt == k) {
				deque.offerLast(now);
				continue;
			}

			while (!deque.isEmpty() && deque.peekLast() < now && cnt < k) {
				deque.pollLast();
				cnt++;
			}
			deque.offerLast(now);
		}
		
		while(cnt < k) {
			deque.pollLast();
            cnt++;
		}

		while (!deque.isEmpty()) {
			sb.append(deque.pollFirst());
		}

		System.out.println(sb);
	}
}
