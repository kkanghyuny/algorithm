import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Edge implements Comparable<Edge> {
		Point u;
		Point v;
		double w;

		public Edge(Point u, Point v) {
			this.u = u;
			this.v = v;
			this.w = Math.sqrt(Math.pow(u.x - v.x, 2) + Math.pow(u.y - v.y, 2));
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.w, o.w);
		}
	}

	static Map<Point, Integer> idx;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		List<Point> list = new ArrayList<>();
		idx = new HashMap<>();
		parent = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			Point newPoint = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			list.add(newPoint);
			idx.put(newPoint, i);
			parent[i] = i;
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				pq.offer(new Edge(list.get(i), list.get(j)));
			}
		}

		int pick = 0;

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			int find1 = find(a);
			int find2 = find(b);

			if (find1 != find2) {
				pick++;
				union(find1, find2);
			}
		}
		
		double ans = 0;
		
		while (!pq.isEmpty() && pick < n - 1) {
			Edge curr = pq.poll();
			int a = idx.get(curr.u);
			int b = idx.get(curr.v);
			
			int find1 = find(a);
			int find2 = find(b);
			if (find1 != find2) {
				pick++;
				union(find1, find2);
				ans += curr.w;
			}
		}

		System.out.printf("%.2f", (double) Math.round(ans * 100) / 100.0);
	}

	static int find(int n) {
		if(parent[n] == n) return n;
		return parent[n] = find(parent[n]);
	}

	static void union(int a, int b) {
		if(a > b) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		parent[b] = a;
	}
}
