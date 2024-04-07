import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 테케 갯수 t
		int t = Integer.parseInt(br.readLine());
		
		// 우선순위 큐
		PriorityQueue<Long> pq = new PriorityQueue<>();
		
		// 테케 갯수만큼 for문
		for(int tc = 0; tc < t; tc++) {
			// 입력 데이터 개수 n
			int n = Integer.parseInt(br.readLine());
			// 입력 데이터
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 최소 비용을 계산할 sum
			long sum = 0;
			
			// 큐에 값들을 넣어주고
			for(int i = 0; i < n; i++) {
				pq.add((long)Integer.parseInt(st.nextToken()));
			}
			
			// 큐의 사이즈가 1보다 클때만 작은 값부터 두개씩 뽑아서 더하고 큐에도 다시 넣어주기
			while(pq.size() > 1) {
				long a = pq.poll();
				long b = pq.poll();
				sum += a + b;
				pq.add(a+b);
			}
			// 최소 비용 출력
			System.out.println(sum);
			
			// 다음 테케를 위해 큐 비워주기
			pq.clear();
		}
	}
}
