import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		String s = br.readLine();
		String str[] = s.split(" ");

		Integer[] arr = new Integer[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < n; i++) {
			
			while (!stack.isEmpty() && (arr[i] > arr[stack.peek()])) {
				arr[stack.pop()] = arr[i];
			}
			
			stack.push(i);
		}
		
		while(!stack.isEmpty()) {
			arr[stack.pop()] = -1;
		}

		for (int i = 0; i < n - 1; i++) {
			sb.append(arr[i]).append(" ");
		}
		sb.append(arr[n - 1]);
		
		System.out.println(sb);
	}
}
