import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets, new Comparator<>(){ 
            
            public int compare(int[] o1, int[] o2){
                return Integer.compare(o1[1], o2[1]);    
            }
        });
        
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.offer(targets[0][1]); // 끝수넣음
        
        for(int i = 1; i < targets.length; i++){
            if (pq.peek() > targets[i][0]){ // 시작점이 더 작으면 : continue
                continue;
            }
            pq.offer(targets[i][1]);
        }
    
        return pq.size();
    }
}