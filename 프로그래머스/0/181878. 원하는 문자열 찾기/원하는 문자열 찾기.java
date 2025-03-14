class Solution {
    public int solution(String myString, String pat) {
        myString = myString.toLowerCase();
        pat = pat.toLowerCase();
        
        int answer = 0;
        if(myString.length() < pat.length()) {
            answer = 0;
        } else {
            for(int i = 0; i < myString.length() - pat.length() + 1; i++) {
                for(int j = 0; j < pat.length(); j++) {
                    if(myString.charAt(i + j) == pat.charAt(j)) 
                        answer = 1;
                    else {
                        answer = 0;
                        break;
                    }
                }
                
                if (answer == 1)
                    break;
            }
        }
        
        return answer;
    }
}