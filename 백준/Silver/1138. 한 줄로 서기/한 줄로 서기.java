import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int[] arr = new int[n];
		int prev = 11;
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(arr[i] == 0) prev = Math.min(prev, i);
		}
		
		boolean[] vis = new boolean[n];
		for(int i = 0; i < n; i++) {
			sb.append(prev + 1).append(' ');
			vis[prev] = true;
			for(int j = 0; j < prev; j++) {
				arr[j]--;
			}
			prev = 11;
			for(int j = 0; j < n; j++) {
				if(!vis[j] && arr[j] == 0) prev = Math.min(prev, j);
			}
		}
		System.out.println(sb);
	}
}
