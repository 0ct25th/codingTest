class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int start = 1;
        int end = Integer.MIN_VALUE;
        for(int i = 0; i < diffs.length; i++)
            end = Math.max(end, diffs[i]);
        
        while(start < end) {
            int mid = (start + end) / 2; // 현재 숙련도
            long time = 0l;
            
            int time_prev = 0;
            for(int i = 0; i < diffs.length; i++) {
                int diff = diffs[i]; // 난이도
                int time_cur = times[i]; // 소요 시간
                
                // 현재 난이도로 풀 수 있는 경우
                if(diff <= mid) 
                    time += time_cur;
                
                // 현재 난이도로 풀 수 없는 경우
                else 
                    time += (time_cur + time_prev) * (diff - mid) + time_cur;
                    
                time_prev = time_cur;
            }
            
            if(time > limit)
                start = mid + 1;
            else
                end = mid;
        }
        
        return end;
    }
}