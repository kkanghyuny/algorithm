import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] arr1 = new int[n];
		int[] arr2 = new int[n];
		int[] ans = new int[n];
		int cnt1 = 0;
		int cnt2 = 0;

		String s = br.readLine();
		for (int i = 0; i < n; i++) {
			arr1[i] = s.charAt(i) - '0';
			arr2[i] = s.charAt(i) - '0';
		}
		s = br.readLine();
		for (int i = 0; i < n; i++) {
			ans[i] = s.charAt(i) - '0';
		}

		arr1[0] = 1 - arr1[0];
		arr1[1] = 1 - arr1[1];
		cnt1++;

		for (int i = 0; i < n - 1; i++) {
			if (arr1[i] != ans[i]) {
				arr1[i] = 1 - arr1[i];
				arr1[i + 1] = 1 - arr1[i + 1];
				if(i + 2 < n) arr1[i + 2] = 1 - arr1[i + 2];
				cnt1++;
			}

			if (arr2[i] != ans[i]) {
				arr2[i] = 1 - arr2[i];
				arr2[i + 1] = 1 - arr2[i + 1];
				if(i + 2 < n) arr2[i + 2] = 1 - arr2[i + 2];
				cnt2++;
			}
		}
		
		if(arr1[n - 1] != ans[n - 1] && arr2[n - 1] != ans[n - 1]) {
			System.out.println(-1);
			return;
		}
		
		if (arr1[n - 1] != ans[n - 1]) {
			System.out.println(cnt2);
			return;
		}

		if (arr2[n - 1] != ans[n - 1]) {
			System.out.println(cnt1);
			return;
		}

		System.out.println(Math.min(cnt1, cnt2));
	}
}
