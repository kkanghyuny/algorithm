import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		boolean[] vis = new boolean[n + 1];
		int[] arr = new int[n + 1];
		List<Integer>[] list = new List[n + 1];
		
		for(int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int prev = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());

			arr[num]++;
			list[prev].add(num);
		}
		
		for(int i = 1; i <= n; i++) {
			if(arr[i] == 0) pq.offer(i);
		}
		
		while(!pq.isEmpty()) {
			int now = pq.poll();
			
			for(int i = 0; i < list[now].size(); i++) {
				int next = list[now].get(i);
				if(--arr[next] == 0) pq.offer(next);
			}
			
			sb.append(now).append(' ');
		}
		System.out.println(sb);
	}
}