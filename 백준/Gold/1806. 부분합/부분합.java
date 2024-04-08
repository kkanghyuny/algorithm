import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			dp[i] = dp[i - 1] + arr[i - 1];
		}

		int i = 0;
		int j = 1;
		int sum = 0;

		int ans = 987654321;

		while (i < n) {
			sum = dp[j] - dp[i];
			if (j == n) {
				if (sum >= s) {
					ans = Math.min(ans, j - i);
				}
				i++;
				continue;
			}

			if (sum >= s) {
				ans = Math.min(ans, j - i);
				i++;
			} else {
				j++;
			}

		}

		if (ans == 987654321) {
			System.out.println(0);
		} else {
			System.out.println(ans);
		}
	}
}
