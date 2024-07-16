class Solution {
    public int[] solution(long[] numbers) {
        int numLen = numbers.length;
        int[] answer = new int[numLen];
        
        for(int i = 0; i < numLen; i++){
            long curr = numbers[i];
            String s = "";
            while(curr > 0){
                String c = String.valueOf(curr % 2);
                curr /= 2;
                s = c + s;
            }
            
            int size = 1;
            while(size < s.length() + 1){
                size *= 2;
            }
            // 완전 이진 트리(?)의 노드 개수는 2^n - 1
            while(size > s.length() + 1){
                s = '0' + s;
            }
            
            if(check(s, 0, s.length() - 1, size / 4)){
                answer[i] = 1;
            }
        }
        
        return answer;
    }
    
    public boolean check(String s, int start, int end, int toChildNode){
        if (start > end) {
            return true;
        }

        int mid = (start + end) / 2;

        // 부모가 연결되지 않았는데?
        if (s.charAt(mid) == '0') {
            // 왼쪽 자식이 연결된 경우
            if (mid - toChildNode >= start && s.charAt(mid - toChildNode) == '1') {
                return false;
            }
            // 오른쪽 자식이 연결된 경우
            if (mid + toChildNode <= end && s.charAt(mid + toChildNode) == '1') {
                return false;
            }
        }

        return check(s, start, mid - 1, toChildNode / 2) && check(s, mid + 1, end, toChildNode / 2);
    }
}

// 1 1 1
// 0 1 0 1 0 1 0
// 0 0 0 1 0 0 0 1 0 0 0 1 0 0 0
// 루트는 문자열 길이 나누기 2부터 시작
// 자식은 루트 +- toChildNode