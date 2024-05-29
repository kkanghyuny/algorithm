import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());

		int[][] arr = new int[n][3];
		int[][] dp = new int[n][3];
		int answer = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			Arrays.fill(dp[i], 987654321);
		}
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}

		dp[0] = Arrays.copyOf(arr[0], 3);

		for (int k = 0; k < 3; k++) {
			for (int i = 0; i < 3; i++) {
				if (i == k)
					dp[0][i] = arr[0][i];
				else
					dp[0][i] = 987654321;
			}

			for (int i = 1; i < n; i++) {
				dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + arr[i][0];
				dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + arr[i][1];
				dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + arr[i][2];
			}
			
			for (int i = 0; i < 3; i++) {
				if (i != k) {
					answer = Math.min(answer, dp[n - 1][i]);
				}
			}
		}

		System.out.println(answer);

	}
}
