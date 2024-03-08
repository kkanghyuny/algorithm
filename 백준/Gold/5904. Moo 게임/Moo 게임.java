import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<Integer> arr = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		arr.add(3);
		int wordLength = 3;
		int idx = 0;

		// 입력 받은 n보다 크게 moo 문자열 길이 생성(k 역할 하는 idx)
		while (true) {
			if (wordLength >= n)
				break;
			int midLength = 3 + ++idx;
			wordLength = wordLength * 2 + midLength;
			arr.add(wordLength);
		}

		System.out.println(moo(idx, n));
	}

	public static char moo(int idx, int n) {
		// idx 0일경우 1, 2, 3이 있는데 1만 m 나머지는 o
		if (idx == 0) {
			if (n == 1) {
				return 'm';
			}
			return 'o';
		}
		
		// moomooomoo 인 경우
		// 좌 moo
		// 중간 mooo
		// 우 moo

		// moo + k개의 o 이므로 길이에 3 추가
		int midLength = idx + 3;

		// idx - 1을 가져오면 moo mooo moo 인 경우에 왼쪽의 moo 길이만을 가져옴
		// 중간
		if (n > arr.get(idx - 1) && n < arr.get(idx - 1) + midLength) {
			if (arr.get(idx - 1) + 1 == n)
				return 'm';
			return 'o';
			
		}
		// 좌
		else if (n < arr.get(idx - 1)) {
			return moo(idx - 1, n);
		} 
		// 우
		else {
			return moo(idx - 1, n - arr.get(idx - 1) - midLength);
		}

	}
}
