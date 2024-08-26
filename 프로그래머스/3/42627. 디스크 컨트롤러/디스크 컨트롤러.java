import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int time = 0;
        int answer = 0;
        int jobCount = 0;
        int i = 0;
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        
        while (jobCount < jobs.length) {
            while (i < jobs.length && jobs[i][0] <= time) {
                pq.offer(jobs[i++]);
            }
            if (!pq.isEmpty()) {
                int[] curr = pq.poll();
                time += curr[1];
                answer += time - curr[0];
                jobCount++;
            } else if (i < jobs.length) {
                time = jobs[i][0];
            }
        }
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            time += curr[1];
            answer += time - curr[0];
        }
        
        answer /= jobs.length;
        return answer;
    }
}
