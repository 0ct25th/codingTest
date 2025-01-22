import java.util.*;

class Solution {
    
    static int answer;
    static char[][] map = new char[102][102]; // 2배 크기 지도
    static boolean[][] isVisited; // 방문 여부
    static int[] dr = {-1, 1, 0, 0}; 
    static int[] dc = {0, 0, -1, 1}; 
    
    public int solution(int[][] rectangle, int X, int Y, int itemX, int itemY) {
        // 지도 초기화 (2배 크기 확장)
        for (int i = 0; i < rectangle.length; i++) {
            int x1 = rectangle[i][0];
            int y1 = rectangle[i][1];
            int x2 = rectangle[i][2];
            int y2 = rectangle[i][3];
            draw(y1 * 2, x1 * 2, y2 * 2, x2 * 2); // 좌표 2배 확장
        }
        
        isVisited = new boolean[102][102]; // 방문 여부 초기화
        answer = bfs(Y * 2, X * 2, itemY * 2, itemX * 2);  // 시작과 목표 좌표 2배 확장
        
        return answer / 2;  // 2배 확장했으므로 결과는 나누기 2
    }
    
    // BFS 탐색
    static int bfs(int startY, int startX, int goalY, int goalX) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startY, startX, 0}); // 시작점, 0은 시작 거리
        isVisited[startY][startX] = true;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int y = curr[0];
            int x = curr[1];
            int distance = curr[2];
            
            // 목표 지점에 도달하면 거리 반환
            if (y == goalY && x == goalX) {
                return distance;
            }
            
            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int ny = y + dr[i];
                int nx = x + dc[i];
                
                if (ny < 0 || nx < 0 || ny >= map.length || nx >= map[0].length)
                    continue;
                
                if (isVisited[ny][nx] || map[ny][nx] != '2')  // 이미 방문하거나 테두리가 아니면
                    continue;
                
                isVisited[ny][nx] = true;
                queue.add(new int[]{ny, nx, distance + 1});
            }
        }
        
        return -1; // 목표 지점에 도달하지 못한 경우
    }
    
    // 직사각형 테두리 그리기 (2배 확대된 좌표)
    static void draw(int y1, int x1, int y2, int x2) {
        for (int r = y1; r <= y2; r++) {
            for (int c = x1; c <= x2; c++) {
                // 이미 내부는 처리되었으면 건너뛰기
                if (map[r][c] == '1') 
                    continue;  
                
                map[r][c] = '1';  // 내부 처리
                
                if (r == y1 || r == y2 || c == x1 || c == x2) {
                    map[r][c] = '2';  // 테두리 처리
                }
            }
        }
    }
}
