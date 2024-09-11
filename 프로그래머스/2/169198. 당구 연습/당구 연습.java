class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        
        for(int i = 0; i < balls.length; i++) {
            int[] curr = balls[i];
            int currMin = Integer.MAX_VALUE;

            if (!(curr[0] < startX && curr[1] == startY)) // 왼쪽 벽
                currMin = Math.min(currMin, (int)(Math.pow(startX + curr[0], 2) + Math.pow(startY - curr[1], 2)));
            if (!(curr[1] < startY && curr[0] == startX)) // 아래쪽 벽
                currMin = Math.min(currMin, (int)(Math.pow(startX - curr[0], 2) + Math.pow(startY + curr[1], 2)));
            if (!(curr[0] > startX && curr[1] == startY)) // 오른쪽 벽
                currMin = Math.min(currMin, (int)(Math.pow(m - startX + (m - curr[0]), 2) + Math.pow(startY - curr[1], 2)));
            if (!(curr[1] > startY && curr[0] == startX)) // 위쪽 벽
                currMin = Math.min(currMin, (int)(Math.pow(startX - curr[0], 2) + Math.pow(n - startY + (n - curr[1]), 2)));
            if (!(curr[0] <= startX && curr[1] <= startY)) // 왼쪽 위 모서리
                currMin = Math.min(currMin, (int)(Math.pow(startX + curr[0], 2) + Math.pow(startY + curr[1], 2)));
            if (!(curr[0] >= startX && curr[1] <= startY)) // 오른쪽 위 모서리
                currMin = Math.min(currMin, (int)(Math.pow((m - startX) + (m - curr[0]), 2) + Math.pow(startY + curr[1], 2)));
            if (!(curr[0] <= startX && curr[1] >= startY)) // 왼쪽 아래 모서리
                currMin = Math.min(currMin, (int)(Math.pow(startX + curr[0], 2) + Math.pow((n - startY) + (n - curr[1]), 2)));
            if (!(curr[0] >= startX && curr[1] >= startY)) // 오른쪽 아래 모서리
                currMin = Math.min(currMin, (int)(Math.pow((m - startX) + (m - curr[0]), 2) + Math.pow((n - startY) + (n - curr[1]), 2)));
            answer[i] = currMin;
        }
        return answer;
    }
}