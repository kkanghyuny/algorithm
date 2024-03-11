import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int answer = 0;
		
		PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> a.x - b.x);
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			pq.add(new Point (Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		for(int i = 0; i < n; i++) {
			Point now = pq.poll();
			int x = now.x;
			int y = now.y;
			
			// 시작때도 돌테고 초기화 됐을 때도 돌 첫번째 if 문
			if(min > x && max < y) {
				min = x;
				max = y;
			} 
			// 그 다음으로 x가 max보다 크다면 볼 것도 없이 이전거 선 길이 더하고 초기화
			else if(x > max) {
				answer += max - min;
				min = x;
				max = y;
			} 
			// 나머지 경우들 처리
			else if(min <= x && max < y) {
				max = y;
			} else if(min > x && max >= y) {
				min = x;
			}
		}
		
		answer += max - min;
		
		System.out.println(answer);
	}
}
