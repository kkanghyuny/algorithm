import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int[] group;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		group = new int[n];
		
		for(int i = 0; i < n; i++) {
			group[i] = i;
		}
		int[][] arr = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		
		for(int i = 0; i < n; i++) {
			for(int j = i + 1; j < n; j++) {
				pq.offer(new int[] {i, j, arr[i][j]});
			}
		}
		
		int pick = 0;
		long ans = 0;
		
		while(!pq.isEmpty() && pick < n - 1) {
			int[] curr = pq.poll();
			
			int find1 = find(curr[0]);
			int find2 = find(curr[1]);
			
			if(find1 != find2) {
				union(find1, find2);
				pick++;
				ans += curr[2];
			}
		}
		
		System.out.println(ans);
		
	}
	
	static int find(int n) {
		if(group[n] == n) return n;
		return find(group[n]);
	}
	
	static void union(int a, int b) {
		if(a > b) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		group[b] = a; 
	}
}
