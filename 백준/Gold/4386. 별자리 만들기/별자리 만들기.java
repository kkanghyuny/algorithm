import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Edge implements Comparable<Edge> {
		int st, ed;
		double w;

		public Edge() {
		}

		public Edge(int st, int ed, double w) {
			this.st = st;
			this.ed = ed;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.w, o.w);
		}
	}

	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		int n = Integer.parseInt(br.readLine());

		double[] allX = new double[n];
		double[] allY = new double[n];
		arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = i;
		}

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			allX[i] = Double.parseDouble(st.nextToken());
			allY[i] = Double.parseDouble(st.nextToken());
		}

		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				double dist = distance(allX[i], allY[i], allX[j], allY[j]);
				pq.offer(new Edge(i, j, dist));
			}
		}

		int pick = 0;
		double ans = 0;

		while (!pq.isEmpty() && pick < n - 1) {
			Edge curr = pq.poll();

			int parentA = find(curr.st);
			int parentB = find(curr.ed);

			if (parentA != parentB) {
				ans += curr.w;
				pick++;
				union(parentA, parentB);
			}
		}

		System.out.println(Math.round(ans * 100) / 100.0);

	}

	static double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	static int find(int x) {
		if (x == arr[x])
			return x;
		return find(arr[x]);
	}

	static void union(int x, int y) {
		arr[y] = arr[x];
	}
}
