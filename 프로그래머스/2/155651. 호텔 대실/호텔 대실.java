import java.io.*;
import java.util.*;
import java.lang.*;

class Solution {    
    public int solution(String[][] book_time) {
        StringTokenizer st;
        // 24 * 60 = 1440 (23:59 까지이므로 1439가 최대 + 10분 청소 1449가 최대)
        int[] room = new int[1450];
        
        // 시작순으로 시작시간으로 나열
        for(int i = 0; i < book_time.length; i++){
            st = new StringTokenizer(book_time[i][0], ":");
            int start = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());
            st = new StringTokenizer(book_time[i][1], ":");
            int end = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken()) + 9;
            // 10분 이후부터 사용 가능하므로 end + 9
            
            for(int idx = start; idx <= end; idx++){
                room[idx]++;
            }
        }
        
        int answer = 0;
        for(int i = 0; i < 1440; i++){
            answer = Math.max(answer, room[i]);
        }
        
        return answer;
    }
}