import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		PriorityQueue<int[]> pq1 = new PriorityQueue<>(new Comparator<int []>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if (o1[0]==o2[0]) return o1[1]-o2[1];
				return o1[0]-o2[0];
			}
		 });
		PriorityQueue<Integer> pq2 = new PriorityQueue<>();
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			pq1.offer(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
		
		int count = 1;
		
		pq2.offer(pq1.poll()[1]);
		
		while(!pq1.isEmpty()) {
			int[] val = pq1.poll();
			int start = val[0];
			int end = val[1];
			if(start >= pq2.peek()) {
				pq2.poll();
				pq2.add(end);
				count++;
			}
			if(end >= pq2.peek()) {
				continue;
			} else if(end < pq2.peek()) {
				pq2.poll();
				pq2.offer(end);
			}
			
			
		}
		
		System.out.println(count);
	}
}
