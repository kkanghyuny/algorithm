import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int max = Integer.MIN_VALUE;

		int[][] arr = new int[n + 1][2];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[n + 1];

		for (int i = 0; i < n + 1; i++) {
			max = Math.max(max, dp[i]);
			
			int next = i + arr[i][0];
			if (next <= n) {
				dp[next] = Math.max(dp[next], max + arr[i][1]);
			}

		}

		System.out.println(max);
	}
}
