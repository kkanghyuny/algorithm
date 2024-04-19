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

		public Node(int id) {
			this.id = id;
			this.link = new ArrayList<>();
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		int score = Integer.MAX_VALUE;
		List<Integer> list = new ArrayList<>();
		Queue<Node> queue = new ArrayDeque<>();

		Node[] nodes = new Node[n + 1];
		boolean[] vis = new boolean[n + 1];

		for (int i = 0; i < n + 1; i++) {
			nodes[i] = new Node(i);
		}

		while (true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (a == -1 && b == -1)
				break;

			nodes[a].link.add(nodes[b]);
			nodes[b].link.add(nodes[a]);
		}

		for (int i = 1; i < n + 1; i++) {
			vis = new boolean[n + 1];
			vis[i] = true;
			queue.offer(nodes[i]);
			int count = -1;

			while (true) {
				int size = queue.size();
				if (size == 0)
					break;

				while (size-- > 0) {
					Node curr = queue.poll();

					for (int t = 0; t < curr.link.size(); t++) {
						if (!vis[curr.link.get(t).id]) {
							vis[curr.link.get(t).id] = true;
							queue.offer(curr.link.get(t));
						}
					}
				}
				count++;
			}
			if (score > count) {
				score = count;
				list.clear();
				list.add(i);
			} else if (score == count) {
				list.add(i);
			}
		}

		sb.append(score).append(" ").append(list.size()).append('\n');
		
		for(int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)).append(" ");
		}
		
		System.out.println(sb);

	}
}
