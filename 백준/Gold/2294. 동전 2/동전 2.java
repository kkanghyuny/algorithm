import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] dp = new int[k + 1];
		Arrays.fill(dp, 987654321);
		dp[0] = 0;
		
		for(int i = 0; i < n; i++) {
			int t = Integer.parseInt(br.readLine());
			for(int j = t; j <= k; j++) {
				dp[j] = Math.min(dp[j], dp[j - t] + 1);
			}
		}
		System.out.println(dp[k] == 987654321 ? -1 : dp[k]);
	}

}
