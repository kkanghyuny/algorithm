import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<String> queue = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		
		boolean answer = false;

		// S
		String first = br.readLine();
		
		// T
		String second = br.readLine();
		sb.append(second);
		sb.reverse();
		
		// T 거꾸로
		String secondRev = sb.toString();
		
		sb.setLength(0);
		queue.offer(first);
		
		while(!queue.isEmpty()) {
			String now = queue.poll();

			// T, T거꾸로에 포함이 안되어 있다면 이미 가망 X
			if(!second.contains(now) && !secondRev.contains(now)) {
				continue;
			}
			
			if(now.length() == second.length() && now.equals(second)) {
				answer = true;
				break;
			}
			
			if(now.length() == second.length()) {
				continue;
			}
			
			queue.offer(now + "A");
			// sb 초기화
			sb.setLength(0);
			// sb에 now 넣고
			sb.append(now).append("B");
			// 뒤집기
			sb.reverse();
			// 뒤집은거에 B 추가한걸 queue에 추가
			queue.offer(sb.toString());
		}
		
		if(answer) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
		
	}
}
