import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        int n = gems.length;
        Set<String> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            set.add(gems[i]);
        }
        int gemCnt = set.size();
        if(gemCnt == 1) {
            answer[0] = 1;
            answer[1] = 1;
            return answer;
        }
        
        Map<String, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int minSize = 987654321;
        map.put(gems[left], 1);
        System.out.println(gemCnt);
        
        while(right < n) {
            if(map.size() < gemCnt) {
                if(++right == n) break;
                int cnt = map.getOrDefault(gems[right], 0);
                map.put(gems[right], cnt + 1);
            } else {
                int cnt = map.get(gems[left]);
                if(cnt == 1) {
                    map.remove(gems[left]);
                } else {
                    map.put(gems[left], cnt - 1);
                }
                left++;
            }
            
            if(map.size() == gemCnt && minSize > (right - left)) {
                minSize = right - left;
                answer[0] = left + 1;
                answer[1] = right + 1;
            }
        }
        return answer;
    }
}