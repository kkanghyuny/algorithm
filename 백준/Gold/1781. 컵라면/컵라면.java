import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

// int[] 는 2개를 가진 배열 {데드라인, 컵라면 수}
// 데드라인을 오름차순, 데드라인이 같다면 컵라면수가 많은 것이 큐의 우선순위
class CustomObject implements Comparable<CustomObject> {
	int[] arr;

	public CustomObject(int[] arr) {
		this.arr = arr;
	}

	@Override
	public int compareTo(CustomObject o) {
		// 0번 인덱스 기준으로 오름차순 정렬
		int result = Integer.compare(this.arr[0], o.arr[0]);

		// 0번이 같다면 1번 인덱스 기준으로 내림차순 정렬
		if (result == 0) {
			result = Integer.compare(o.arr[1], this.arr[1]);
		}
		return result;
	}
}

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		// 위에서 만들어준 기준으로 우선순위를 갖는 큐 생성
		PriorityQueue<CustomObject> pq = new PriorityQueue<>();
		// 라면 넣었던 값들을 낮은 값부터 빼줄 pq2
		PriorityQueue<Integer> pq2 = new PriorityQueue<>();

		// n은 숙제의 개수
		int n = sc.nextInt();
		// sum은 컵라면 수를 받을 변수, day는 날짜를 카운트해나갈 변수
		int sum = 0;
		int day = 0;

		// 입력 받고 큐에 int[] { 데드라인, 라면 수 } 로 저장
		// 우선순위에 의해 데드라인은 오름차순, 라면 수는 내림차순으로 저장
		for (int i = 1; i <= n; i++) {
			int deadLine = sc.nextInt();
			int ramen = sc.nextInt();

			pq.add(new CustomObject(new int[] { deadLine, ramen }));
		}

		// 큐가 빌 때까지
		while (!pq.isEmpty()) {
			// 받아오고 customObject.arr = {deadLine, ramen}으로 구성되어 있어서
			// customObject.arr[0] 은 데드라인, customObject.arr[1] 은 라면 수
			CustomObject customObject = pq.poll();

			// 현재 저장된 day보다 이후의 day가 나오면(오름차순이기 때문에 무조건 이후 날)
			if (day < customObject.arr[0]) {
				// day++ 해주고 (1일짜리가 없이 2일이 두개면 둘 다 들어갈 수 있음)
				day++;
				sum += customObject.arr[1];
				pq2.add(customObject.arr[1]); // sum에 추가한 값 pq2에 추가
				
				// 만약에 날짜가 같은 값이 더 있는데
			} else if (day == customObject.arr[0]) {
				// 현재 라면에 추가된 값 중 제일 작은 값보다 내가 현재 더 크네?
				if(pq2.peek() < customObject.arr[1]) {
					// 그럼 걔 빼주고 나 추가
					sum = sum - pq2.poll() + customObject.arr[1];
					// pq2에도 나 추가
					pq2.add(customObject.arr[1]);
				}
			}
		}
		System.out.println(sum);
	}
}
