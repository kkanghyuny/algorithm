import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			int dist = - (Integer.parseInt(st.nextToken()) - Integer.parseInt(st.nextToken()));
			int max = (int)Math.sqrt(dist);

			if(max == Math.sqrt(dist)) sb.append(2 * max - 1).append('\n');
			else if(dist <= max * max + max) sb.append(2 * max).append('\n');
			else sb.append(2 * max + 1).append('\n');
		}

		System.out.println(sb);
	}
}
