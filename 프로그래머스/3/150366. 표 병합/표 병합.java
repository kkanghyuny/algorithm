import java.util.*;

class Solution {
    
    static int n;
    static int[] group;
    static String[] valueArr;
    
    public String[] solution(String[] commands) {
        List<String> list = new ArrayList<>();
        n = 50 + (50 * 49);
        group = new int[n + 1];
        valueArr = new String[n + 1];
        for(int i = 1; i <= n; i++) {
            group[i] = i;
            valueArr[i] = "";
        }
        
        for(int i = 0; i < commands.length; i++) {
            String[] cmd = commands[i].split(" ");
            if("UPDATE".equals(cmd[0]) && cmd.length == 4) {
                int r = Integer.parseInt(cmd[1]);
                int c = Integer.parseInt(cmd[2]);
                int idx = r + (50 * (c - 1));
                valueArr[find(idx)] = cmd[3];
            } else if("UPDATE".equals(cmd[0])) {
                for(int j = 1; j <= n; j++) {
                    if(cmd[1].equals(valueArr[find(j)])) {
                        valueArr[j] = cmd[2];
                    }
                }
            } else if("MERGE".equals(cmd[0])) {
                int r1 = Integer.parseInt(cmd[1]);
                int c1 = Integer.parseInt(cmd[2]);
                int r2 = Integer.parseInt(cmd[3]);
                int c2 = Integer.parseInt(cmd[4]);
                int idx1 = r1 + (50 * (c1 - 1));
                int idx2 = r2 + (50 * (c2 - 1));
                union(idx1, idx2);
            } else if("UNMERGE".equals(cmd[0])) {
                int r = Integer.parseInt(cmd[1]);
                int c = Integer.parseInt(cmd[2]);
                int idx = r + (50 * (c - 1));
                int findIdx = find(idx);
                String value = valueArr[findIdx];
                for(int j = n; j >= 0; j--) {
                    if(find(j) == findIdx) {
                        valueArr[j] = "";
                        group[j] = j;
                    }
                }
                valueArr[idx] = value;
            } else {
                int r = Integer.parseInt(cmd[1]);
                int c = Integer.parseInt(cmd[2]);
                int idx = r + (50 * (c - 1));
                String value = valueArr[find(idx)];
                list.add(value.equals("") ? "EMPTY" : value);
            }
        }
        
        String[] answer = new String[list.size()];
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
    
    public int find(int a) {
        if(group[a] == a) return a;
        return group[a] = find(group[a]);
    }
    
    public void union(int a, int b) {
        int findA = find(a);
        int findB = find(b);
        if(!valueArr[findA].equals("")) {
            valueArr[findB] = valueArr[findA];
        } else {
            valueArr[findA] = valueArr[findB];
        }
        
        if(findA > findB) {
            group[findA] = findB;
        } else {
            group[findB] = findA;
        }
    }
}