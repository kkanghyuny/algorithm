import java.util.*;
import java.io.*;

class Solution {
    static class Course {
        String food;
        int orderCnt;
        
        public Course(String food) {
            this.food = food;
            this.orderCnt = 1;
        }
    }
    
    static int ordersLen, maxLen, idx;
    static Course[] list;
    static boolean[] vis;
    static HashSet<Integer> set;
    static HashMap<String, Integer> map;
    static HashMap<Integer, Integer> mapMax;
    
    public String[] solution(String[] orders, int[] course) {
        ordersLen = orders.length;
        idx = 0;
        set = new HashSet<>();
        map = new HashMap<>();
        mapMax = new HashMap<>();
        
        for(int i = 0; i < course.length; i++){
            set.add(course[i]);
        }
        
        int[][] dp = new int[11][11];
        for(int i = 0; i < 11; i++){
            dp[i][0] = 1;
            dp[i][i] = 1;
            dp[i][1] = i;
        }
        
        for(int i = 1; i < 11; i++){
            for(int j = 1; j < 11; j++){
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        
        // Course 갯수
        int n = 0;
        for(int i = 0; i < ordersLen; i++){            
            for(int j = 0; j < course.length; j++){
                n += dp[orders[i].length()][course[j]];
            }
        }
        
        list = new Course[n];
        
        for(int i = 0; i < ordersLen; i++){
            String s = orders[i];
            String[] arr = s.split("");
            Arrays.sort(arr);
            maxLen = arr.length;
            vis = new boolean[maxLen];
            for(int j = 0; j < maxLen; j++){
                vis[j] = true;
                perm(j + 1, 1, arr[j], arr);
                vis[j] = false;
            }
        }
        
        List<String> answerList = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(list[i] == null) break;
            if(list[i].orderCnt < 2) continue;
            if(list[i].orderCnt == mapMax.get(list[i].food.length())) answerList.add(list[i].food);
        }

        String[] answer = new String[answerList.size()];
        for(int i = 0; i < answerList.size(); i++){
            answer[i] = answerList.get(i);
        }
        Arrays.sort(answer);
        return answer;
    }
    
    static void perm(int index, int depth, String s, String[] arr){
        if(set.contains(depth)){
            if(map.containsKey(s)){
                list[map.get(s)].orderCnt++;
            } else {
                list[idx] = new Course(s);
                map.put(s, idx++);
            }
            
            if(mapMax.containsKey(depth)){
                mapMax.put(depth, Math.max(mapMax.get(depth), list[map.get(s)].orderCnt));
            } else {
                mapMax.put(depth, list[map.get(s)].orderCnt);
            }
        }
        
        if(depth == maxLen || index >= maxLen) return;
        
        for(int i = index; i < maxLen; i++){
            if(vis[i]) continue;
            vis[i] = true;
            perm(i + 1, depth + 1, s + arr[i], arr);
            vis[i] = false;
        }
    }
}