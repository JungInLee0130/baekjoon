class Solution {
    static int N;
    static int[] pulseUpSequence; // 1시작
    static int[] pulseDownSequence; // -1시작
    public long solution(int[] sequence) {
        N = sequence.length;
        
        pulseUpSequence = new int[N];
        pulseDownSequence = new int[N];
        boolean isUp = true;
        for (int i = 0; i < N; i++){
            if (isUp){
                pulseUpSequence[i] = 1 * sequence[i];
                pulseDownSequence[i] = -1 * sequence[i];
            }
            else{
                pulseUpSequence[i] = -1 * sequence[i];
                pulseDownSequence[i] = 1 * sequence[i];
            }
            isUp = !isUp;
        }
        
        // 여기서 부분합이 가장 큰걸 찾아내면됨.
        long[] sum1 = new long[N];
        long[] sum2 = new long[N];
        // 최대 누적합들 구하기
        // 이전 것들의 합 vs 이전것만 
        
        sum1[0] = pulseUpSequence[0];
        sum2[0] = pulseDownSequence[0];
        
        // 처음도 초기값을 잡아야한다... long.MIN_VALUE 쓰면안됨.
        long max = Math.max(sum1[0], sum2[0]);
        
        for (int i = 1; i < N; i++){
            // 1. 누적합 vs 이전것만
            // i == 1 부터 시작하기때문에
            // 1에서는 이전원소와 자신의 합과 자기자신을 비교하게된다.
            // 따라서 누적합 vs 이전것만 -> 누적합 + 이전것만을 합치고 vs 자기자신 으로
            // 합칠수있다.
            sum1[i] = Math.max(sum1[i - 1] + pulseUpSequence[i],
                               pulseUpSequence[i]);
            
            sum2[i] = Math.max(sum2[i - 1] + pulseDownSequence[i],
                               pulseDownSequence[i]);
            
            max = Math.max(sum1[i], max);
            max = Math.max(sum2[i], max);
        }
        
        return max;
    }
}