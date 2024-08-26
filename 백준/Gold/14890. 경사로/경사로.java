import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());

		int[][] arr = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int count = 0;
		// 내리막을 설치하자마자는 오르막을 설치할 수 없고
		// 오르막을 설치하자마자는 내리막을 설치할 수 있음
		boolean desc = false;
		for(int i = 0; i < n; i++) {
			desc = false;
			a: for(int j = 0; j < n; ) {
				int nowHeight = arr[i][j];

				// 직전에 내리막을 설치하지 않았을때 오르막 설치가 가능한 경우
				for(int k = j + 1; k <= j + l && k < n && !desc; ) {
					if(k != j + l && arr[i][k] == nowHeight) {
						k++;
						continue;
					} else if(k == j + l && arr[i][k] == nowHeight + 1) {
						j += l;
						continue a;
					}
					break;
				}

				// 내리막 설치가 가능한 경우
				for(int k = j + 1; k <= j + l && k < n; ) {
					if(k != j + l && arr[i][k] == nowHeight - 1) {
						k++;
						continue;
					} else if(k == j + l && arr[i][k] == nowHeight - 1) {
						j += l;
						desc = true;
						continue a;
					}
					break;
				}

				// 끝까지 도달했다면 count++
				if(j == n - 1) {
					count++;
					break;
				}
				// 오르막X, 내리막X이지만 한칸 전진이 가능할 때
				else if(j < n - 1 && arr[i][j + 1] == nowHeight) {
					j++;
					desc = false;
				}
				// 다 안된다면 가능하지 않은 경우임
				else {
					break;
				}
			}
		}

		// 위와 동일
		for(int j = 0; j < n; j++) {
			desc = false;
			b: for(int i = 0; i < n; ) {
				int nowHeight = arr[i][j];

				for(int k = i + 1; k <= i + l && k < n && !desc; ) {
					if(k != i + l && arr[k][j] == nowHeight) {
						k++;
						continue;
					} else if(k == i + l && arr[k][j] == nowHeight + 1) {
						i += l;
						continue b;
					}
					break;
				}

				for(int k = i + 1; k <= i + l && k < n; ) {
					if(k != i + l && arr[k][j] == nowHeight - 1) {
						k++;
						continue;
					} else if(k == i + l && arr[k][j] == nowHeight - 1) {
						i += l;
						desc = true;
						continue b;
					}
					break;
				}

				if(i == n - 1) {
					count++;
					break;
				} else if(i < n - 1 && arr[i + 1][j] == nowHeight) {
					i++;
					desc = false;
				} else {
					break;
				}
			}
		}

		System.out.println(count);
	}
}
