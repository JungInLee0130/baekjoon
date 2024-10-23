class Solution {
    static int cnt;
    public int solution(int n, int[] money) {
        //%1_000_000_007;
        
        long[] dp = new long[n + 1]; // 0 ~ n
        
        // 초기(화폐 한개 사용) : (경우의 수)
        for (int i = 0; i <= n; i++){
            if (i % money[0] == 0){
                dp[i] = 1;
            }
        }
        
        for (int i = 1; i < money.length; i++){
            for (int j = money[i]; j <= n; j++){
                dp[j] = dp[j] + dp[j - money[i]];
            }
        }
        
        cnt = (int)(dp[n] % 1_000_000_007);
        
        return cnt;
    }
}