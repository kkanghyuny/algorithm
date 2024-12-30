import java.io.*;
import java.util.*;

public class Main {	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[][] human = new int[n][2];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			human[i][0] = Integer.parseInt(st.nextToken());
			human[i][1] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0; i < n; i++) {
			int cnt = 1;
			for(int j = 0; j < n; j++) {
				if(i == j) continue;
				if(human[i][0] < human[j][0] && human[i][1] < human[j][1]) cnt++;
			}
			sb.append(cnt).append(' ');
		}
 		System.out.println(sb);
	}
}
