import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        // 시간 오름차순 정렬
        Arrays.sort(times);
        
        long start = 0;
        long end = times[times.length - 1] * (long) n; //모든 사람이 가장 느리게 심사받음
        
        while(start <= end) {
            long mid = (start + end) / 2;
            long sum = 0;
            
            for (int i = 0; i < times.length; i++)
                sum += mid / times[i];
            
            // 해당 시간에는 모든 사람이 검사받을 수 없음
            if (sum < n)
                start = mid + 1;
            
            // 모두 검사받았으나, 더 최솟값이 있을 수 있음
            else {
                end = mid - 1;
                answer = mid;
            }
        }  
        return answer;
    }
}