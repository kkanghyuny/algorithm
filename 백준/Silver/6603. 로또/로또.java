import java.util.Scanner;

public class Main {
	static int[] arr;
	static boolean[] vis;
	static StringBuilder sb;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		sb = new StringBuilder();
		
		while(true) {
			int k = sc.nextInt();
			
			if(k == 0) break;
			
			arr = new int[k];
			vis = new boolean[k];
			
			
			for(int i = 0; i < k; i++) {
				arr[i] = sc.nextInt();
			}
			
			for(int i = 0; i < arr.length; i++) {
				if(i + 6 > arr.length) break;
				vis[i] = true;
				backTracking(i, 1, arr[i] + " ");
				vis[i] = false;
			}
			
			// 테케 하나 끝나고 한 줄 띄기
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
	
	public static void backTracking(int idx, int cnt, String str) {
		if(idx >= arr.length || idx + 6 - cnt > arr.length) return;
		
		if(cnt == 6) {
			sb.append(str).append('\n');
			return;
		}
		
		for(int i = idx + 1; i < arr.length; i++) {
			if(vis[i]) continue;
			vis[i] = true;
			backTracking(i, cnt + 1, str + arr[i] + " ");
			vis[i] = false;
		}
	}
}
