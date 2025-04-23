import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringBuilder ansSb = new StringBuilder();

		for(int i = 0; i < n; i++) {
			sb.append(br.readLine());
		}

		String s = sb.toString();
		int left = 0;
		int right = n - 1;
		int count = 0;

		while(left <= right) {
			if(s.charAt(left) < s.charAt(right)) { // left가 right보다 사전순으로 빠른 경우
				ansSb.append(s.charAt(left));
				left++;
			} else if(s.charAt(left) > s.charAt(right)){ // right가 left보다 사전순으로 빠른 경우
				ansSb.append(s.charAt(right));
				right--;
			} else { // 둘이 현재 같다면?
				int nleft = left + 1;
				int nright = right - 1;
				while(nleft < nright && s.charAt(nleft) == s.charAt(nright)) { // 같은 것들은 스킵 (ex. AAABAAA) 면 B까지
					nleft++;
					nright--;
				}
				if(nleft > nright || nleft == nright) { // nleft가 넘은 경우는 짝수에서 끝까지 같음, 둘이 같다면 홀수에서 끝까지 같음
					ansSb.append(s.charAt(left));
					left++;
				} else if(s.charAt(nleft) < s.charAt(nright)) { // 끝까지 가지 못했는데 nleft가 사전순으로 빠른 경우
					ansSb.append(s.charAt(left));
					left++;
				} else { // 끝까지 가지 못했는데 nright가 사전순으로 빠른 경우
					ansSb.append(s.charAt(right));
					right--;
				}
			}
			if(++count % 80 == 0) ansSb.append('\n');
		}
		System.out.println(ansSb.toString());
	}
}
