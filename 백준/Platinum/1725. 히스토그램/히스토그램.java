import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n + 2];
		int answer = 0;
		for(int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		for(int i = 1; i <= n + 1; i++) {
			while(!stack.isEmpty()) {
				int top = stack.peek();
				if(arr[top] <= arr[i]) break;
				stack.pop();
				answer = Math.max(answer, arr[top] * (i - stack.peek() - 1));
			}
			stack.push(i);
		}
		System.out.println(answer);
	}
}
