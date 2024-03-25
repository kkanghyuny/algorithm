import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
		PriorityQueue<Integer> pq2 = new PriorityQueue<>();
		
		int n = Integer.parseInt(br.readLine());
		int max = 0;
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			pq.offer(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
		
		for(int i = 0; i < n; i++) {
			int[] poll = pq.poll();
			int x = poll[0];
			int y = poll[1];
			
			while(!pq2.isEmpty() && pq2.peek() <= x) {
				pq2.poll();
			}
			
			pq2.offer(y);
			
			max = Math.max(max, pq2.size());
		}
		
		System.out.println(max);
	}
}
