import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		Integer[] dist = new Integer[n - 1];
		for(int i = 0; i < n - 1; i++) {
			dist[i] = arr[i + 1] - arr[i];
		}

		Arrays.sort(dist, Collections.reverseOrder());

		int answer = 0;
		for(int i = k - 1; i < n - 1; i++) {
			answer += dist[i];
		}

		System.out.println(answer);
	}
}
