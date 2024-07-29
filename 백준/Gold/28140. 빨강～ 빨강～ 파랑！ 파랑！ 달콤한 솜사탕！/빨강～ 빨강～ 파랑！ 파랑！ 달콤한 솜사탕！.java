import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		String s = br.readLine();

		int[] arrR = new int[n];
		int[] arrB = new int[n];
		
		int prevR = n;
		int prevB = n;

		for (int i = n - 1; i >= 0; i--) {
			char c = s.charAt(i);
			
			if(c == 'R') {
				prevR = i;
			} else if(c == 'B') {
				prevB = i;
			}
			
			arrR[i] = prevR;
			arrB[i] = prevB;
		}

		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());

			int a = arrR[left];
			if(a >= right) {
				sb.append("-1\n");
				continue;
			}
			
			int b = arrR[a + 1];
			if(b >= right) {
				sb.append("-1\n");
				continue;
			}
			
			int c = arrB[b + 1];
			if(c >= right) {
				sb.append("-1\n");
				continue;
			}
			
			int d = arrB[c + 1];
			if(d > right) {
				sb.append("-1\n");
				continue;
			}
			
			sb.append(a).append(" ").append(b).append(" ").append(c).append(" ").append(d).append('\n');
		}

		System.out.println(sb);

	}
}
