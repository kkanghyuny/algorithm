import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			if(n == 0) break;
			long[] arr = new long[n + 2];
			long answer = 0;
			for (int i = 1; i <= n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Stack<Integer> stack = new Stack<>();
			stack.push(0);
			for (int i = 1; i <= n + 1; i++) {
				while (!stack.isEmpty()) {
					int top = stack.peek();
					if (arr[top] <= arr[i])
						break;
					stack.pop();
					answer = Math.max(answer, arr[top] * (i - stack.peek() - 1));
				}
				stack.push(i);
			}
			sb.append(answer).append('\n');
		}
		System.out.println(sb);
	}
}
