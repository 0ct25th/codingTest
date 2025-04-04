import java.util.*;

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int t = bandage[0]; // 시전 시간
        int s = bandage[1]; // 초당 회복량
        int a = bandage[2]; // 추가 회복량
        
        int cur = health; // 현재 체력
        int seq = 0; // 연속 성공
        int time = 0; // 현재 시간
        int idx = 0; // 공격 순번
        while(idx < attacks.length) {
            // 현재 시간이 공격 시간인 경우
            if(time == attacks[idx][0]) {
                cur = Math.max(0, cur - attacks[idx][1]); // 체력 감소
                idx++; // 다음 공격
                seq = 0; // 연속 초기화
            }
            
            // 현재 시간이 공격 시간이 아닌 경우 -> 붕대 감기
            else {
                cur = Math.min(health, cur + s); // 체력 증가
                seq++; // 연속 성공
                
                // 연속 성공인 경우
                if(seq == t) {
                    cur = Math.min(health, cur + a); // 추가 체력 증가
                    seq = 0; // 연속 성공 초기화
                }
            }
            
            // 체력이 0이하인 경우
            if(cur <= 0)
               return -1; // 죽음
            
            time++; // 시간 증가
        }
        
        return cur;
    }
}