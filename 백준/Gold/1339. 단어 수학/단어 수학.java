import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		HashMap<Character, Integer> map = new HashMap<>();
		HashMap<Character, Integer> map2 = new HashMap<>();
		Queue<String> queue = new ArrayDeque<>();
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();

		for(int i = 0; i < n; i++) {
			String s = sc.next();
			queue.offer(s);
			int jegop = s.length() - 1;
			
			for(int j = 0; j < s.length(); j++) {
				// 이미 있다면 원래 값에 10의 n승 해서 점수 더하기
				if(map.containsKey(s.charAt(j))) {
					map.put(s.charAt(j), map.get(s.charAt(j)) + (int)(Math.pow(10, jegop--)));
				} 
				// 값이 없었다면 10의 n승으로 새로 만들어주기
				else {
					map.put(s.charAt(j), (int)Math.pow(10, jegop--));
				}
			}
		}
		
		// map에 있던 걸 value로 높은 값부터 나오게정렬하기
		List<Character> keySet = new ArrayList<>(map.keySet());
		keySet.sort(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return map.get(o2).compareTo(map.get(o1));
            }
        });
		
		// 정렬한 키셋으로 높은 우선순위부터 9부터 내려가게 map2에 저장
		int num = 9;
		for(int i = 0; i < keySet.size(); i++) {
			map2.put(keySet.get(i), num--);
		}
		
		int answer = 0;
		
		// 계산
		while(!queue.isEmpty()) {
			String poll = queue.poll();
			String s = "";
			
			for(int i = 0; i < poll.length(); i++) {
				s += map2.get(poll.charAt(i));
			}
			
			answer += Integer.parseInt(s);
		}
		System.out.println(answer);
	}
}
