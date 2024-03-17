import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static char[] arr = new char[25];
	static boolean[] vis = new boolean[25];
	static int count = 0;
	static Queue<Integer> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int idx = 0;
		for (int r = 0; r < 5; r++) {
			String s = br.readLine();
			for (int c = 0; c < 5; c++) {
				arr[idx++] = s.charAt(c);
			}
		}

		for (int i = 0; i < 25; i++) {
			if (i + 7 > arr.length)
				break;
			vis[i] = true;
			if (arr[i] == 'Y') {
				princess(i, i, 1, 1);
			} else {
				princess(i, i, 1, 0);
			}
			vis[i] = false;
		}
		System.out.println(count);

	}

	public static void princess(int start, int idx, int cnt, int ycnt) {
//      시간도 멈춰가며 하나하나 확인도 해보고...
//		Thread.sleep(100);
		if (ycnt >= 4)
			return;
		if (cnt == 7) {
//			System.out.println(start + " " + str);
			bfs(start);
			return;
		}
		for (int i = idx + 1; i < arr.length; i++) {
			if (vis[i])
				continue;
			vis[i] = true;
			if (arr[i] == 'Y')
				ycnt++;
			princess(start, i, cnt + 1, ycnt);
			if (arr[i] == 'Y')
				ycnt--;
			vis[i] = false;
		}
	}

	public static void bfs(int start) {
		boolean[] visit = new boolean[25];
		int linkCount = 1;

		for (int i = 0; i < 25; i++) {
			if (vis[i]) {
				visit[i] = true;
			}
		}

		visit[start] = false;
		queue.add(start);

		while (!queue.isEmpty()) {
			int idx = queue.poll();
//          이런 짓도 했답니다 하하
//			System.out.print(idx + " 이거로 큐 돌아가유");
			visit[idx] = false;

			// 계속 +1, -1, +5, -5 로만 돌려보다가
			// 오른쪽 끝과 다음줄 왼쪽 끝을 인접하다고 했다는걸 못 찾고 남석이 조건 보고
			// 깨닫고 추가해주기...
			if (idx - 1 >= 0 && idx % 5 != 0) {
				if (visit[idx - 1]) {
					visit[idx - 1] = false;
					queue.add(idx - 1);
					linkCount++;
				}
			}

			if (idx + 1 <= 24 && (idx + 1) % 5 != 0) {
				if (visit[idx + 1]) {
					visit[idx + 1] = false;
					queue.add(idx + 1);
					linkCount++;
				}
			}

			if (idx + 5 <= 24) {
				if (visit[idx + 5]) {
					visit[idx + 5] = false;
					queue.add(idx + 5);
					linkCount++;
				}
			}

			if (idx - 5 >= 0) {
				if (visit[idx - 5]) {
					visit[idx - 5] = false;
					queue.add(idx - 5);
					linkCount++;
				}
			}
		}

		if (linkCount == 7) {
//			System.out.println(start + " 일 때 추가됨");
			count++;
		}
	}

}
