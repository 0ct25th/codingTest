import java.util.*; 

class Solution {
    public int[] solution(String[] operations) {
        Queue<Integer> maxPq = new PriorityQueue<>((o1, o2) -> -(o1 - o2));
        Queue<Integer> minPq = new PriorityQueue<>();
        
        for(String str: operations) {
            char op = str.charAt(0);
            int num = Integer.parseInt(str.substring(2));
            
            switch(op) {
                case 'I':
                    maxPq.offer(num);
                    minPq.offer(num);
                    break;
                
                case 'D':
                    if(maxPq.isEmpty() || minPq.isEmpty())
                        continue;
                    
                    if(num == 1) { // 최대값 삭제
                        int rm = maxPq.poll();
                        
                        Queue<Integer> tmpPq = new PriorityQueue<>();
                        boolean chk = true;
                        while(!minPq.isEmpty()) {
                            int cur = minPq.poll();
                            
                            if(rm == cur && chk) {
                                chk = false;
                                continue;
                            }
                            
                            tmpPq.offer(cur);
                        }
                        
                        minPq = tmpPq;
                    } else { // 최소값 삭제
                        int rm = minPq.poll();
                        
                        Queue<Integer> tmpPq = new PriorityQueue<>((o1, o2) -> -(o1 - o2));
                        boolean chk = true;
                        while(!maxPq.isEmpty()) {
                            int cur = maxPq.poll();
                            
                            if(rm == cur && chk) {
                                chk = false;
                                continue;
                            }
                            
                            tmpPq.offer(cur);
                        }
                        
                        maxPq = tmpPq;
                    }
                    break;
            }
        }
        
        int[] answer = new int[2];
        if(!maxPq.isEmpty() && !minPq.isEmpty()) {
            answer[0] = maxPq.poll();
            answer[1] = minPq.poll();
        }
        
        
        return answer;
    }
}