import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        Queue<Integer> pq = new PriorityQueue<>((o1, o2) -> -(o1 - o2));
        for(int i: works) 
            pq.offer(i);
        
        
        while(!pq.isEmpty() && n > 0) {
            int cur = pq.poll();
            
            cur--;
            n--;
            
            if(cur == 0)
                continue;
            
            pq.offer(cur);
        }
        
        while(!pq.isEmpty()) {
            int cur = pq.poll();
            answer += Math.pow(cur, 2);
        }
        
        return answer;
    }
}