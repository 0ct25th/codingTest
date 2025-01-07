import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 1;
        int index = 0;
        int now_weight = 0;
        Queue<Integer> bridge = new ArrayDeque<>();
        
        for(int i = 0; i < bridge_length - 1; i++)
            bridge.offer(0);    
        
        while(index < truck_weights.length) {
            // 최대 무게 이하
            if(now_weight + truck_weights[index] <= weight) {
                bridge.offer(truck_weights[index]);
                now_weight += truck_weights[index];
                index++;
            } else 
                bridge.offer(0); // 앞으로 나아가기
            
            answer++; // 초 증가
            now_weight -= bridge.poll(); // 무게 조정
        }
        
        answer += bridge.size();
        
        return answer;
    }
}