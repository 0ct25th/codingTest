import java.util.*;

class Solution {
    
    static int[] minDist;
    static List<Integer>[] nodeList;
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        nodeList = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++)
            nodeList[i] = new ArrayList<>();
        
        for(int[] e: edge) {
            int a = e[0];
            int b = e[1];
            
            // 양방향 그래프
            nodeList[a].add(b);
            nodeList[b].add(a);
        }
        
        dijkstra(n);
        
        int max = 0;
        for(int i = 2; i <= n; i++) {
            if(minDist[i] == Integer.MAX_VALUE)
                continue;
            
            if(max == minDist[i]) {
                answer++;
            } else if (max < minDist[i]) {
                max = minDist[i];
                answer = 1;
            }
        }
        
        return answer;
    }
    
    static void dijkstra(int n) {
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> (o1.dist - o2.dist));
        minDist = new int[n + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);
        
        minDist[1] = 0;
        pq.offer(new Node(1, minDist[1]));
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if(cur.dist > minDist[cur.num])
                continue;
            
            for(int nxt: nodeList[cur.num]) {
                if(minDist[nxt] <= minDist[cur.num] + 1)
                    continue;
                
                minDist[nxt] = minDist[cur.num] + 1;
                pq.offer(new Node(nxt, minDist[nxt]));
            }
        }
    }
    
    static class Node {
        int num, dist;
        
        Node(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }
    }
}