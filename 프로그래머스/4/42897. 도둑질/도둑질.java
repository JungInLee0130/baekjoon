class Solution {
    static int N;
    static int[] dpO, dpX;
    public int solution(int[] money) {
        N = money.length;
        dpO = new int[N];
        dpX = new int[N];
        
        // 첫집을 터는 경우, 털지 않는 경우
        dpO[0] = money[0];
        dpO[1] = dpO[0];
        
        dpX[0] = 0;
        dpX[1] = money[1];
        
        for (int i = 2; i < N; i++){
            dpO[i] = Math.max(dpO[i - 1], dpO[i - 2] + money[i]);
            dpX[i] = Math.max(dpX[i - 1], dpX[i - 2] + money[i]);
        }
        
        return Math.max(dpO[N - 2], dpX[N - 1]);
    }
}