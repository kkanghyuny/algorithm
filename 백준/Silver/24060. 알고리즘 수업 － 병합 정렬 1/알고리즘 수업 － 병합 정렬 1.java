import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	int[] A;
	static int[] tmp;
	static int k, cnt, result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		cnt = 0;
		result = -1;
		
		int n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		int[] A = new int[n];
		tmp = new int[n];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		merge_sort(A, 0, n-1);
		System.out.println(result);
		
	}
	
	static void merge_sort(int A[], int p, int r) {
		if(cnt > k) return;
		
		if(p < r) {
			int q = (p+r) / 2;
			
			// 앞쪽
			merge_sort(A, p, q);
			
			// 뒤쪽
			merge_sort(A, q + 1, r);
			
			// 합치기
			merge(A, p, q, r);
		}
	}
	
	static void merge(int A[], int p, int q, int r) {
		int i = p;
		int j = q + 1;
		int t = 0;
		
		// 쇽쇽 해오고
		while(i <= q && j <= r) {
			if(A[i] < A[j]) {
				tmp[t++] = A[i++];
			} else {
				tmp[t++] = A[j++];
			}
		}
		
		// 왼쪽이 남았다면
		while(i <= q) {
			tmp[t] = A[i];
			t++;
			i++;
		}
		
		// 오른쪽이 남았다면
		while(j <= r) {
			tmp[t++] = A[j++];
		}
		
		i = p;
		t = 0;
		
		// 정렬 결과 A에 저장
		while(i <= r) {
			cnt++;
			
			if(cnt == k) {
				result = tmp[t];
				break;
			}
			
			A[i++] = tmp[t++];
		}
	}
}
