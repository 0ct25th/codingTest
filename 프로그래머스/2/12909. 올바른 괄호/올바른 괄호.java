import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Stack<String> stk = new Stack<>();

        for(int i = 0; i < s.length(); i++) {
            // 열린 괄호인 경우
            if(s.charAt(i) == '(') {
                // stk에 삽입
                stk.add("(");
            } 
            // 닫힌 괄호인 경우
            else {
                // 열린 괄호가 stk에 없는 경우
                if (stk.empty())
                    return false; // 올바르지 않은 괄호
                
                // stk에서 제거
                stk.pop();
            }
        }
        
        // stk에 괄호가 남아있는 경우
        if(!stk.empty()) 
            return false; // 올바르지 않은 괄호

        return answer;
    }
}