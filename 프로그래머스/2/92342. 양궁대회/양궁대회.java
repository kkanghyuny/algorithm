import java.util.*;
import java.io.*;

class Solution {
    // 가장 많이 차이 나는 점수를 저장해둘 maxScore
    static int maxScore;
    // 바꿔먹을 배열 perm, 정답을 저장해둘 배열 answer
    static int[] perm, answer;
    
    public int[] solution(int n, int[] info) {
        // 값들 초기화
        maxScore = 0;
        answer = new int[] {-1};
        perm = new int[11];
        
        perm(n, 0, 0, info);
        
        return answer;
    }
    
    // n은 n, depth는 사용한 화살 수, idx는 perm을 조정하기 위한 시작 index, info는 info
    static void perm(int n, int depth, int idx, int[] info){
        // 화살을 다 썼다면
        if(depth == n){
            int score = 0;
            
            // 점수를 계산해보고
            for(int i = 0; i < 11; i++){
                if(perm[i] == 0 && info[i] == 0){
                    continue;
                } else if(perm[i] > info[i]){
                    score += (10 - i);
                } else {
                    score -= (10 - i);
                }
            }
            
            // 여태 저장한 maxScore보다 높다면(한판도 못이겼다면 돌아가지 않을 부분)
            if(score > maxScore){
                maxScore = score;
                // answer 바꾸기
                answer = new int[11];
                for(int i = 0; i < 11; i++){
                    answer[i] = perm[i];
                }
            } else if(score == maxScore && maxScore != 0){
                // 비기는 경우 고려 안해줘서 테케 하나 틀렸음...
                // 점수가 동일하다면 가장 낮은 점수를 더 많이 맞힌 경우를 answer 배열에 담아야 함
                for(int i = 10; i >= 0; i--){
                    if(answer[i] < perm[i]){
                        answer = new int[11];
                        for(int j = 0; j < 11; j++){
                            answer[j] = perm[j];
                        }
                        break;
                    } else if(answer[i] > perm[i]){
                        break;
                    }
                }
            }
            return;
        }
        
        // perm[i]++로 하면 시간초과, Math.min을 사용안하고 info[i] + 1을 하면 depth가 넘어가버림(수정하면 가능은 할듯)
        // info[i] + 1은 내가 어피치를 이기는 경우, n - depth는 남은 화살 수, 중에 작은 값 만큼 쏠 수 있음
        for(int i = idx; i < 11; i++){
            int k = Math.min(info[i] + 1, n - depth);
            perm[i] += k;
            perm(n, depth + k, i + 1, info);
            perm[i] -= k;
        }
    }
}