import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int answer = 0;
		boolean allZero = false;
		a: while(!allZero) {
			allZero = true;
			for (int i = 0; i < n; i++) {
				if (arr[i] % 2 != 0) {
					arr[i]--;
					answer++;
					allZero = false;
					continue a;
				} else if (arr[i] != 0) {
					allZero = false;
				}

				if(i == n - 1 && !allZero) {
					for (int j = 0; j < n; j++) {
						arr[j] /= 2;
					}
					answer++;
				}
			}
		}

		System.out.println(answer);
	}
}
