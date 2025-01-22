import java.util.*;

class Solution {
    
    static int answer = 0;
    static boolean[] isVisited;
    
    public int solution(String begin, String target, String[] words) {
        isVisited = new boolean[words.length];
        bfs(begin, target, words);
        
        return answer;
    }
    
    static void bfs(String begin, String target, String[] words) {
        Queue<Word> dq = new ArrayDeque<>();
        dq.offer(new Word(0, begin));
        
        while(!dq.isEmpty()) {
            Word cur = dq.poll();
            int count = cur.count;
            String word = cur.word;
            
            // target을 찾은 경우
            if (word.equals(target)) {
                answer = count;
                return;
            }
            
            // words 배열 탐색
            for(int i = 0; i < words.length; i++) {
                // 이미 방문한 경우
                if(isVisited[i])
                    continue;
                
                // 다른 알파벳 개수 세기
                int sCount = 0;
                for(int j = 0; j < word.length(); j++) {
                    if(word.charAt(j) != words[i].charAt(j))
                        sCount++;
                }
                
                // 1개만 다른 경우만 취급
                if(sCount == 1) {
                    isVisited[i] = true;
                    dq.offer(new Word(count + 1, words[i]));
                }
            }
        }
    }
    
    static class Word{
        int count;
        String word;
        
        Word(int count, String word) {
            this.count = count;
            this.word = word;
        }
    }
}