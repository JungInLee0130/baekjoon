import java.util.*;
class Solution {
    public int[] solution(int n, int s) {
        // 좀 머리가 필요한문제
        // S / N = 몫이 있으면 균등하게 배분
        // S % N = 나머지가 있으면 균등하게 1씩 배분
        // 원소가 존재하면 모두 곱하고, S/N == 0 이면 -1을 넣어서 리턴
        
        if (s / n == 0) return new int[]{-1};
        
        int[] answer = new int[n]; // n개
        
        int p = s / n;
        int r = s % n;
        
        for (int i = 0; i < n; i++){
            answer[i] = p;
        }
        
        int idx = 0;
        while (r != 0){
            answer[idx++]++;
            r--;
        }
        
        Arrays.sort(answer);
        
        return answer;
    }
}