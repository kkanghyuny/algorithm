import java.util.*;
import java.io.*;
import java.lang.*;

class Solution {
    public static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int userCnt = stages.length;
        int[] imos = new int[N + 3];
        int[] stopUserCnt = new int[N + 2];
        
        for(int i = 0; i < userCnt; i++) {
            int currStage = stages[i];
            imos[1]++;
            imos[currStage + 1]--;
            stopUserCnt[currStage]++;
        }
        
        Stage[] stageArr = new Stage[N + 1];
        stageArr[0] = new Stage(0);
        stageArr[0].failPer = -100;
        int reachCnt = 0;
        for(int i = 1; i <= N; i++) {
            reachCnt += imos[i];
            Stage nowStage = new Stage(i);
            if(stopUserCnt[i] == 0) {
                nowStage.failPer = 0;
            } else {
                nowStage.failPer = ((double)stopUserCnt[i]) / reachCnt;
            }
            stageArr[i] = nowStage;
        }
        Arrays.sort(stageArr);
        for(int i = 0; i < N; i++) {
            answer[i] = stageArr[i].stageNum;
        }
        
        return answer;
    }
    
    static class Stage implements Comparable<Stage> {
        int stageNum;
        double failPer;
        
        public Stage(int stageNum) {
            this.stageNum = stageNum;
        }

        public int compareTo(Stage s) {
            return Double.compare(s.failPer, this.failPer);
        }
    }
}