import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int answerL = 0;
        int answerR = 0;
        int answerM = 0;
        long answer = Long.MAX_VALUE;
        
        int[] arr = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);
        
        for(int i = 0; i < n - 2; i++) {
        	for(int j = i + 2; j < n; j++) {
        		int left = i + 1;
        		int right = j - 1;
        		long sumA = arr[i] + arr[j];
        		
        		while(left <= right) {
        			int mid = (left + right) / 2;
        			long sum = sumA + arr[mid];
        			
        			if(Math.abs(sum) < answer) {
        				answer = Math.abs(sum);
        				answerL = arr[i];
        				answerR = arr[j];
        				answerM = arr[mid];
        			}
        			
        			if(left == right) break;
        			
        			if(sum > 0) {
        				right = mid;
        			} else {
        				left = mid + 1;
        			}
        		}
        	}
        }
        
        System.out.println(answerL + " " + answerM + " " + answerR);
    }
}