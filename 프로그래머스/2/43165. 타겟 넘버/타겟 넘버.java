class Solution {
    
    static int answer = 0;
    
    public int solution(int[] numbers, int target) {
        
        dfs(0, 0, numbers, target);
        
        return answer;
    }
    
    static void dfs(int depth, int sum, int[] numbers, int target) {
        // 기저조건: 모든 숫자가 사용 된 경우
        if(depth == numbers.length) {
            if(sum == target)
                answer++;
            
            return;
        }
        
        // 더하기
        dfs(depth + 1, sum + numbers[depth], numbers, target);
        
        // 빼기
        dfs(depth + 1, sum - numbers[depth], numbers, target);
    }
}