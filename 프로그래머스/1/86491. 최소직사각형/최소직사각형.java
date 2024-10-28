class Solution {

    public int solution(int[][] sizes) {
        int answer = 0;
        int zero = 0;
        int one = 0;
        
        for (int[] size: sizes) {
            int z = size[0];
            int o = size[1];
            
            // 작은 값 [0]으로 몰아버리기
            if(z > o) {
                size[0] = o;
                size[1] = z;
            }
            
            zero = Integer.max(zero, size[0]);
            one = Integer.max(one, size[1]);
        }
        
        answer = zero * one;
        
        return answer;
    }
}