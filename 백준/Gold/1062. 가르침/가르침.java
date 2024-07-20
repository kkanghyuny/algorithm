import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n, k, answer;
	static String[] arr;
	static boolean[] vis;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		answer = 0;

		if (k < 5) {
			System.out.println(0);
			return;
		}

		if (k == 26) {
			System.out.println(n);
			return;
		}

		arr = new String[n];
		vis = new boolean[26];
		vis[0] = true;
		vis[2] = true;
		vis[8] = true;
		vis[13] = true;
		vis[19] = true;

		for (int i = 0; i < n; i++) {
			arr[i] = br.readLine();
			arr[i] = arr[i].replace("anta", "");
			arr[i] = arr[i].replace("tica", "");
		}

		backtracking(0, 0);

		System.out.println(answer);
	}

	static void backtracking(int t, int length) {
		if (length == k - 5) {
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				boolean check = true;
				for (int j = 0; j < arr[i].length(); j++) {
					if (!vis[arr[i].charAt(j) - 'a']) {
						check = false;
						break;
					}
				}
				if (check) cnt++;
			}
			answer = Math.max(answer, cnt);
			return;
		}

		for (int i = t; i < 26; i++) {
			if (vis[i]) continue;
			vis[i] = true;
			backtracking(i, length + 1);
			vis[i] = false;
		}
	}
}
