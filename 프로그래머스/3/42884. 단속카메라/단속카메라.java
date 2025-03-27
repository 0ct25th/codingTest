import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 1; // 카메라 수
        
        // 진출 지점 기준으로 정렬
        Arrays.sort(routes, (o1, o2) -> (o1[1] - o2[1]));
        
        // 카메라 위치 저장 변수
        int camera = routes[0][1]; // 첫 차량 진출 지점
        for(int i = 1; i < routes.length; i++) {
            // 카메라가 i번 차량 진입 시점보다 앞서 있는 경우
            if(camera >= routes[i][0])
                continue;
            
            camera = routes[i][1]; // 카메라 위치 조정
            answer++; // 카메라 개수 증가
        }
        
        return answer;
    }
}