import java.io.*;
import java.util.*;

class Solution {
    static int n, m, answer;
    static Set<String> set;
    static Set<String> answerSet;
    static boolean[] vis;
    static String[] userId, bannedId;
    public int solution(String[] user_id, String[] banned_id) {
        answer = 0;
        n = banned_id.length;
        m = user_id.length;
        userId = user_id;
        bannedId = banned_id;
        set = new HashSet<>();
        answerSet = new HashSet<>();
        vis = new boolean[m];
        backtracking(0);
        return answer;
    }
    
    static void backtracking(int depth) {
        if(depth == n) {
            String s = "";
            for(int i = 0; i < m; i++) {
                if(vis[i]) s += i;
            }
            if(!answerSet.contains(s)) {
                System.out.println(s);
                answerSet.add(s);
                answer++;
            }
            return;
        }
    
        String compare = bannedId[depth];
        for(int i = 0; i < m; i++) {
            if(vis[i]) continue;
            String curr = userId[i];
            if(set.contains(curr)) continue;
            if(curr.length() != compare.length()) continue;
            for(int j = 0; j < curr.length(); j++) {
                char c = curr.charAt(j);
                char comC = compare.charAt(j);
                if(comC != '*' && c != comC) {
                    break;
                }
                if(j == curr.length() - 1) {
                    vis[i] = true;
                    backtracking(depth + 1);
                    vis[i] = false;
                }
            }
        }
    }
}