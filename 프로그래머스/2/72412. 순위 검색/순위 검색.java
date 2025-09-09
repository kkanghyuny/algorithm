import java.io.*;
import java.util.*;

class Solution {
    static Map<String, Integer> map;
    
    public int[] solution(String[] info, String[] query) {
        StringTokenizer st;
        makeMap();
        
        int n = info.length;
        int t = query.length;
        int[] answer = new int[t];
        int[][][][][] arr = new int[4][3][3][3][100_001];
        
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(info[i]);
            String lan = st.nextToken();
            String occu = st.nextToken();
            String his = st.nextToken();
            String food = st.nextToken();
            int score = Integer.parseInt(st.nextToken());
            int lanIdx = map.get(lan);
            int occuIdx = map.get(occu);
            int hisIdx = map.get(his);
            int foodIdx = map.get(food);

            arr[lanIdx][occuIdx][hisIdx][foodIdx][score]++;
            arr[0][occuIdx][hisIdx][foodIdx][score]++;
            arr[lanIdx][0][hisIdx][foodIdx][score]++;
            arr[lanIdx][occuIdx][0][foodIdx][score]++;
            arr[lanIdx][occuIdx][hisIdx][0][score]++;
            arr[0][0][hisIdx][foodIdx][score]++;
            arr[0][occuIdx][0][foodIdx][score]++;
            arr[0][occuIdx][hisIdx][0][score]++;
            arr[lanIdx][0][0][foodIdx][score]++;
            arr[lanIdx][0][hisIdx][0][score]++;
            arr[lanIdx][occuIdx][0][0][score]++;
            arr[0][0][0][foodIdx][score]++;
            arr[0][0][hisIdx][0][score]++;
            arr[0][occuIdx][0][0][score]++;
            arr[lanIdx][0][0][0][score]++;
            arr[0][0][0][0][score]++;
        }
        
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 3; j++) {
                for(int k = 0; k < 3; k++) {
                    for(int q = 0; q < 3; q++) {
                        for(int sc = 99_999; sc >= 0; sc--) {
                            arr[i][j][k][q][sc] += arr[i][j][k][q][sc + 1];
                        }
                    }
                }
            }
        }
        
        for(int i = 0; i < t; i++) {
            String[] parts = query[i].split(" and ");
            String[] lastPart = parts[3].split(" ");
            int score = Integer.parseInt(lastPart[1]);
            answer[i] = arr[map.get(parts[0])][map.get(parts[1])][map.get(parts[2])][map.get(lastPart[0])][score];
        }
        
        return answer;
    }
    
    void makeMap() {
        map = new HashMap<>();
        map.put("-", 0);
        map.put("cpp", 1);
        map.put("java", 2);
        map.put("python", 3);
        map.put("backend", 1);
        map.put("frontend", 2);
        map.put("junior", 1);
        map.put("senior", 2);
        map.put("chicken", 1);
        map.put("pizza", 2);
    }
}