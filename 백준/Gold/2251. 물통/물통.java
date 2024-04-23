import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		HashSet<Integer> set = new HashSet<>();
		StringBuilder sb = new StringBuilder();
		
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int hash = c + 1;
		
		boolean[] vis = new boolean[c + 1];
		
		Queue<int[]> queue = new ArrayDeque<>();
		
		queue.offer(new int[] {0, 0, c});
		set.add(c);
		
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			int nowA = curr[0];
			int nowB = curr[1];
			int nowC = curr[2];
			
			if(nowA == 0) {
				vis[nowC] = true;
			}
			
			if(nowA > 0) {
				int cal = Math.min(b - nowB, nowA);
				int sum = (nowA - cal) * (int)Math.pow(hash, 2) + (nowB + cal) * hash + nowC;
				if(!set.contains(sum)) {
					set.add(sum);
					queue.offer(new int[] {nowA - cal, nowB + cal, nowC});
				}
				cal = Math.min(c - nowC, nowA);
				sum = (nowA - cal) * (int)Math.pow(hash, 2) + (nowB  * hash) + nowC + cal;
				if(!set.contains(sum)) {
					set.add(sum);
					queue.offer(new int[] {nowA - cal, nowB, nowC + cal});
				}
			}
			
			if(nowB > 0) {
				int cal = Math.min(nowB, a - nowA);
				int sum = (nowA + cal) * (int)Math.pow(hash, 2) + (nowB - cal) * hash + nowC;
				if(!set.contains(sum)) {
					set.add(sum);
					queue.offer(new int[] {nowA + cal, nowB - cal, nowC});
				}
				cal = Math.min(c - nowC, nowB);
				sum = nowA * (int)Math.pow(hash, 2) + (nowB-cal)  * hash + nowC + cal;
				if(!set.contains(sum)) {
					set.add(sum);
					queue.offer(new int[] {nowA, nowB-cal, nowC + cal});
				}
			}
			
			if(nowC > 0) {
				int cal = Math.min(nowC, a - nowA);
				int sum = (nowA + cal) * (int)Math.pow(hash, 2) + (nowB * hash) + nowC - cal;
				if(!set.contains(sum)) {
					set.add(sum);
					queue.offer(new int[] {nowA + cal, nowB, nowC - cal});
				}
				cal = Math.min(nowC, b - nowB);
				sum = nowA * (int)Math.pow(hash, 2) + (nowB+cal)  * hash + nowC - cal;
				if(!set.contains(sum)) {
					set.add(sum);
					queue.offer(new int[] {nowA, nowB+cal, nowC - cal});
				}
			}
		}
		
		for(int i = 0; i < c + 1; i++) {
			if(vis[i]) sb.append(i).append(" ");
		}
		System.out.println(sb);
	}
}
