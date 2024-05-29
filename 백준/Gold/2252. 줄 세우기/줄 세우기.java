import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Student implements Comparable<Student> {
		int id;
		int height;

		public Student(int id, int height) {
			this.id = id;
			this.height = height;
		}

		@Override
		public int compareTo(Student o) {
			return this.height - o.height;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		Student[] list = new Student[n + 1];

		for (int i = 1; i <= n; i++) {
			list[i] = new Student(i, 0);
		}

		Queue<int[]> queue = new ArrayDeque<>();

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			queue.offer(new int[] { a, b });
		}

		int size = m;
		while (size-- > 0) {
			int[] curr = queue.poll();

			int a = curr[0];
			int b = curr[1];
			
			if(list[a].height + 1 > list[b].height) {
				list[b].height = list[a].height + 1;
				size = m;
			}
			queue.offer(curr);
		}

		PriorityQueue<Student> pq = new PriorityQueue<>();

		for (int i = 1; i <= n; i++) {
			pq.offer(list[i]);
		}

		while (!pq.isEmpty()) {
			sb.append(pq.poll().id).append(' ');
		}

		System.out.println(sb);
	}
}
