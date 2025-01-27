class Solution {
    
    static String answer;
    
    public String solution(String code) {
        answer = "";
        makeStr(code);
        if (answer.length() > 0)
            return answer;
        else
            return "EMPTY";
    }
    
    static void makeStr(String code) {
        int mode = 0;
        for(int i = 0; i < code.length(); i++) {
            if(mode == 0) {
                if(code.charAt(i) != '1' && i % 2 == 0)
                    answer += code.charAt(i);
                else if (code.charAt(i) == '1')
                    mode = 1;
            } else if (mode == 1) {
                if(code.charAt(i) != '1' && i % 2 != 0)
                    answer += code.charAt(i);
                else if (code.charAt(i) == '1')
                    mode = 0;
            }
        }
    }
}