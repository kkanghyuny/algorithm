import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Map<Integer, Integer> map = new HashMap<>();
		int answer = 0;
		for(int i = 0; i < n; i++) {
			if(map.containsKey(arr[i])) {
				int cnt = map.get(arr[i]);
				if(cnt == 1) {
					map.remove(arr[i]);
				} else {
					map.put(arr[i], cnt - 1);
				}
				map.put(arr[i] - 1, map.getOrDefault(arr[i] - 1, 0) + 1);
			} else {
				map.put(arr[i] - 1, map.getOrDefault(arr[i] - 1, 0) + 1);
				answer++;
			}
		}
		System.out.println(answer);
	}
}
