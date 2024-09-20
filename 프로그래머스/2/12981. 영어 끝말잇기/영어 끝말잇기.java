import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[] {0, 0};
        int turn = 1;
        int idx = 0;
        String prev = null;
        Set<String> set = new HashSet<>();
        
        a: while(idx < words.length) {
            for(int i = 1; i <= n; i++) {
                String curr = words[idx++];
                if(prev != null && prev.charAt(prev.length() - 1) != curr.charAt(0)) {
                    answer[0] = i;
                    answer[1] = turn;
                    break a;
                }
                
                if(set.contains(curr) || curr.length() == 1) {
                    answer[0] = i;
                    answer[1] = turn;
                    break a;
                }
                
                set.add(curr);
                prev = curr;
                if(idx >= words.length) break a;
            }
            turn++;
        }

        return answer;
    }
}