class Solution {
    static int level;
    static int N; // 퍼즐의 개수
    public int solution(int[] diffs, int[] times, long limit) {
        N = diffs.length;
        
        int start = 1;
        int end = 0;// diff max
        
        for (int i = 0; i < N; i++){
            end = Math.max(diffs[i], end);        
        }
        
        while (start <= end){
            int mid = (start + end) / 2;
            
            long sumTime = 0;
            
            // mid == level
            for (int i = 0; i < N; i++){
                // 0일때 여기들어가니까 딱히 신경안써도됨.
                if (mid >= diffs[i]){ // level > diffs[i] 
                    sumTime += times[i];
                }
                else{ // level < diffs[i]
                    // time_prev
                    // diffs[i] - level의 차이만큼 틀림
                    int tempTime = (diffs[i] - mid) * (times[i - 1] + times[i]);
                    sumTime += tempTime + times[i];
                }
            }
            
            // sumTime이 limit 내외인지 확인
            
            if (sumTime > limit){ // sumTime > limit
                start = mid + 1;
            }
            else{ // sumTime <= limit
                level = mid;
                end = mid - 1;
            }
        }
        
        return level;
    }
    
}