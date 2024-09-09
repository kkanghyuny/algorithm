import java.io.*;
import java.util.*;

public class Main {
	static int[] friends;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= t; tc++) {
			sb.append("Scenario ").append(tc).append(":\n");
			int n = Integer.parseInt(br.readLine());
			int k = Integer.parseInt(br.readLine());

			friends = new int[n];
			for(int i = 0; i < n; i++) {
				friends[i] = i;
			}

			for(int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int a = find(Integer.parseInt(st.nextToken()));
				int b = find(Integer.parseInt(st.nextToken()));
				union(a, b);
			}

			int m = Integer.parseInt(br.readLine());
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = find(Integer.parseInt(st.nextToken()));
				int b = find(Integer.parseInt(st.nextToken()));
				if(a == b) sb.append("1\n");
				else sb.append("0\n");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static int find(int n) {
		if(friends[n] == n) return n;
		return friends[n] = find(friends[n]);
	}

	static void union(int a, int b) {
		if(a > b) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		friends[b] = a;
	}
}
