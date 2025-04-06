import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0; // 모든 로봇이 운송을 마칠 때까지 발생하는 위험한 상황의 횟수
        
        int routeStep = routes[0].length; // 총 경유지 개수 -> 모든 경유지 다 돌았는지 체크
        List<Robot> robots = new ArrayList<>();
        Map<Integer, Integer> startCollisions = new HashMap<>(); // 충돌 체크 맵
        
        // 초기 시작 위치
        for(int[] route: routes) {
            int start = route[0] - 1;
            int r = points[start][0];
            int c = points[start][1];
            
            robots.add(new Robot(r, c, 1));
            // route가 존재하지 않으면 1, 존재하면 기존값 + 1
            startCollisions.merge(route[0], 1, Integer::sum);
            // startCollisions.merge(route[0], 1, (oldValue, newValue) -> oldValue + newValue);
        }
        
        // 초기 시작 위치에서 충돌 횟수 탐색
        for(int value: startCollisions.values()) 
            if(value > 1) // 같은 좌표가 2개 이상인 경우
                answer++; // 충돌 횟수 증가
        
        // 모든 로봇이 목적지에 도착할 때까지 반복
        while(!robots.isEmpty()) {
            Map<String, Integer> collisionsMap = new HashMap<>(); // 충돌 체크 맵
            boolean[] arrived = new boolean[robots.size()]; // 모든 로봇 도착 체크 배열
            
            // 로봇 이동하기
            for(int i = 0; i < robots.size(); i++) {
                // 현재 로봇 정보
                Robot cur = robots.get(i);
                int next = cur.next;
                
                // 현재 로봇이 목적지에 도달한 경우
                if(next >= routeStep) {
                    arrived[i] = true; // 도착 체크
                    continue; // 넘기기
                }
                
                // 다음 경유지 좌표값 찾기
                int pointIdx = routes[i][next] - 1;
                int targetR = points[pointIdx][0];
                int targetC = points[pointIdx][1];
                
                cur.moveToTarget(targetR, targetC); // 다음 경유지로 이동
                
                // 이동 후 현재 좌표
                String curCoord = cur.r + " " + cur.c;
                collisionsMap.merge(curCoord, 1, Integer::sum); // 충돌 체크
                
                // 현재 좌표가 포인트에 도착한 경우
                if(cur.isArrived(targetR, targetC))
                    cur.plusToNext(); // 다음 경유지 이동
            }
            
            // 충돌 확인
            for(int value: collisionsMap.values())
                if(value > 1)
                    answer++;
            
            // 모든 로봇 도착 체크
            boolean isAllArrived = true;
            for(boolean status: arrived) {
                if(!status) {
                    isAllArrived = false;
                    break;
                }
            }
            
            // 모든 로봇 도착한 경우
            if(isAllArrived) break; // 반복 종료
        }
        
        return answer;
    }
    static class Robot {
        int r, c; // 해당 로봇 좌표
        int next; // 다음 경유지

        Robot(int r, int c, int next) {
            this.r = r;
            this.c = c;
            this.next = next;
        }

        // 위치 이동(상, 하, 좌, 우 순)
        void moveToTarget(int targetR, int targetC) {
            // 행 이동(먼저 이동 !)
            if (r > targetR) {
                r--;
                return;
            } else if(r < targetR) {
                r++;
                return;
            } 

            // 열 이동
            if(c > targetC) 
                c--;
             else if(c < targetC) 
                c++;
        }

        // 도착 여부 확인
        boolean isArrived(int targetR, int targetC) {
            return r == targetR && c == targetC;
        }

        // 다음 경유지로 이동
        void plusToNext() {
            next++;
        }
    }
}