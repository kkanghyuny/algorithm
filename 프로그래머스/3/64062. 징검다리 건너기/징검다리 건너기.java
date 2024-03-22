import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int answer = Integer.MAX_VALUE;
        Deque<Integer> deque = new ArrayDeque<>();
        
        for(int i = 0; i < stones.length; i++){
            while(!deque.isEmpty() && stones[deque.peekLast()] < stones[i]){
                deque.pollLast();
            }
            
            while(!deque.isEmpty() && i - deque.peekFirst() >= k){
                deque.pollFirst();
            }
            
            deque.addLast(i);
            
            if(i >= k - 1) {
                answer = Math.min(answer, stones[deque.peekFirst()]);
            }
        }

        if(answer == Integer.MAX_VALUE) return 0;
        return answer;
    }
}