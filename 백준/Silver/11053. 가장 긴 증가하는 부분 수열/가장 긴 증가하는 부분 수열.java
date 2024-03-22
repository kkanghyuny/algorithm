import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		int[] dp = new int[n];
		int answer = 1;

		dp[0] = 1;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i < n; i++) {
			int now = arr[i];
			int max = 0;
			for(int j = i - 1; j >= 0; j--) {
				if(arr[j] < now && dp[j] > max) {
					max = dp[j];
				}
			}
			
			dp[i] = max + 1;
			answer = Math.max(dp[i], answer);
		}
		
		System.out.println(answer);
	}
}
