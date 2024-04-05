import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		HashMap<Integer, Integer> map = new HashMap<>();

		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int[] arr = new int[n + k - 1];

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		for (int i = n; i < n + k - 1; i++) {
			arr[i] = arr[i % n];
		}

		map.put(c, 987654321);
		
		for (int i = 0; i < k; i++) {
			if (map.containsKey(arr[i])) {
				map.put(arr[i], map.get(arr[i]) + 1);
			} else {
				map.put(arr[i], 1);
			}
		}

		int count = map.size();

		for (int i = 1; i < n; i++) {
			if(map.get(arr[i - 1]) == 1) {
				map.remove(arr[i - 1]);
			} else {
				map.put(arr[i - 1], map.get(arr[i - 1]) - 1);
			}
			
			if(map.containsKey(arr[i + k - 1])) {
				map.put(arr[i + k - 1], map.get(arr[i + k - 1]) + 1);
			} else {
				map.put(arr[i + k - 1], 1);
			}
			
			count = Math.max(count, map.size());
		}

		System.out.println(count);
	}
}
