import java.util.*;
class Solution {
    // deque를 LinkedList로 구현
    // 이러면 정렬가능
    static LinkedList<Integer> deque; 
    static int N;
    public int[] solution(String[] operations) {
        int[] answer = {0,0};
        
        N = operations.length;
        
        deque = new LinkedList<>();
        
        for (int i = 0; i < N; i++){
            String[] sp = operations[i].split("\\s"); // 공백
            String command = sp[0];
            int num = Integer.parseInt(sp[1]);
            
            switch(command){
                case "I" :
                    deque.add(num);
                    Collections.sort(deque);
                    break;
                case "D" :
                    // 빈큐 : 무시
                    if (deque.isEmpty()) break;
                    // 최솟값
                    else if (num == -1){
                        deque.pollFirst();
                    }
                    // 최댓값
                    else{
                        deque.pollLast();
                    }
                    
                    break;
            }
        }
        
        // 비어있으면 그냥 {0,0} 리턴
        if (!deque.isEmpty()){
            answer[0] = deque.getLast();
            answer[1] = deque.getFirst();
        }
        
        return answer;
    }
}