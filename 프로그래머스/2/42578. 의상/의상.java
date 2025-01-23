import java.util.*;

class Solution {
    
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> category = new HashMap<>();
        
        // 옷 분류하기
        for(String[] cloth: clothes)
            category.put(cloth[1], category.getOrDefault(cloth[1], 0) + 1);
    
        // 옷 종류 세기
        Iterator<Integer> iter = category.values().iterator();
        while(iter.hasNext())
            answer *= iter.next().intValue() + 1;
        
        return answer - 1;
    }
}