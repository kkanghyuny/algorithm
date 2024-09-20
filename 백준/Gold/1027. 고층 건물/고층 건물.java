import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int answer = 0;
		for (int i = 0; i < n; i++) {
			double lean = 1_000_000_001;
			int cnt = 0;
			for(int j = 1; i - j >= 0; j++) {
				double currLean = (double)(arr[i] - arr[i - j]) / j;
				if(currLean < lean) {
					cnt++;
					lean = currLean;
				}
			}
			lean = 1_000_000_001;
			for(int j = 1; i + j < n; j++) {
				double currLean = (double)(arr[i] - arr[i + j]) / j;
				if(currLean < lean) {
					cnt++;
					lean = currLean;
				}
			}
			answer = Math.max(answer, cnt);
		}
		System.out.println(answer);
	}
}
