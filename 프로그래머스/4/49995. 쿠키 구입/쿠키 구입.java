class Solution {
    static int N;
    public int solution(int[] cookie) {
        N = cookie.length;
        
        int max = 0;
        
        for (int i = 0; i < N - 1; i++){
            int left = i;
            int right = i + 1;
            int leftSum = cookie[left];
            int rightSum = cookie[right];
            
            while (true){
                
                // 일단 같아도 최대값을 구해야하기때문에 계속 돌려야함.
                if (leftSum == rightSum){
                    max = Math.max(leftSum, max);
                }
                
                if (leftSum >= rightSum && right < N - 1){
                    rightSum += cookie[++right];
                }
                
                else if (rightSum >= leftSum && left > 0){ // rightSum > leftSum
                    leftSum += cookie[--left];
                }
                else{ // 구간이 맞징낳을때를 따로 빼줌.
                    break;
                }
            }
        }
        
        return max;
    }
}