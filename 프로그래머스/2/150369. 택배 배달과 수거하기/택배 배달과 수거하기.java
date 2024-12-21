import java.util.*;
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        // 현재 감당가능한 deliver, pickup 수
        int d = 0;
        int p = 0;
        
        for (int i = n - 1; i >= 0; i--){
            d -= deliveries[i];
            p -= pickups[i];
            
            // 감당 불가능하면
            while (d < 0 || p < 0){
                d += cap;
                p += cap;
                
                answer += (i + 1) * 2;
            }
        }
        
        
        return answer;
    }
}