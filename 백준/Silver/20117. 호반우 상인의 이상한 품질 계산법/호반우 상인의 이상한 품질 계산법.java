import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int answer = 0;
		if(n % 2 == 0) {
			for(int i = n / 2; i < n; i++) {
				answer += (2 * arr[i]);
			}
		} else {
			for(int i = 1 + (n / 2); i < n; i++) {
				answer += (2 * arr[i]);
			}
			answer += arr[n / 2];
		}
		System.out.println(answer);
	}
}
