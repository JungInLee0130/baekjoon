import java.util.*;

public class Solution {
    static int N;
    public int[] solution(int[] arr) {
        int[] answer = {};
        
        Stack<Integer> stack = new Stack<>();
        
        N = arr.length;
        stack.push(arr[0]);
        for (int i = 1; i < N; i++){
            if (stack.peek() == arr[i]){
                continue; // 같으면
            }
            else{
                stack.push(arr[i]);
            }
        }
        
        int m = stack.size();
        answer = new int[m];
        
        for (int i = m - 1; i >= 0; i--){
            answer[i] = stack.pop();
        }

        return answer;
    }
}