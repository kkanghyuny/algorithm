import java.util.*;
import java.io.*;

public class Main {
	static class Node {
		int id;
		List<Integer> toList;
		List<Integer> cost;

		public Node(int id) {
			this.id = id;
			this.toList = new ArrayList<>();
			this.cost = new ArrayList<>();
		}
	}

	static class Person implements Comparable<Person> {
		int nowAt;
		int cost;
		int afterVisit;

		public Person(int nowAt, int cost, int afterVisit) {
			this.nowAt = nowAt;
			this.cost = cost;
			this.afterVisit = afterVisit;
		}

		@Override
		public int compareTo(Person o) {
			if(this.afterVisit == o.afterVisit) {
				return this.cost - o.cost;
			}
			return o.afterVisit - this.afterVisit;
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int answer = 0;
		Node[] nodes = new Node[n + 1];

		for(int i = 1; i <= n; i++) {
			nodes[i] = new Node(i);
		}

		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			nodes[start].toList.add(end);
			nodes[start].cost.add(cost);
		}

		PriorityQueue<Person> pq = new PriorityQueue<>();
		boolean[][] vis = new boolean[n + 1][2];

		for(int i = 1; i <= n; i++) {
			if(i == x) continue;
			vis = new boolean[n + 1][2];
			pq.offer(new Person(i, 0, 0));

			while(!pq.isEmpty()) {
				Person p = pq.poll();
				int now = p.nowAt;
				int cost = p.cost;
				int afterVisit = p.afterVisit;

				if(afterVisit == 1 && now == i) {
					answer = Math.max(answer, cost);
					break;
				}

				if(vis[now][afterVisit]) continue;
				vis[now][afterVisit] = true;
				if(now == x) {
					afterVisit = 1;
					vis[now][afterVisit] = true;
				}

				for(int j = 0; j < nodes[now].toList.size(); j++) {
					int next = nodes[now].toList.get(j);
					if(vis[next][afterVisit]) continue;

					pq.offer(new Person(next, cost + nodes[now].cost.get(j), afterVisit));
				}
			}

			pq.clear();
		}

		System.out.println(answer);
	}
}
