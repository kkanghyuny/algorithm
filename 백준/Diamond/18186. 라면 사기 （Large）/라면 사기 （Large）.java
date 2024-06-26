import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		long c = Long.parseLong(st.nextToken());

		long[] arr = new long[n + 2];
		long cnt = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Long.parseLong(st.nextToken());
			cnt += arr[i];
		}

		long ans = 0;

		if (b <= c) {
			ans += (cnt * b);
		} else {
			for (int i = 0; i < n; i++) {
				if (arr[i] <= 0) continue;

				// 일단 다 빼고
				long now = arr[i];
				ans += now * b;
				arr[i] = 0;

				// i + 1번째도 가능한만큼 빼고
				now = Math.min(now, arr[i + 1]);
				ans += now * c;
				arr[i + 1] -= now;

				// i + 2번째는 i가 i + 1이 됐을때 두개 뒤에도 연결될 수 있게 징검다리 역할 남기기
				now = Math.min(now, arr[i + 2] - Math.min(arr[i + 1], arr[i + 2]));
				ans += now * c;
				arr[i + 2] -= now;
			}
		}
		System.out.println(ans);
	}
}
