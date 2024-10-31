import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        Set<Integer> set = new HashSet<>();
        
        for(int num: nums) {
            set.add(num);
        }
        
        answer = set.size() <  (int) (nums.length / 2) ? set.size():(int) (nums.length / 2);
        return answer;
    }
}