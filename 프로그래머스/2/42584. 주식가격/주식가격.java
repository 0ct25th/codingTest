import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer =  new int[prices.length];
        Queue<Integer> dq = new ArrayDeque<>();
        int index = 0;
        
        for(int price: prices)
            dq.offer(price);
        
        while(!dq.isEmpty()) {
            int now_price = dq.poll();
            
            for(int i = prices.length - dq.size(); i < prices.length; i++) {
                // 가격이 떨어진 경우
                if (now_price > prices[i]) {
                    answer[index]++;
                    break;
                }
                
                // 가격이 떨어지지 않은 경우
                else
                    answer[index]++;
            }
            
            index++;
        }
        
        return answer;
    }
}