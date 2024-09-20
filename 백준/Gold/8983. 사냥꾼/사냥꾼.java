import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		int answer = 0;

		int[] shotFrom = new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			shotFrom[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(shotFrom);

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if(c > l) continue;

			int left = 0;
			int right = m - 1;

			while(left < right) {
				int mid = (left + right) / 2;
				if(shotFrom[mid] < r) left = mid + 1;
				else right = mid;
			}

			int hunter = shotFrom[left];
			if(Math.abs(hunter - r) + c <= l) answer++;
			else if (left > 0 && Math.abs(shotFrom[left - 1] - r) + c <= l) answer++;
		}
		System.out.println(answer);
	}
}
