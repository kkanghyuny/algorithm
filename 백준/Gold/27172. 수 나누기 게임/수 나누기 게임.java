import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		int max = 0;
		int[] score = new int[n + 1];

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}

		int[] pos = new int[max + 1];

		for (int i = 0; i < n; i++) {
			pos[arr[i]] = i + 1;
		}

		for (int val : arr) {
			for (int i = val * 2; i <= max; i += val) {
				if (pos[i] != 0) {
					score[pos[i]]--;
					score[pos[val]]++;
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			sb.append(score[i]).append(' ');
		}

		System.out.println(sb);
	}
}
