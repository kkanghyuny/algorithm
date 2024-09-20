import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		int answer = 0;

		int[] shotFrom = new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			shotFrom[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(shotFrom);

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			pq.offer(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}

		int idx = 0;
		while(idx < m && !pq.isEmpty()) {
			int[] curr = pq.poll();
			int r = curr[0];
			int c = curr[1];
			int now = shotFrom[idx];

			if(Math.abs(now - r) + c <= l) {
				answer++;
				continue;
			}

			if(now < r) {
				now = shotFrom[++idx];
				if(Math.abs(now - r) + c <= l) {
					answer++;
				}
			}
		}
		System.out.println(answer);
	}
}
