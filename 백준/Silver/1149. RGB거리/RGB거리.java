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
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}
		
		dp[0] = Arrays.copyOf(arr[0], 3);

		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					if (j == k)
						continue;
					dp[i + 1][k] = Math.min(arr[i + 1][k] + dp[i][j], dp[i + 1][k]);
				}
			}
		}

		for(int i = 0; i < 3; i++) {
			answer = Math.min(answer, dp[n - 1][i]);
		}
		
		System.out.println(answer);
		
	}
}
