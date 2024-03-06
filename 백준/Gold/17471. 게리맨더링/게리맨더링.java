import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class NodeAB {
	int num;
	int people;
	char reg;
	List<NodeAB> links;

	public NodeAB(int num) {
		this.num = num;
		this.links = new ArrayList<>();
	}
}

public class Main {
	static boolean[] vis, visPermA;
	static List<Integer> listA, listB;
	static NodeAB[] nodes;
	static int minPeople, n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		nodes = new NodeAB[n + 1];
		vis = new boolean[n + 1];
		visPermA = new boolean[n + 1];
		listA = new ArrayList<>();
		listB = new ArrayList<>();
		minPeople = Integer.MAX_VALUE;

		for (int i = 1; i < n + 1; i++) {
			nodes[i] = new NodeAB(i);
		}

		st = new StringTokenizer(br.readLine());

		for (int i = 1; i < n + 1; i++) {
			nodes[i].people = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i < n + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());

			for (int j = 0; j < cnt; j++) {
				int link = Integer.parseInt(st.nextToken());
				nodes[i].links.add(nodes[link]);
				nodes[link].links.add(nodes[i]);
			}
		}

		for (int i = 1; i < n + 1; i++) {
			listA.add(i);
			visPermA[i] = true;
			nodes[i].reg = 'A';
			permA(0, 1);
			listA.remove(listA.size() - 1);
			visPermA[i] = false;
		}
		
		if(minPeople == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(minPeople);
		}

	}

	static void permA(int idx, int depth) {
		if (depth >= 1 && depth < n) {
			permB();
		}

		for (int i = idx + 1; i < n + 1; i++) {
			if (visPermA[i])
				continue;

			visPermA[i] = true;
			listA.add(i);
			nodes[i].reg = 'A';
			permA(i, depth + 1);
			visPermA[i] = false;
			listA.remove(listA.size() - 1);

		}
	}

	static void permB() {
		for (int i = 1; i < n + 1; i++) {
			if (visPermA[i])
				continue;
			listB.add(i);
			nodes[i].reg = 'B';
		}

		calculate();
		listB.clear();
	}

	static void calculate() {
		int sumA = 0;
		int sumB = 0;
		int sumListA = 0;
		int sumListB = 0;
		boolean[] visA = new boolean[n + 1];
		Queue<Integer> queueA = new LinkedList<>();

		queueA.add(listA.get(0));
		sumA += nodes[listA.get(0)].people;
		visA[listA.get(0)] = true;

		while (!queueA.isEmpty()) {
			int num = queueA.poll();
			NodeAB now = nodes[num];

			for (int i = 0; i < now.links.size(); i++) {
				if (visA[now.links.get(i).num] || now.links.get(i).reg != 'A')
					continue;

				visA[now.links.get(i).num] = true;
				sumA += now.links.get(i).people;
				queueA.add(now.links.get(i).num);
			}
		}

		for(int i = 0; i < listA.size(); i++) {
			sumListA += nodes[listA.get(i)].people;
		}
		
		if(sumA != sumListA) return;
		
		boolean[] visB = new boolean[n + 1];
		Queue<Integer> queueB = new LinkedList<>();

		queueB.add(listB.get(0));
		sumB += nodes[listB.get(0)].people;
		visB[listB.get(0)] = true;

		while (!queueB.isEmpty()) {
			int num = queueB.poll();
			NodeAB now = nodes[num];

			for (int i = 0; i < now.links.size(); i++) {
				if (visB[now.links.get(i).num] || now.links.get(i).reg != 'B')
					continue;

				visB[now.links.get(i).num] = true;
				sumB += now.links.get(i).people;
				queueB.add(now.links.get(i).num);
			}
		}

		for(int i = 0; i < listB.size(); i++) {
			sumListB += nodes[listB.get(i)].people;
		}
		
		if(sumB == sumListB) {
			minPeople = Math.min(minPeople, Math.abs(sumA - sumB));
		}
	}
}
