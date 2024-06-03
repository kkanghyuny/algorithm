import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static class Node {
		int cost;
		int value;

		public Node(int cost, int value) {
			this.cost = cost;
			this.value = value;
		}
	}

	static int n, m, k;
	static int[] parent, candy, count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		parent = new int[n + 1];
		candy = new int[n + 1];
		count = new int[n + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
			candy[i] = Integer.parseInt(st.nextToken());
			count[i] = 1;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = find(Integer.parseInt(st.nextToken()));
			int b = find(Integer.parseInt(st.nextToken()));

			if (a != b) {
				union(a, b);
			}
		}

		for (int i = 1; i <= n; i++) {
			if (parent[i] != i) {
				int p = find(i);
				candy[p] += candy[i];
				count[p] += count[i];
			}
		}

		List<Node> list = new ArrayList<>();

		for (int i = 1; i <= n; i++) {
			if (parent[i] == i) {
				list.add(new Node(count[i], candy[i]));
			}
		}

		int[][] dp = new int[list.size() + 1][k];

		for (int i = 1; i <= list.size(); i++) {
			for (int j = 0; j < k; j++) {
				if (list.get(i - 1).cost <= j) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - list.get(i - 1).cost] + list.get(i - 1).value);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}

		System.out.println(dp[list.size()][k - 1]);
	}

	static void union(int a, int b) {
		if (a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}

	static int find(int n) {
		if (parent[n] == n)	return n;
		return parent[n] = find(parent[n]);
	}
}
