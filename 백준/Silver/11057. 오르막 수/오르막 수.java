import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[][] dp = new int[n + 1][11];

		for(int i = 0; i <= 10; i++){
			dp[0][i] = 1;
		}

		for(int i = 1; i <= n; i++){
			for(int j = 10; j >= 0; j--){
				if(j == 10) {
					dp[i][j] = 1;
					continue;
				}

				dp[i][j]  = (dp[i][j + 1] + dp[i - 1][j]) % 10007;
			}
		}

		System.out.println(dp[n][1]);
	}
}
