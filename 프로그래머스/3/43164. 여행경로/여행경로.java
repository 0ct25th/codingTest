import java.util.*;

class Solution {
    
    static boolean[] isSelected;
    static List<String> allRoutes;
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        isSelected = new boolean[tickets.length]; // 주어진 항공권 모두 사용
        allRoutes = new ArrayList<>();
        
        dfs(0, "ICN", "ICN", tickets);
        Collections.sort(allRoutes); // 알파벳 순서가 앞서는 경로 구하기
        answer = allRoutes.get(0).split(" ");
        
        return answer;
    }
    
    static void dfs(int depth, String now, String route, String[][] tickets) {
        // 기저조건: 모든 항공권을 사용한 경우
        if(depth == tickets.length) {
            allRoutes.add(route); // 지금 루트 저장
            return;
        }
        
        for(int i = 0; i < tickets.length; i++) {
            // 사용한 항공권인 경우 || 지금 공항과 일치하지 않은 경우
            if(isSelected[i] || !now.equals(tickets[i][0]))
                continue;   // 넘김
            
            isSelected[i] = true;   // 선택 체크
            dfs(depth + 1, tickets[i][1], route + " " + tickets[i][1], tickets);
            isSelected[i] = false;  // 선택 원복
            
            
        }
    }
}