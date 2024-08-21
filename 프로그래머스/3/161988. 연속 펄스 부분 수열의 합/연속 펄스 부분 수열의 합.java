import java.util.*;
import java.io.*;

class Solution {
    public long solution(int[] sequence) {
        int n = sequence.length;
        long answer = 0;
        long[] arr1 = new long[n];
        long[] arr2 = new long[n];
        long prev1 = 0L;
        long prev2 = 0L;
        long max1 = 0L;
        long max2 = 0L;
        long min1 = 0L;
        long min2 = 0L;
        int max1Idx = 0;
        int max2Idx = 0;
        
        int a = 1;
        for(int i = 0; i < n; i++) {
            arr1[i] = a * sequence[i] + prev1;
            if(arr1[i] > max1) {
                max1 = arr1[i];
                max1Idx = i;
            }
            prev1 = arr1[i];
            a = -a;
            arr2[i] = a * sequence[i] + prev2;
            if(arr2[i] > max2) {
                max2 = arr2[i];
                max2Idx = i;
            }
            prev2 = arr2[i];
        }
        
        for(int i = 0; i < max1Idx; i++) {
            min1 = Math.min(min1, arr1[i]);
        }
        
        for(int i = 0; i < max2Idx; i++) {
            min2 = Math.min(min2, arr2[i]);
        }
        
        answer = Math.max(max1 - min1, max2 - min2);
        
        return answer;
    }
}