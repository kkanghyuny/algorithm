import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> pq1 = new PriorityQueue<>();
		PriorityQueue<Integer> pq2 = new PriorityQueue<>((a, b) -> b - a);

		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			int val = Integer.parseInt(br.readLine());

			if (val > 0) {
				pq1.offer(val);
			}
			else if (val < 0) {
				pq2.offer(val);
			} else if(val == 0 && !pq1.isEmpty() && !pq2.isEmpty()){
				if(pq1.peek().compareTo(Math.abs(pq2.peek())) == 0){
					sb.append(pq2.poll()).append('\n');
				} else if(pq1.peek().compareTo(Math.abs(pq2.peek())) > 0) {
					sb.append(pq2.poll()).append('\n');
				} else if(pq1.peek().compareTo(Math.abs(pq2.peek())) < 0){
					sb.append(pq1.poll()).append('\n');
				}
			} else if(!pq1.isEmpty()) {
				sb.append(pq1.poll()).append('\n');
			} else if(!pq2.isEmpty()){
				sb.append(pq2.poll()).append('\n');
			} else {
				sb.append(0).append('\n');
			}
		}
		System.out.println(sb);
	}
}
