import java.io.*;
import java.util.*;

public class Main {
	static int n, maxValue, minValue;
	static int[] arr, cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		maxValue = -1_000_000_001;
		minValue = 1_000_000_001;

		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		cnt = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			cnt[i] = Integer.parseInt(st.nextToken());
		}

		calculate(arr[0], 1);
		System.out.println(maxValue + "\n" + minValue);
	}

	static void calculate(int nowValue, int idx) {
		if(idx == n - 1) {
			for(int i = 0; i < 4; i++) {
				if(cnt[i] == 0) continue;
				int changeValue = nowValue;
				cnt[i]--;
				switch(i) {
					case 0: changeValue += arr[idx]; break;
					case 1: changeValue -= arr[idx]; break;
					case 2: changeValue *= arr[idx]; break;
					case 3: changeValue /= arr[idx]; break;
				}
				cnt[i]++;
				maxValue = Math.max(maxValue, changeValue);
				minValue = Math.min(minValue, changeValue);
			}
			return;
		}

		for(int i = 0; i < 4; i++) {
			if(cnt[i] == 0) continue;
			int changeValue = nowValue;
			cnt[i]--;
			switch(i) {
				case 0: changeValue += arr[idx]; break;
				case 1: changeValue -= arr[idx]; break;
				case 2: changeValue *= arr[idx]; break;
				case 3: changeValue /= arr[idx]; break;
			}
			calculate(changeValue, idx + 1);
			cnt[i]++;
		}
	}
}
