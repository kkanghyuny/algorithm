import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	
	static class Computer implements Comparable<Computer>{
		int st, ed, w;

		public Computer(int st, int ed, int w) {
			this.st = st;
			this.ed = ed;
			this.w = w;
		}
		
		@Override
		public int compareTo(Computer o) {
			return this.w - o.w;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		PriorityQueue<Computer> pq = new PriorityQueue<>();
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		arr = new int[n + 1];
		
		for(int i = 0; i < n + 1; i++) {
			arr[i] = i;
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			if(a != b) {
				if(a > b) {
					int tmp = b;
					b = a;
					a = tmp;
				}
				pq.offer(new Computer(a, b, w));
			}
		}
		
		int pick = 0;
		int ans = 0;
		
		while(!pq.isEmpty() && pick < n - 1) {
			Computer curr = pq.poll();
			
			int parentX = find(curr.st);
			int parentY = find(curr.ed);
			
			if(parentX != parentY) {
				union(parentX, parentY);
				ans += curr.w;
				pick++;
			}
		}
		
		System.out.println(ans);
	}
	
	static int find(int x) {
		if(arr[x] == x) return x;
		return find(arr[x]);
	}
	
	static void union(int x, int y) {
		arr[y] = arr[x];
	}
}
