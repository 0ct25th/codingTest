import java.util.*;

class Solution {
    public List<Integer> solution(int[] progresses, int[] speeds) {
        Queue<Integer> dq = new ArrayDeque<>();
        List<Integer> answer = new ArrayList<>();
        
        // 배포가 필요한 날짜 queue에 담기
        for (int i = 0;  i < progresses.length; i++) {
            dq.offer((100 - progresses[i]) % speeds[i] == 0 ? (100 - progresses[i]) / speeds[i] : ((100 - progresses[i]) / speeds[i]) + 1);
        }
        
        int now = dq.poll();
        int count = 1;
        
        // dq가 비어져 있을 때까지 진행
        while(!dq.isEmpty()) {
            if (now >= dq.peek()) { // 현재 배포 날짜가 dq의 다음 기능의 배포 날짜보다 크면 추가
                count++;
                dq.poll();
            } else { // 현재 배포 날짜가 dq의 다음 기능 날짜보다 작으면
                answer.add(count); 
                count = 1; // 초기화
                now = dq.poll(); // 다음 배포 날짜
            }
        }
        
        answer.add(count); // 남은 count 담기
        
        return answer;
    }
}