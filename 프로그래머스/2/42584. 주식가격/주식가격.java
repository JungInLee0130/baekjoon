class Solution {
    static int N;
    public int[] solution(int[] prices) {
        int[] answer = {};
        
        N = prices.length;
        
        answer = new int[N];
        for (int i = 0; i < N; i++){
            for (int j = i + 1; j < N; j++){
                if (prices[i] <= prices[j]){
                    answer[i]++;
                }
                else{
                    answer[i]++;
                    break;
                }
            }
        }
        
        
        return answer;
    }
}