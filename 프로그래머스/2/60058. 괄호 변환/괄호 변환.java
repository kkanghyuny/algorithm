import java.io.*;
import java.util.*;

class Solution {
    public String solution(String p) {
        String answer = "";
        if(isCorrectString(p)) return p;
        
        answer = makeString(p);
        
        return answer;
    }
    
    public String makeString(String p) {
        if("".equals(p)) return "";
        String res = "";
        
        String u = "";
        String v = "";
        int k = 0;
        
        for(int i = 0; i < p.length(); i++) {
            if(p.charAt(i) == '(') {
                k++;
            } else {
                k--;
            }
            if(i != 0 && k == 0) {
                u = p.substring(0, i + 1);
                v = p.substring(i + 1, p.length());
                break;
            }
        }
        
        if(isCorrectString(u)) {
            res = u + makeString(v);
        } else {
            String sub = u.substring(1, u.length() - 1);
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            sb.append(makeString(v));
            sb.append(")");
            for(int i = 0; i < sub.length(); i++) {
                if(sub.charAt(i) == '(') {
                    sb.append(")");
                } else {
                    sb.append("(");
                }
            }
            res = sb.toString();
        }
        return res;
    }
    
    public boolean isCorrectString(String s) {
        boolean isCor = true;
        int k = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                k++;
            } else {
                k--;
            }
            
            if(k < 0) {
                isCor = false;
                break;
            }
        }
        return isCor;
    }
}