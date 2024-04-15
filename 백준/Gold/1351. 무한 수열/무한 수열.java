import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static long n, p, q;
	static Map<Long, Long> map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		map = new HashMap<>();
		
		n = Long.parseLong(st.nextToken());
		p = Long.parseLong(st.nextToken());
		q = Long.parseLong(st.nextToken());
		
		map.put(0L,  1L);
		
		System.out.println(dp(n));
	}
	
	static long dp(long val) {
		if(map.containsKey(val)) return map.get(val);
		
		long a = (long)Math.floor(val / p);
		long b = (long)Math.floor(val / q);
		
		map.put(val, dp(a) + dp(b));
		return map.get(val);
	}
}
