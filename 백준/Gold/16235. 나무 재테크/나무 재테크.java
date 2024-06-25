import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] A = new int[n + 1][n + 1];
		int[][] arr = new int[n + 1][n + 1];
		int[][] death = new int[n + 1][n + 1];
		int[][] breed = new int[n + 1][n + 1];
		Deque<Integer>[][] tree = new ArrayDeque[n + 1][n + 1];
		Queue<Integer> queue = new ArrayDeque<>();
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				arr[i][j] = 5;
				tree[i][j] = new ArrayDeque<>();
			}
		}
		
		while(m-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			
			tree[r][c].offer(age);
		}
		
		while(k-- > 0) {
			// 봄
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					int size = tree[i][j].size();
					
					while(size-- > 0) {
						int age = tree[i][j].pollFirst();
						if(age <= arr[i][j]) {
							arr[i][j] -= age++;
							queue.offer(age);
						} else {
							death[i][j] += (age / 2);
						}
					}
					
					while(!queue.isEmpty()) {
						int poll = queue.poll();
						tree[i][j].offerLast(poll);
						
						// 번식하는 나무 몇개인지 카운트
						if(poll % 5 == 0) {
							breed[i][j]++;
						}
					}
				}
			}
			
			// 여름 + 가을 + 겨울
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					// 여름
					arr[i][j] += death[i][j];
					death[i][j] = 0;
					
					// 가을
					while(breed[i][j] > 0) {
						breed[i][j]--;
						for(int r = i - 1; r <= i + 1; r++) {
							for(int c = j - 1; c <= j + 1; c++) {
								if(r < 1 || r > n || c < 1 || c > n) continue;
								if(r == i && c == j) continue;
								
								tree[r][c].offerFirst(1);
							}
						}
					}
					
					// 겨울
					arr[i][j] += A[i][j];
				}
			}
		}
		
		long ans = 0;
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				ans += tree[i][j].size();
			}
		}
		
		System.out.println(ans);
	}
}
