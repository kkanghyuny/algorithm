import java.util.Scanner;

public class Main {
	static int[][] dp = new int[30][30];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		
		for (int tc = 0; tc < t; tc++) {
			int n = sc.nextInt();
			int m = sc.nextInt();

			System.out.println(comb(m, n));
		}
	}

	public static int comb(int n, int r) {
		if (dp[n][r] > 0) {
			return dp[n][r];
		}
		if (r == 0 || n == r) {
			return dp[n][r] = 1;
		}
		
		return dp[n][r] = comb(n - 1, r - 1) + comb(n - 1, r);
	}
}