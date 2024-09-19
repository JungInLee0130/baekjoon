import java.util.*;
class Solution {
    static PriorityQueue<Integer> pqMin, pqMax;
    static int N;
    public int[] solution(String[] operations) {
        int[] answer = {0,0};
        
        N = operations.length;
        
        pqMin = new PriorityQueue<>();
        pqMax = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int i = 0; i < N; i++){
            String[] sp = operations[i].split("\\s"); // 공백
            
            switch(sp[0]){
                case "I" :
                    int element = Integer.parseInt(sp[1]);
                    pqMin.add(element);
                    pqMax.add(element);
                    break;
                case "D" :
                    if (pqMin.isEmpty() && pqMax.isEmpty()) break;
                    // 최솟값
                    else if (sp[1].equals("-1")){
                        int poll = pqMin.poll();
                        pqMax.remove(Integer.valueOf(poll));
                    }
                    else{
                        int poll = pqMax.poll();
                        pqMin.remove(Integer.valueOf(poll));
                    }
                    break;
            }
        }
        
        if (!pqMin.isEmpty() && !pqMax.isEmpty()){
            if (pqMin.size() == 1){
                int poll = pqMin.poll();
                answer[0] = poll;
                answer[1] = poll;
            }
            else{
                int min = pqMin.poll();
                int max = pqMax.poll();
                
                answer[0] = max;
                answer[1] = min;
            }
        }
        
        return answer;
    }
}