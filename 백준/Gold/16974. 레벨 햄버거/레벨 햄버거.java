import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
    static long[] pattyCnt, burgerLength;
    
    public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	long x = Long.parseLong(st.nextToken());
    	pattyCnt = new long[51];
    	burgerLength = new long[51];
    	
        pattyCnt[0] = 1;
        burgerLength[0] = 1;
        
        for (int i = 1; i <= 50; i++) {
            burgerLength[i] = 2 * burgerLength[i - 1] + 3;
            pattyCnt[i] = 2 * pattyCnt[i - 1] + 1;
        }
        
        System.out.println(countPatty(n, x));
    }
    
    static long countPatty(int n, long idx) {
        if (n == 0) return 1;

        long prev = burgerLength[n - 1];
        
        if (idx == 1) {
            return 0;
        } else if (idx <= prev + 1) {
            return countPatty(n - 1, idx - 1);
        } else if (idx == prev + 2) {
            return pattyCnt[n - 1] + 1;
        } else if (idx <= 2 * prev + 2) {
            return pattyCnt[n - 1] + 1 + countPatty(n - 1, idx - prev - 2);
        } else {
            return 2 * pattyCnt[n - 1] + 1;
        }
    }
    
}
