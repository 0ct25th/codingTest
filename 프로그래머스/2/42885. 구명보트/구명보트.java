import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int start = 0;
        int end = people.length - 1;
        
        Arrays.sort(people);
        
        while(start <= end) {
            // 가장 가벼운 사람과 무거운 사람을 같이 태울 수 있는 경우
            if(people[start] + people[end] <= limit) 
                start++;
            end--;
            answer++;
        }
        
        return answer;
    }
}