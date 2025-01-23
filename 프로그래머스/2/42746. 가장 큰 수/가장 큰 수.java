import java.util.*;

class Solution {
    
    static List<String> lst;
    
    public String solution(int[] numbers) {
        String answer = "";
        lst = new ArrayList<>();
        
        for(int number: numbers)
            lst.add(String.valueOf(number));
        
        Collections.sort(lst, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        
        for(String number: lst)
            answer += number;
        
        if(answer.charAt(0) == '0')
            answer = "0";
        
        return answer;
    }
}