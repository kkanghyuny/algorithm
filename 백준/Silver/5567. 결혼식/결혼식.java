import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int id;
		List<Node> link;

		public Node() {
			this.link = new ArrayList<>();
		}

		public Node(int id) {
			this.id = id;
			this.link = new ArrayList<>();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		Node[] nodes = new Node[n + 1];
		boolean[] vis = new boolean[n + 1];
		for (int i = 1; i < n + 1; i++) {
			nodes[i] = new Node(i);
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			nodes[a].link.add(nodes[b]);
			nodes[b].link.add(nodes[a]);
		}

		int count = 0;
		int cnt = 2;
		Queue<Node> queue = new ArrayDeque<>();

		queue.offer(nodes[1]);
		vis[1] = true;
		while (cnt-- > 0) {
			int size = queue.size();
			while (size-- > 0) {
				Node curr = queue.poll();

				for (int i = 0; i < curr.link.size(); i++) {
					if (!vis[curr.link.get(i).id]) {
						vis[curr.link.get(i).id] = true;
						queue.offer(curr.link.get(i));
						count++;
					}
				}
			}
		}

		System.out.println(count);
	}
}
