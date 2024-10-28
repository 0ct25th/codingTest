class Solution {

    public int solution(int[][] sizes) {
        int length = 0;
        int height = 0;
        
        for (int[] size: sizes) {
            length = Integer.max(length, Integer.min(size[0], size[1]));
            height = Integer.max(height, Integer.max(size[0], size[1]));
        }
        
        int answer = length * height;
        
        return answer;
    }
}