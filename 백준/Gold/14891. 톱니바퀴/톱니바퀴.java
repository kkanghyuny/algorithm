import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String s;

		LinkedList<Integer> deque1 = new LinkedList<>();
		LinkedList<Integer> deque2 = new LinkedList<>();
		LinkedList<Integer> deque3 = new LinkedList<>();
		LinkedList<Integer> deque4 = new LinkedList<>();

		int answer = 0;

		// 1,3은 시계방향 2,4는 반시계로 넣어 똑같은 방향으로 빼고 넣으면 알아서 시계 반시계가 맞춰짐
		s = br.readLine();
		for (int i = 0; i < 8; i++) {
			deque1.addLast(s.charAt(i) - '0');
		}
		s = br.readLine();
		for (int i = 0; i < 8; i++) {
			deque2.addFirst(s.charAt(i) - '0');
		}
		deque2.addFirst(deque2.pollLast());
		s = br.readLine();
		for (int i = 0; i < 8; i++) {
			deque3.addLast(s.charAt(i) - '0');
		}
		s = br.readLine();
		for (int i = 0; i < 8; i++) {
			deque4.addFirst(s.charAt(i) - '0');
		}
		deque4.addFirst(deque4.pollLast());
		
		int k = Integer.parseInt(br.readLine());

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int number = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());

			switch (number) {
			case 1: {
				if (dir == 1) {
					// 맞닿은 부분이 같지 않다면
					if (deque1.get(2) != (deque2.get(2))) {
						// 맞닿은 부분이 같지 않다면
						if (deque2.get(6) != deque3.get(6)) {
							// 맞닿은 부분이 같지 않다면
							if (deque3.get(2) != deque4.get(2)) {
								deque4.addFirst(deque4.pollLast());
							}
							deque3.addFirst(deque3.pollLast());
						}
						deque2.addFirst(deque2.pollLast());
					}
					deque1.addFirst(deque1.pollLast());
				} else if (dir == -1) {
					// 맞닿은 부분이 같지 않다면
					if (deque1.get(2) != deque2.get(2)) {
						// 맞닿은 부분이 같지 않다면
						if (deque2.get(6) != deque3.get(6)) {
							// 맞닿은 부분이 같지 않다면
							if (deque3.get(2) != deque4.get(2)) {
								deque4.addLast(deque4.pollFirst());
							}
							deque3.addLast(deque3.pollFirst());
						}
						deque2.addLast(deque2.pollFirst());
					}
					deque1.addLast(deque1.pollFirst());
				}
				break;
			}
			case 2: {
				if (dir == 1) {
					// 맞닿은 부분이 같지 않다면
					if (deque2.get(6) != deque3.get(6)) {
						// 맞닿은 부분이 같지 않다면
						if (deque3.get(2) != deque4.get(2)) {
							deque4.addLast(deque4.pollFirst());
						}
						deque3.addLast(deque3.pollFirst());
					}
					// 맞닿은 부분이 같지 않다면
					if (deque1.get(2) != deque2.get(2)) {
						deque1.addLast(deque1.pollFirst());
					}
					deque2.addLast(deque2.pollFirst());
				} else if (dir == -1) {
					// 맞닿은 부분이 같지 않다면
					if (deque2.get(6) != deque3.get(6)) {
						// 맞닿은 부분이 같지 않다면
						if (deque3.get(2) != deque4.get(2)) {
							deque4.addFirst(deque4.pollLast());
						}
						deque3.addFirst(deque3.pollLast());
					}
					// 맞닿은 부분이 같지 않다면
					if (deque1.get(2) != deque2.get(2)) {
						deque1.addFirst(deque1.pollLast());
					}
					deque2.addFirst(deque2.pollLast());
				}
				break;
			}
			case 3: {
				if (dir == 1) {
					// 맞닿은 부분이 같지 않다면
					if (deque3.get(6) != deque2.get(6)) {
						// 맞닿은 부분이 같지 않다면
						if (deque1.get(2) != deque2.get(2)) {
							deque1.addFirst(deque1.pollLast());
						}
						deque2.addFirst(deque2.pollLast());
					}
					// 맞닿은 부분이 같지 않다면
					if (deque3.get(2) != deque4.get(2)) {
						deque4.addFirst(deque4.pollLast());
					}
					deque3.addFirst(deque3.pollLast());
				} else if (dir == -1) {
					// 맞닿은 부분이 같지 않다면
					if (deque3.get(6) != deque2.get(6)) {
						// 맞닿은 부분이 같지 않다면
						if (deque1.get(2) != deque2.get(2)) {
							deque1.addLast(deque1.pollFirst());
						}
						deque2.addLast(deque2.pollFirst());
					}
					// 맞닿은 부분이 같지 않다면
					if (deque3.get(2) != deque4.get(2)) {
						deque4.addLast(deque4.pollFirst());
					}
					deque3.addLast(deque3.pollFirst());
				}
				break;
			}
			case 4: {
				if (dir == 1) {
					// 맞닿은 부분이 같지 않다면
					if (deque4.get(2) != deque3.get(2)) {
						// 맞닿은 부분이 같지 않다면
						if (deque3.get(6) != deque2.get(6)) {
							// 맞닿은 부분이 같지 않다면
							if (deque2.get(2) != deque1.get(2)) {
								deque1.addLast(deque1.pollFirst());
							}
							deque2.addLast(deque2.pollFirst());
						}
						deque3.addLast(deque3.pollFirst());
					}
					deque4.addLast(deque4.pollFirst());
				} else if (dir == -1) {
					// 맞닿은 부분이 같지 않다면
					if (deque4.get(2) != deque3.get(2)) {
						// 맞닿은 부분이 같지 않다면
						if (deque3.get(6) != deque2.get(6)) {
							// 맞닿은 부분이 같지 않다면
							if (deque2.get(2) != deque1.get(2)) {
								deque1.addFirst(deque1.pollLast());
							}
							deque2.addFirst(deque2.pollLast());
						}
						deque3.addFirst(deque3.pollLast());
					}
					deque4.addFirst(deque4.pollLast());
				}
				break;
			}
			}
			
		}

		if (deque1.peekFirst() == 1)
			answer += 1;
		if (deque2.peekFirst() == 1)
			answer += 2;
		if (deque3.peekFirst() == 1)
			answer += 4;
		if (deque4.peekFirst() == 1)
			answer += 8;

		System.out.println(answer);
	}
}
