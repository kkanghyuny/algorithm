import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		int cntPos = 0;
		int cntNeg = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(arr[i] > 0) {
				cntPos++;
			} else {
				cntNeg++;
			}
		}
		
		if(cntPos == 0) {
			System.out.println(arr[n - 2] + " " + arr[n - 1]);
			return;
		} else if(cntNeg == 0) {
			System.out.println(arr[0] + " " + arr[1]);
			return;
		}
		
		int i = 0;
		int j = n - 1;
		int sum = 0;
		int min = Integer.MAX_VALUE;
		int answerX = 0;
		int answerY = 0;
		
		while(i < j) {
			if(min == 0) break;
			
			sum = arr[i] + arr[j];
			if(Math.abs(sum) < min) {
				min = Math.abs(sum);
				answerX = arr[i];
				answerY = arr[j];
			}
			
			if(sum > 0) {
				j--;
			} else {
				i++;
			}
		}
		
		System.out.println(answerX + " " + answerY);
	}
}
