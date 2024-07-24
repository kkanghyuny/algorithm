import java.util.*;
import java.io.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < skill.length(); i++) {
            map.put(skill.charAt(i), i);
        }
        
        int answer = 0;
        a: for(int i = 0; i < skill_trees.length; i++) {
            int idx = 0;
            String s = skill_trees[i];
            for(int j = 0; j < s.length(); j++) {
                if(!map.containsKey(s.charAt(j))) continue;
                if(map.get(s.charAt(j)) != idx) continue a;
                idx++;
            }
            answer++;
        }
        
        return answer;
    }
}