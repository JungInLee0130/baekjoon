import java.util.*;

// 히스토그램이랑 유사..
class Solution {
    static int N;
    public int[] solution(int[] prices) {
        int[] answer = {};
        
        // N이 1억이라면?
        N = prices.length;
        
        answer = new int[N];
        // 인덱스 저장
        Stack<Integer> stack = new Stack<>();
        
        stack.push(0);
        
        for (int i = 1; i < N; i++){
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]){
                int preIdx = stack.pop();
                answer[preIdx] = i - preIdx; // 개수 저장
            }
            stack.push(i);
        }
        
        while (!stack.isEmpty()){
            int idx = stack.pop();
            answer[idx] = N - 1 - idx;
        }
        
        return answer;
    }
}