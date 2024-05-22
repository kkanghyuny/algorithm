import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		StringTokenizer s = new StringTokenizer(br.readLine());
		
		// x에는 탑의 높이, y에는 인덱스
		Stack<Point> stack = new Stack<>();
		
		for(int i = 0; i < n; i++) {
			int now = Integer.parseInt(s.nextToken());
			
			// 스택이 비어있지 않고 스택의 맨 위 높이 값이 나보다 작은 경우는 다 빼버림
			while(!stack.isEmpty() && stack.peek().x < now) {
				stack.pop();
			}
			
			// 다 뺀 이후에 비어있지 않다면 그 탑의 인덱스 + 1 한 값을 arr에 추가
			if(!stack.isEmpty()) {
				arr[i] = stack.peek().y + 1;
			}
			
			// 현재 탑을 스택에 쌓음
			stack.push(new Point(now, i));
		}
		
		for(int i = 0; i < n - 1; i++) {
			sb.append(arr[i] + " ");
		}
		sb.append(arr[n - 1]);
		
		System.out.println(sb);
	}
}
