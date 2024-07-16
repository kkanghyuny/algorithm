import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Tree implements Comparable<Tree> {
		int start;
		int end;
		int height;
		int num;

		public Tree(int start, int end, int height, int num) {
			this.start = start;
			this.end = end;
			this.height = height;
			this.num = num;
		}

		@Override
		public int compareTo(Tree o) {
			if(this.start == o.start){
				return o.end - this.end;
			}
			return this.start - o.start;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		int[] group = new int[n + 1];
		for(int i = 1; i <= n; i++){
			group[i] = i;
		}

		PriorityQueue<Tree> pq = new PriorityQueue<>();
		for(int i = 1; i <= n; i++){
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			pq.offer(new Tree(start, end, height, i));
		}

		int parent = 0;
		int max_end = 0;
		while(!pq.isEmpty()){
			Tree curr = pq.poll();
			int start = curr.start;
			int end = curr.end;
			int num = curr.num;

			if(start > max_end){
				max_end = end;
				parent = num;
			} else if(end < max_end){
				group[num] = parent;
			} else {
				max_end = end;
				group[num] = parent;
			}
		}

		for(int i = 0; i < q; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(group[a] == group[b]){
				sb.append(1).append('\n');
			} else {
				sb.append(0).append('\n');
			}
		}

		System.out.println(sb);
	}
}
