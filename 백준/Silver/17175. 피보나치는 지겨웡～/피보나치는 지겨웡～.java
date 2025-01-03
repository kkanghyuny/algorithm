import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[] dp = new long[51];
		dp[0] = 1;
		dp[1] = 1;
		for(int i = 2; i < 51; i++) {
			dp[i] = (1 + dp[i - 2] + dp[i - 1]);
		}
		System.out.println(dp[n] % 1_000_000_007);
	}
}
