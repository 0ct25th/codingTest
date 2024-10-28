class Solution {
    static int answer = 0;
    
    public int solution(int[] numbers, int target) {
        subset(0, 0, target, numbers);
        return answer;
    }
    
    public void subset(int depth, int sum, int target, int[] numbers) {
        // 기저조건
        if(depth == numbers.length) {
            if(sum == target) answer++;
            
            return;
        }
        
        // 더하기
        subset(depth + 1, sum + numbers[depth], target, numbers);
        
        // 빼기
        subset(depth + 1, sum - numbers[depth], target, numbers);
    }
}