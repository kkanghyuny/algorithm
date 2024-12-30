class Solution {
    static int n, answer;
    static int[] weak, dist, madeWeak, madeDist;
    static boolean[] vis;
    public int solution(int n, int[] weak, int[] dist) {
        answer = 987654321;
        this.n = n;
        this.weak = weak;
        this.dist = dist;
        madeWeak = new int[weak.length];
        madeDist = new int[dist.length];
        vis = new boolean[dist.length];
        makeWeak();
        if(answer == 987654321) answer = -1;
        return answer;
    }
    
    static void makeWeak() {
        for(int i = 0; i < madeWeak.length; i++) {
            madeWeak[0] = weak[i];
            for(int j = i + 1; j < i + madeWeak.length; j++) {
                if(j >= madeWeak.length) {
                    madeWeak[j - i] = n + weak[j - madeWeak.length]; 
                } else {
                    madeWeak[j - i] = weak[j];
                }
                
                if(j == i + madeWeak.length - 1) makeDist(0);
            }
        }
    }
    
    static void makeDist(int depth) {
        if(depth == dist.length) {
            check();
            return; 
        }
        
        for(int i = 0; i < madeDist.length; i++) {
            if(vis[i]) continue;
            vis[i] = true;
            madeDist[depth] = dist[i];
            makeDist(depth + 1);
            madeDist[depth] = 0;
            vis[i] = false;
        }
    }
    
    static void check() {
        int curr = 0;
        int idx = 0;
        int next = 1;
        
        while(curr < madeWeak.length && idx < madeDist.length) {
            while(next < madeWeak.length && madeWeak[curr] + madeDist[idx] >= madeWeak[next]) next++;
            curr = next++;
            idx++;
        }
        
        if(curr == madeWeak.length && idx < answer) answer = idx;
    }
}