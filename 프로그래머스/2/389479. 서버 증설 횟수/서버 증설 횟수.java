import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0; // 증설 횟수
        Queue<Integer> server = new ArrayDeque<>(); // 반납 시간을 저장
        
        // 총 24시간 운영
        for(int i = 0; i < 24; i++) {
            int s = players[i] / m; // 필요한 서버 수
            
            // 이용시간 만료 서버 확인
            while(!server.isEmpty() && server.peek() <= i)
                // 반납 시간이 지난 경우
                server.poll(); // 반납
            
            // 필요 서버가 지금 서버 수보다 많은 경우
            if(s > server.size()) {
                int cnt = s - server.size();
                // 필요한만큼 서버 증설하기
                for(int j = 0; j < cnt; j++) {
                    answer++; // 증설 개수 증가
                    server.offer(i + k); // 지금 시간부터 k시간을 더해 서버 증설
                }
            }
        }
        
        return answer;
    }
}