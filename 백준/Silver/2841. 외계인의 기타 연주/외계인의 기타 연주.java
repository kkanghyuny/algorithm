import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 한줄 읽어오고
		StringTokenizer st = new StringTokenizer(br.readLine());
		int count = 0;

		// 읽어온 것 중 앞에꺼 n 뒤에꺼 p
		int n = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());

		// 스택 배열 사이즈는 줄의 갯수 6
		Stack<Integer>[] stack = new Stack[6];

		// 각 스택 배열 new Stack으로 선언해주고
		for (int i = 0; i < 6; i++) {
			stack[i] = new Stack<>();
		}

		for (int t = 0; t < n; t++) {
			// 한 줄 읽고 line은 Stack 배열이니까 인덱스값으로 해주기 위해 -1 ,fret은 그냥 받고
			StringTokenizer now = new StringTokenizer(br.readLine());
			int line = Integer.parseInt(now.nextToken()) - 1;
			int fret = Integer.parseInt(now.nextToken());

			// peek 했을때 비어있다면 EmptyStackException이 뜨기 때문에 try catch
			try {
				// fret 보다 큰 값들을 빼내며 count++
				while (stack[line].peek() > fret) {
					stack[line].pop();
					count++;
				}
				
				// fret보다 큰 애들은 다 뺀 이후에 맨 윗 값이 나보다 작다면 추가하고 count++
				// 맨 윗 값이 나와 같다면 continue
				if (stack[line].peek() < fret) {
					stack[line].push(fret);
					count++;
				} else if (stack[line].peek() == fret) {
					continue;
				}
			} catch (EmptyStackException e) {
				stack[line].push(fret);
				count++;
			}
		}
		
		System.out.println(count);
	}
}
