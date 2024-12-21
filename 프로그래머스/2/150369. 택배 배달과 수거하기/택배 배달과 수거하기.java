import java.util.*;
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int curD = 0;
        int curP = 0;
        for (int i = n - 1; i >= 0; i--){
            curD -= deliveries[i];
            curP -= pickups[i];
            
            while (curD < 0 || curP < 0){
                curD += cap;
                curP += cap;
                
                answer += (i + 1) * 2;
            }
        }
        
        return answer;
    }
}