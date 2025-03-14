class Solution {
    public String solution(String my_string, String alp) {
        String answer = "";
        
        for(int i = 0; i < my_string.length(); i++) {
            char cur = my_string.charAt(i);
            if(my_string.charAt(i) == alp.charAt(0))
                answer += Character.toUpperCase(cur);
            else
                answer += cur;
        }
        return answer;
    }
}