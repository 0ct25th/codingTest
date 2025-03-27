import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        // 두 배열을 확인할 인덱스
        int a = 0;
        int b = 0;
        for(int i = 0; i < A.length; i++) {
            // A[a]가 더 크거나 같은 경우
            if(A[a] >= B[b]) 
                b++; // b배열 다음 순서로
            
            // A와 B값이 같은 경우
            else {
                a++; // a배열 다음 순서로
                b++; // b배열 다음 순서로
                answer++; // 승점 증가
            }
        }
        
        return answer;
    }
}