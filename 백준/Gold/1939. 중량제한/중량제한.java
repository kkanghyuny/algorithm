import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int id;
		List<Node> link;

		public Node() {
			link = new ArrayList<>();
		}

		public Node(int id) {
			this.id = id;
			link = new ArrayList<>();
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		HashMap<Point, Integer> map = new HashMap<>();
		Point point = new Point();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Node[] nodes = new Node[n + 1];
		int answer = 0;
		boolean[] vis = new boolean[n + 1];

		for (int i = 1; i <= n; i++) {
			nodes[i] = new Node(i);
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			Point point1 = new Point(a, b);
			Point point2 = new Point(b, a);
			int val = Integer.parseInt(st.nextToken());

			if (map.containsKey(point1)) {
				if (map.get(point1) < val) {
					map.put(point1, val);
					map.put(point2, val);
				}
			} else {
				nodes[a].link.add(nodes[b]);
				nodes[b].link.add(nodes[a]);
				map.put(point1, val);
				map.put(point2, val);
			}
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[1] - a[1]);

		queue.offer(new int[] { start, Integer.MAX_VALUE });

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			if(vis[curr[0]]) continue;
			vis[curr[0]] = true;
			if (curr[0] == end) {
				answer = curr[1];
				break;
			}

			for (int i = 0; i < nodes[curr[0]].link.size(); i++) {
				Node child = nodes[curr[0]].link.get(i);
				point.x = nodes[curr[0]].id;
				point.y = child.id;
				int dist = Math.min(map.get(point), curr[1]);
				if (!vis[child.id]) {
					queue.offer(new int[] { child.id, dist });
				}
			}
		}
		
		System.out.println(answer);

	}
}
