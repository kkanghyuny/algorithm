import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		int answer = 0;

		while(n-- > 0) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			int y = Integer.parseInt(st.nextToken());


			while(!stack.isEmpty() && stack.peek() > y) {
				stack.pop();
				answer++;
			}

			if(!stack.isEmpty() && stack.peek() == y) continue;
			if(y == 0) continue;

			stack.push(y);
		}

		answer += stack.size();

		System.out.println(answer);
	}
}
