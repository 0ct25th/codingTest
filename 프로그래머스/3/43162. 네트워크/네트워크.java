import java.util.*;

class Solution {
    static boolean[] isVisited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        isVisited = new boolean[n];
        
        for(int i = 0; i < n; i++) {
            if(!isVisited[i]) {
                bfs(i, n, computers);
                answer++;
            } 
        }
        
        return answer;
    }
    
    public void bfs(int start, int n, int[][] computers) {
        Queue<Integer> dq = new ArrayDeque<>();
        dq.offer(start); // 덱 삽입
        
        while(!dq.isEmpty()) {
            int now = dq.poll();
            isVisited[now] = true; // 방문 처리
            
            for(int i = 0; i < n; i++) {
                if(i == now || isVisited[i])
                    continue;
                
                if(computers[now][i] == 0)
                    continue;
                
                isVisited[i] = true;
                dq.offer(i);
            }
        }
    }
}