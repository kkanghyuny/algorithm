import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		
		for(int tc = 0; tc < t; tc++) {
			int n = Integer.parseInt(br.readLine());
			int score = Integer.MAX_VALUE;
			int answer = 0;
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				pq.offer(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
			}
			
			while(!pq.isEmpty()) {
				int[] now = pq.poll();
				int val = now[1];
				
				if(score > val) {
					score = val;
					answer++;
				}
			}
			
			System.out.println(answer);
		}
		
	}
}
