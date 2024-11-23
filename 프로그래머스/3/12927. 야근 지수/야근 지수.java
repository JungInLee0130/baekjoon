import java.util.*;
class Solution {
    static PriorityQueue<Integer> pq = new PriorityQueue<>
        (Collections.reverseOrder());
    
    static int workslen;
    public long solution(int n, int[] works) {
        workslen = works.length;
        
        for (int i = 0; i < workslen; i++){
            pq.add(works[i]);
        }
        
        while (n != 0){ // 100만
            int e = pq.poll();
            if (e == 0){
                break;
            }
            e--;
            n--;
            pq.add(e);
        }
        
        long answer = 0;
        while (!pq.isEmpty()){
            int e = pq.poll();
            answer += Math.pow(e, 2);
        }
        
        return answer;
    }
}