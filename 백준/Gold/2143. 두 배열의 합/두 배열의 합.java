import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<Integer, Integer> map = new HashMap<>();
		StringTokenizer st;

		long count = 0;
		int t = Integer.parseInt(br.readLine());
		int n1 = Integer.parseInt(br.readLine());
		int[] dp1 = new int[n1];

		st = new StringTokenizer(br.readLine());
		dp1[0] = Integer.parseInt(st.nextToken());
		for (int i = 1; i < n1; i++) {
			dp1[i] = dp1[i - 1] + Integer.parseInt(st.nextToken());
		}

		int n2 = Integer.parseInt(br.readLine());
		int[] dp2 = new int[n2];

		st = new StringTokenizer(br.readLine());
		dp2[0] = Integer.parseInt(st.nextToken());
		for (int i = 1; i < n2; i++) {
			dp2[i] = dp2[i - 1] + Integer.parseInt(st.nextToken());
		}

		int size1 = (n1 * (n1 + 1)) / 2;

		int[] arr1 = new int[size1];

		int idx = 0;
		for (int i = 0; i < n1; i++) {
			arr1[idx++] = dp1[i];
			for (int j = 0; j < i; j++) {
				arr1[idx++] = dp1[i] - dp1[j];
			}
		}

		for (int i = 0; i < n2; i++) {
			if (map.containsKey(dp2[i])) {
				int num = map.get(dp2[i]);
				map.put(dp2[i], num + 1);
			} else {
				map.put(dp2[i], 1);
			}
			for (int j = 0; j < i; j++) {
				int val = dp2[i] - dp2[j];
				if(map.containsKey(val)) {
					int num = map.get(val);
					map.put(val, num + 1);
				} else {
					map.put(val, 1);
				}
			}
		}

		for (int i = 0; i < size1; i++) {			
			int num = t - arr1[i];
			if(map.containsKey(num)) {
				count += map.get(num);
			}
		}
		
		System.out.println(count);
	}
}
