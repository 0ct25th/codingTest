import java.util.*;

public class Solution {
    public Stack<Integer> solution(int []arr) {
        Stack<Integer> answer = new Stack<>();
        answer.push(arr[0]);
        
        for(int i = 1; i < arr.length; i++) {
            if (answer.peek() == arr[i])
                continue;
            else
                answer.push(arr[i]);
        }

        return answer;
    }
}