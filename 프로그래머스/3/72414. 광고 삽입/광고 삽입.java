import java.io.*;
import java.util.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "00:00:00";
        int playSecond = timeToSecond(play_time) + 1;
        int[] arr = new int[playSecond];
        System.out.println(playSecond);
        for(int i = 0; i < logs.length; i++) {
            StringTokenizer st = new StringTokenizer(logs[i], "-");
            int startSecond = timeToSecond(st.nextToken());
            int endSecond = timeToSecond(st.nextToken());
            arr[startSecond]++;
            arr[endSecond]--;
        }
    
        for(int i = 1; i < playSecond; i++) {
            arr[i] += arr[i - 1];
        }
        
        int left = 1;
        int right = timeToSecond(adv_time);
        long maxPlayTime = 0;
        for(int i = 1; i <= right; i++) {
            maxPlayTime += arr[i];
        }
        
        long playTime = maxPlayTime;
        while(right < playSecond - 1) {
            playTime -= arr[left++];
            playTime += arr[++right];
            
            if(maxPlayTime < playTime) {
                maxPlayTime = playTime;
                answer = secondToTime(left);
            }
        }
        
        return answer;
    }
    
    public int timeToSecond(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());
        return hour * 3600 + minute * 60 + second;
    }
    
    public String secondToTime(int sec) {
        String hour = String.valueOf(sec / 3600);
        sec %= 3600;
        String minute = String.valueOf(sec / 60);
        sec %= 60;
        String second = String.valueOf(sec);
        if(hour.length() == 1) hour = "0" + hour;
        if(minute.length() == 1) minute = "0" + minute;
        if(second.length() == 1) second = "0" + second;
        return hour + ":" + minute + ":" + second;
    }
}