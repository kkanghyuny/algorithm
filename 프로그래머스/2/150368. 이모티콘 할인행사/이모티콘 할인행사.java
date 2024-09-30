import java.io.*;
import java.util.*;

class Solution {
    
    static int n;
    static int[] answer, discount, price, emoticon;
    static int[][] user;
    
    public int[] solution(int[][] users, int[] emoticons) {
        n = emoticons.length;
        answer = new int[2];
        discount = new int[n];
        price = new int[n];
        emoticon = emoticons;
        user = users;
        
        backtracking(0);
        
        return answer;
    }
    
    static void backtracking(int depth) {
        if(depth == n) {
            for(int i = 0; i < n; i++) {
                price[i] = emoticon[i] * (100 - discount[i]) / 100;
            }

            int sumPrice = 0;
            int cnt = 0;
            for(int i = 0; i < user.length; i++) {
                int sum = 0;
                for(int j = 0; j < n; j++) {
                    if(discount[j] >= user[i][0]) sum += price[j];
                }
                if(sum >= user[i][1]) cnt++;
                else sumPrice += sum;
            }
            if(answer[0] < cnt) {
                answer[0] = cnt;
                answer[1] = sumPrice;
            } else if(answer[0] == cnt) {
                answer[1] = Math.max(sumPrice, answer[1]);
            }
            return;
        }
        
        for(int i = 10; i <= 40; i += 10) {
            discount[depth] = i;
            backtracking(depth + 1);
        }
    }
}