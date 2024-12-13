import java.util.*;
// 어려워서 나중에 다시봐야할듯
// 아무리봐도
// a + b <= gmax + smin = gmin + smax가 왜 성립하는지 모르겠음

// 유형은 이분탐색.
// k시간안에 옮길수있는지를 알아보면됨. 여기서 k가 최소가 되게하면되고.
class Solution {
    static long N;
    static long answer;
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        N = g.length;
        
        answer = (long) ((10e9 * 2) / 1 * (10e5 * 2)); // 필요한 최대무게, 1kg만 옮기기 가능하고, 모두 왕복함.
        
        long start = 0;
        long end = answer;
        
        while (start <= end){
            long mid = (start + end) / 2;
            int gold = 0; // 금
            int silver = 0; // 은
            int add = 0; // 합계
            
            for (int i = 0; i < N; i++){
                int nowGold = g[i];
                int nowSilver = s[i];
                int nowWeight = w[i];
                long nowTime = t[i];
                
                long moveCount = mid / (nowTime * 2); // 가능한 왕복횟수
                
                if (mid % (nowTime * 2) >= t[i]){
                    moveCount++;
                }
                
                gold += Math.min(nowGold, moveCount * nowWeight);
                silver += Math.min(nowSilver, moveCount * nowWeight);
                add += Math.min(nowGold + nowSilver, moveCount * nowWeight);
            }
            
            if (a <= gold && b <= silver && a + b <= add){
                end = mid - 1;
                answer = Math.min(mid, answer);
                continue;
            }
            start = mid + 1;
        }
        
        return answer;
    }
}