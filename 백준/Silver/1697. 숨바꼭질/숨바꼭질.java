import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 수빈이가 있는 위치 n, 동생이 있는 위치 k
		int n = sc.nextInt();
		int k = sc.nextInt();

		// 답을 할당할 answer
		int answer = 0;

		// 더하기1, 빼기1, 곱하기2를 할 arr배열, 방문 여부를 저장할 vis배열
		int[] arr = { 1, -1, 2 };
		boolean[] vis = new boolean[100001];

		// queue 선언 (현재위치, 걸린시간) Point로 하려 했는데 ArrayDeque 에는 Point가 안들어가는거 같아서 일단
		// LinkedList 사용
		Queue<Point> queue = new LinkedList<Point>();

		// 현재 수빈이 위치와 시간은 0으로 queue에 입력 및 위치 방문 기록
		queue.add(new Point(n, 0));
		vis[n] = true;

		// 무한루프 돌기 시작
		while (true) {
			// 현재 위치 now_x 와 현재 위치까지 걸린 시간 now_time
			Point now = queue.poll();
			int now_x = now.x;
			int now_time = now.y;

			// 현재 위치가 동생 위치라면 answer에 걸린 시간 할당하고 break
			if (now_x == k) {
//            	System.out.println(now.x + " 끝 " + now.y);
				answer = now.y;
				break;
			}
//            System.out.println(now.x + " " + now.y);

			// +1, -1, *2 를 하기 위한 for문
			for (int dir = 0; dir < 3; dir++) {
				// +1, -1은 그대로
				if (dir == 0 || dir == 1) {
					int nx = now_x + arr[dir];
					// 쓸데 없는 queue가 생기는거 방지 = 범위 안쪽, 방문한거 안들리기
					if (nx >= 0 && nx <= 100000 && !(vis[nx])) {
						queue.add(new Point(nx, now_time + 1));
						vis[nx] = true;
					}

					// dir=2일때는 곱해주기
				} else if (dir == 2) {
					int nx = now_x * arr[dir];
					// 쓸데 없는 queue가 생기는거 방지 = 범위 안쪽, 방문한거 안들리기
					if (nx <= 100001 && !(vis[nx])) {
						queue.add(new Point(nx, now_time + 1));
						vis[nx] = true;
					}
				}
			}
		}
		// 걸린 최소 시간 출력
		System.out.println(answer);
	}
}