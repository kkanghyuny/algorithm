class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int endIdx = n - 1;
        
        for(int i = endIdx; i >= 0; i--){
            if(deliveries[i] != 0 || pickups[i] != 0){
                endIdx = i;
                break;
            } else if(i == 0){
                endIdx = i;
            }
        }
        
        while(true){
            // if(deliveries[0] == 0 && pickups[0] == 0) break;
            if(endIdx == 0 && deliveries[0] == 0 && pickups[0] == 0) break;
            
            int newIdx = 0;
            int deliv = 0;
            int pickup = 0;
            
            for(int i = endIdx; i >= 0; i--){
                if(deliv + deliveries[i] <= cap){
                    deliv += deliveries[i];
                    deliveries[i] = 0;
                } else {
                    deliveries[i] -= (cap - deliv);
                    newIdx = Math.max(newIdx, i);
                    break;
                }
            }
            
            for(int i = endIdx; i >= 0; i--){
                if(pickup + pickups[i] <= cap){
                    pickup += pickups[i];
                    pickups[i] = 0;
                } else {
                    pickups[i] -= (cap - pickup);
                    newIdx = Math.max(newIdx, i);
                    break;
                }
            }
            
            answer += endIdx + 1;
            
            endIdx = newIdx;
            
        }
        
        return answer * 2;
    }
}