import java.util.*;

class Solution {
    public String solution(String s) {
        StringTokenizer st = new StringTokenizer(s);
        List<Integer> list = new ArrayList<>();
        while(st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());
            list.add(num);
        }
        
        Collections.sort(list);
        
        return list.get(0) + " " + list.get(list.size() - 1);
    }
}