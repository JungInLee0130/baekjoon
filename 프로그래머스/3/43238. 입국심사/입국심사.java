import java.util.*;
class Solution {
    static long N, M;
    public long solution(int n, int[] times) {
        N = n;
        M = times.length;
        
        long low = 1;
        long high = 0;
        
        for (int i = 0; i < M; i++){
            high = Math.max(high, times[i]);
        }
        
        high = high * N; // 최대 시간
        
        long minTime = 0;
        
        while (low <= high){
            long mid = (low + high) / 2;
            
            long people = 0;
            for (int i = 0; i < M; i++){
                people += mid / times[i]; // 통과시킬수있는 총 인원수
            }
            
            if (people >= n){
                minTime = mid;
                high = mid - 1;
                high = mid - 1;
            }
            else if (people < n){
                low = mid + 1;
            }
        }
        
        return minTime;
    }
}