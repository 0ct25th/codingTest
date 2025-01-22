import java.util.*;

class Solution {
    
    static int N, M;
    static boolean[] isVisited;
    
    public int solution(int n, int[][] computers) {
        isVisited = new boolean[n];
        int answer = 0;
        
        for(int i = 0; i < n; i++) {
            if(isVisited[i])
                continue;
            
            bfs(i, n, computers);
            answer++;
        }
        
        return answer;
    }
    
    static void bfs(int start, int n, int[][] computers) {
        Queue<Integer> dq = new ArrayDeque<>();
        
        isVisited[start] = true;
        dq.offer(start);
        
        while(!dq.isEmpty()) {
            int cur = dq.poll();
            
            for(int i = 0; i < n; i++) {
                // 자기 자신인 경우 || 이미 방문한 경우 || 연결되어 있지 않은 경우
                if(cur == i || isVisited[i] || computers[cur][i] == 0)
                    continue;
                
                isVisited[i] = true;
                dq.offer(i);
            }
        }
    }
}