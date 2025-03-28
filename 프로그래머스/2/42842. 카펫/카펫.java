class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int carpet = brown + yellow;
        
        for(int i = 3; i <= carpet; i++) {
            int col = i; // 가로
            int row = carpet / col; // 세로
            
            if(col * row != carpet)
                continue;
            
            // 세로가 더 긴 경우
            if(row > col)
                continue; // 넘기기
            
            // 약수인 경우
            if((col - 2) * (row - 2) == yellow) {
                answer[0] = col;
                answer[1] = row;
                break;
            }
        }
        
        return answer;
    }
}