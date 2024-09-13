import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		int prev = 0;
		int[] holeSize = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++){
			int curr = Integer.parseInt(st.nextToken()) + i - 1;
			if(curr > prev) prev = curr;
			holeSize[i] = prev;
		}

		int q = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < q; i++) {
			int curr = Integer.parseInt(st.nextToken());
			int left = 1;
			int right = n + 1;
			while(left < right) {
				int mid = (left + right) / 2;
				if(holeSize[mid] >= curr) right = mid;
				else left = mid + 1;
			}
				sb.append(right).append(" ");
		}
		System.out.println(sb);
	}
}
