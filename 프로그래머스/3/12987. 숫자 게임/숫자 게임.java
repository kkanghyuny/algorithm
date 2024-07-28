import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        int n = A.length;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int aidx = 0;
        int bidx = 0;
        
        while(aidx < n && bidx < n) {
            int currA = A[aidx];
            int currB = B[bidx];
            
            if(currA < currB) {
                answer++;
                aidx++;
                bidx++;
            } else {
                bidx++;
            }
        }
        
        return answer;
    }
}