import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[] cntArr = new int[1_000_001];
		int answer = 0;
		for(int i = 0; i < n; i++) {
			if(cntArr[arr[i]] != 0) {
				cntArr[arr[i]]--;
				cntArr[arr[i] - 1]++;
			} else {
				cntArr[arr[i] - 1]++;
				answer++;
			}
		}
		System.out.println(answer);
	}
}
