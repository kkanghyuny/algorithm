import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	static class Point implements Comparable<Point> {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			return this.x - o.x;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Point> pq = new PriorityQueue<>();
		Stack<Integer> stack = new Stack<>();
		int answer = 0;

		while(n-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			pq.add(new Point(x, y));
		}

		while(!pq.isEmpty()) {
			Point curr = pq.poll();
			int high = curr.y;

			while(!stack.isEmpty() && stack.peek() > high) {
				stack.pop();
				answer++;
			}

			if(!stack.isEmpty() && stack.peek() == high) continue;
			if(high == 0) continue;

			stack.push(high);
		}

		answer += stack.size();

		System.out.println(answer);
	}
}
