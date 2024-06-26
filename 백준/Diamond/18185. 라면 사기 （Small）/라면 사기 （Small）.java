import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] arr = new int[n + 2];

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int ans = 0;

		for (int i = 0; i < n; i++) {
			if (arr[i] <= 0) continue;

			// 일단 다 빼고
			int now = arr[i];
			ans += now * 3;
			arr[i] = 0;

			// i + 1번째도 가능한만큼 빼고
			now = Math.min(now, arr[i + 1]);
			ans += now * 2;
			arr[i + 1] -= now;

			// i + 2번째는 i가 i + 1이 됐을때 두개 뒤에도 연결될 수 있게 징검다리 역할 남기기
			now = Math.min(now, arr[i + 2] - Math.min(arr[i + 1], arr[i + 2]));
			ans += now * 2;
			arr[i + 2] -= now;
		}

		System.out.println(ans);
	}
}
