import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;	
		
		int n = Integer.parseInt(br.readLine());
		int answer = 0;
		
		int[][] arr = new int[n][n];
		int[][] dp = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				if(j <= i) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		dp[0][0] = arr[0][0];
		
		for(int i = 1; i < n; i++) {
			dp[i][0] = dp[i - 1][0] + arr[i][0];
		}

		for(int i = 1; i < n; i++) {
			for(int j = 1; j < n; j++) {
				dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + arr[i][j];
			}
		}
		
		for(int i = 0; i < n; i++) {
			answer = Math.max(dp[n - 1][i], answer);
		}
		
		System.out.println(answer);
	}
}
