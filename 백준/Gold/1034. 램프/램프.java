import java.io.*;
import java.util.*;

public class Main {
	static int n, m, k, answer;
	static int[][] arr;
	static Map<Integer, Integer> map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		map = new HashMap<>();

		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			for(int j = 0; j < m; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}

		k = Integer.parseInt(br.readLine());
		int answer = 0;

		for(int i = 0; i < n; i++) {
			int zeroCnt = 0;
			for(int j = 0; j < m; j++) {
				if(arr[i][j] == 0) zeroCnt++;
			}

			int onRow = 0;
			if(zeroCnt <= k && zeroCnt % 2 == k % 2) {
				for(int j = 0; j < n; j++) {
					if(isEqual(m, arr[i], arr[j])) onRow++;
				}
				answer = Math.max(answer, onRow);
			}
		}
		System.out.println(answer);
	}

	static boolean isEqual(int c, int[] arr1, int[] arr2) {
		for(int i = 0; i < m; i++) {
			if(arr1[i] != arr2[i]) return false;
		}
		return true;
	}
}
