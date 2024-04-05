import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n, maxDist, maxIdx;
	static Node[] nodes;
	static boolean[] vis;

	static class Node {
		int id;
		List<Node> link;
		List<Integer> dist;

		public Node() {
			link = new ArrayList<>();
			dist = new ArrayList<>();
		}

		public Node(int id) {
			this.id = id;
			link = new ArrayList<>();
			dist = new ArrayList<>();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		maxDist = 0;

		nodes = new Node[n + 1];
		vis = new boolean[n + 1];

		for (int i = 1; i <= n; i++) {
			nodes[i] = new Node(i);
		}

		maxIdx = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());

			while (true) {
				int a = Integer.parseInt(st.nextToken());
				if (a == -1)
					break;
				int b = Integer.parseInt(st.nextToken());

				nodes[idx].link.add(nodes[a]);
				nodes[idx].dist.add(b);
			}
		}

		for (int i = 1; i <= n; i++) {
			vis[i] = true;
			for (int j = 0; j < nodes[i].link.size(); j++) {
				if (vis[nodes[i].link.get(j).id])
					continue;

				vis[nodes[i].link.get(j).id] = true;
				int linkDist = nodes[i].dist.get(j);
				dfs(nodes[i].link.get(j), linkDist);
			}
		}

		vis = new boolean[n + 1];

		vis[maxIdx] = true;
		for (int i = 0; i < nodes[maxIdx].link.size(); i++) {
			vis[nodes[maxIdx].link.get(i).id] = true;
			int linkDist = nodes[maxIdx].dist.get(i);
			dfs(nodes[maxIdx].link.get(i), linkDist);
		}

		System.out.println(maxDist);
	}

	static void dfs(Node node, int dist) {
		if (maxDist < dist) {
			maxDist = dist;
			maxIdx = node.id;
		}
		maxDist = Math.max(dist, maxDist);

		for (int i = 0; i < node.link.size(); i++) {
			if (vis[node.link.get(i).id])
				continue;

			vis[node.link.get(i).id] = true;
			int linkDist = node.dist.get(i);
			dfs(node.link.get(i), dist + linkDist);
		}
	}
}
