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
		// [0~n-1 번째 날에][몇번 떡을 만들었는지]
		boolean[][] make = new boolean[n][10];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			for (int j = 0; j < size; j++) {
				make[i][Integer.parseInt(st.nextToken())] = true;
			}
		}

		boolean[][] vis = new boolean[n][10];
		int[][] answer = new int[n][10];

		for (int i = 1; i < 10; i++) {
			if (make[0][i])
				answer[0][i] = i;
		}

		for (int k = 0; k < n - 1; k++) {
			for (int i = 1; i < 10; i++) {
				if (answer[k][i] == 0) continue;
				for (int j = 1; j < 10; j++) {
					if (i == j)	continue;
					if (make[k + 1][j])	answer[k + 1][j] = i;
				}
			}
		}

		boolean impossible = true;
		int idx = 0;
		for (int i = 1; i < 10; i++) {
			if (answer[n - 1][i] != 0) {
				impossible = false;
				idx = i;
				break;
			}
		}

		if (impossible) {
			System.out.println(-1);
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		int[] result = new int[n];
		
		int i = n - 1;
 		while(i >= 0) {
 			result[i] = idx;
 			idx = answer[i][idx];
 			i--;
		}
 		
 		for(int k = 0; k < n; k++) {
 			sb.append(result[k]).append('\n');
 		}
		System.out.println(sb);
	}
}
