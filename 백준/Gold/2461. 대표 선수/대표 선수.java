import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < n; i++) {
			Arrays.sort(arr[i]);
		}
		
		int answer = Integer.MAX_VALUE;
		int[] idxArr = new int[n];
		int cnt = 0;
		while(true) {
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			int semiMin = Integer.MAX_VALUE;
			int minIdx = 0;
			for(int i = 0; i < n; i++) {
				if(arr[i][idxArr[i]] > max) max = arr[i][idxArr[i]];
				if(arr[i][idxArr[i]] < min) {
					min = arr[i][idxArr[i]];
					minIdx = i;
				}
			}
			answer = Math.min(max - min, answer);
			idxArr[minIdx]++;
			if(idxArr[minIdx] >= m) break;
		}
		System.out.println(answer);
	}
}
