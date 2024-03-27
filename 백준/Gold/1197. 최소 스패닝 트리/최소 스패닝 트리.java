import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		arr = new int[v + 1];
		
		for(int i = 0; i < v + 1; i++) {
			arr[i] = i;
		}
		
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			pq.offer(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
		
		int pick = 0;
		int answer = 0;
		
		while(!pq.isEmpty() && pick < (v - 1)) {
			int[] poll = pq.poll();
			int parentX = find(poll[0]);
			int parentY = find(poll[1]);
			
			if(parentX != parentY) {
				union(parentX, parentY);
				pick++;
				answer += poll[2];
			}
		}
		
		System.out.println(answer);
	}
	
	static int find(int x) {
		if(x == arr[x]) return x;
		return find(arr[x]);
	}
	
	static void union(int x, int y) {
		arr[y] = arr[x];
	}
}
