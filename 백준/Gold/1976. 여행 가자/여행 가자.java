import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int connected = Integer.parseInt(st.nextToken());
				if (connected == 1) {
					union(i, j);
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		int curr = find(Integer.parseInt(st.nextToken()) - 1);
		boolean possible = true;
		for (int i = 1; i < m; i++) {
			int next = find(Integer.parseInt(st.nextToken()) - 1);
			if (curr != next) {
				possible = false;
				break;
			}
		}

		System.out.println(possible ? "YES" : "NO");
	}

	static int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}

	static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);
		if (parentA != parentB) {
			parent[parentB] = parentA;
		}
	}
}
