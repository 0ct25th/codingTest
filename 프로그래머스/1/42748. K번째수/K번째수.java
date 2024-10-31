import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for(int a = 0; a < commands.length; a++) {
            int i = commands[a][0];
            int j = commands[a][1];
            int k = commands[a][2];
            
            List<Integer> list = new ArrayList<>();
            for(int b = i - 1; b < j; b++) {
                System.out.println("b: " + b);
                list.add(array[b]);
            }
            
            Collections.sort(list);
            
            answer[a] = list.get(k - 1);
        }
        
        return answer;
    }
}