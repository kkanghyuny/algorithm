import java.util.Scanner;
import java.util.Stack;
 
public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();	// 출력할 결과물 저장
		
		Stack<Integer> stack = new Stack<>();
		
		int n = sc.nextInt();
		
		int start = 0;
		
		// n 번 반복
		for(int i = 0; i < n; i++) {
			
			int value = sc.nextInt();
			
			if(value > start) {
				// start + 1부터 입력받은 value 까지 push
				for(int j = start + 1; j <= value; j++) {
					stack.push(j);
					sb.append('+').append('\n');	// + 를 저장 
				}
				start = value; 	// 다음 push 할 때의 다음 수를 넣기 위해 start 변경 
			}
			
			// top에 있는 원소가 입력받은 값과 같이 않은 경우  
			else if(stack.peek() != value) {
				System.out.println("NO");
				return;
			}
			
			stack.pop();
			sb.append('-').append('\n');
			
		}
		
		System.out.println(sb);
	}
}