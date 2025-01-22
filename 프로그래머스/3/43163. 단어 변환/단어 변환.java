class Solution {
    
    static int answer = 0;
    static boolean[] isVisited;
    
    public int solution(String begin, String target, String[] words) {
        isVisited = new boolean[words.length];
        dfs(begin, target, words, 0);
        
        return answer;
    }
    
    static void dfs(String begin, String target, String[] words, int count) {
        // 변환 가능한 경우
        if(begin.equals(target)) {
            answer = count;
            
            return;
        }
        
        // words 배열 탐색
        for(int i = 0; i < words.length; i++) {
            // 이미 탐색한 경우 넘기기
            if(isVisited[i])
                continue;
            
            // 몇 글자 다른지 판단
            int spelling = 0;
            for(int j = 0; j < begin.length(); j++) {
                if(begin.charAt(j) != words[i].charAt(j))
                    spelling++;
            }
            
            // 1글자만 다른 경우
            if(spelling == 1) {
                isVisited[i] = true;
                dfs(words[i], target, words, count + 1);
            }
        }
    }
}