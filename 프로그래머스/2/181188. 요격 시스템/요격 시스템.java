import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 1;
        
        Arrays.sort(targets, (o1, o2) -> (o1[1] - o2[1]));
        
        int target = targets[0][1];
        for(int i = 1; i < targets.length; i++) {
            if(target > targets[i][0])
                continue;
            
            target = targets[i][1];
            answer++;
        }
        
        return answer;
    }
}