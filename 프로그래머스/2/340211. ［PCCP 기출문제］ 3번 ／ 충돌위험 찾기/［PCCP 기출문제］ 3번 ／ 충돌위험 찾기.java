import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0; // 모든 로봇이 운송을 마칠 때까지 발생하는 위험한 상황의 횟수
        int LastStep = routes[0].length; // 로봇의 운송 경로 갯수(모두 같음)
        List<Robot> robots = new ArrayList<>(); // 운송 시스템
        Map<String, Integer> crashChk = new HashMap<>(); // 충돌 체크 맵
        
        // 첫 번째 포인트
        for(int[] route: routes) {
            int pointNum = route[0] - 1;
            int r = points[pointNum][0];
            int c = points[pointNum][1];
            
            robots.add(new Robot(r, c, 1));
            crashChk.merge(route[0] + "", 1, Integer::sum);
        }
        
        // 첫번째 포인트에서 충돌이 발생한 경우
        for(int value: crashChk.values())
            if(value > 1)
                answer++; // 횟수 증가
        
        // 모든 로봇 운송 마칠 때까지 반복
        while(!robots.isEmpty()) {
            crashChk = new HashMap<>(); // 충돌 체크 맵
            boolean[] isArrived = new boolean[robots.size()]; // 도착 체크 배열
            
            for(int i = 0; i < robots.size(); i++) {
                Robot cur = robots.get(i); // 현재 로봇
                
                // 현재 로봇이 마지막 운송지에 도착한 경우
                if(cur.next == LastStep) {
                    isArrived[i] = true; // 도착 체크
                    continue; // 넘기기
                }
                
                // 다음 경유지 좌표값 찾기
                int pointIdx = routes[i][cur.next] - 1;
                int targetR = points[pointIdx][0];
                int targetC = points[pointIdx][1];
                
                // 다음 경유지로 이동
                cur.moveToTarget(targetR, targetC);
                
                // 이동한 좌표 충돌 체크
                crashChk.merge(cur.r + " " + cur.c, 1, Integer::sum);
                
                // 이동한 좌표가 목표 좌표인 경우
                if(cur.hasArrived(targetR, targetC))
                    cur.moveToNext(); // 다음 경유지로 이동
            }
            
            // 충돌 횟수 확인
            for(int value: crashChk.values())
                if(value > 1)
                    answer++;
            
            // 모든 로봇 운송 마쳤는지 확인
            boolean isAllArrived = true;
            for(boolean chk: isArrived) {
                if(!chk) {
                    isAllArrived = false;
                    break;
                }
            }
            
            // 모든 로봇 방문한 경우
            if(isAllArrived) break; // 반복 종료
        }
        
        
        return answer;
    }
    
    static class Robot {
        int r, c; // 로봇 좌표
        int next; // 다음 경유지
        
        Robot(int r, int c, int next) {
            this.r = r;
            this.c = c;
            this.next = next;
        }
        
        // 목표로 이동(행 좌표 먼저)
        void moveToTarget(int targetR, int targetC) {
            // 행 이동
            if (targetR < r) { // 상
                r--;
                return;
            } else if(r < targetR) { // 하
                r++;
                return;
            }
            
            // 열 이동
            if(targetC < c) // 좌
                c--;
            else if(c < targetC) // 우
                c++;
        }
        
        // (targetR, targetC) 좌표 도착 확인
        boolean hasArrived(int targetR, int targetC) {
            return r == targetR && c == targetC;
        }
        
        // 다음 경유지로 이동
        void moveToNext() {
            next++;
        }
    }
}