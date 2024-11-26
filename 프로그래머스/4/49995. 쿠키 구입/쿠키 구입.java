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
                
                if (leftSum == rightSum){
                    max = Math.max(leftSum, max);
                }
                
                if (leftSum >= rightSum){
                    ++right;
                    if (right > N - 1){
                        break;
                    }
                    rightSum += cookie[right];
                }
                
                else if (rightSum >= leftSum){ // rightSum > leftSum
                    --left;
                    if (left < 0){
                        break;
                    }
                    leftSum += cookie[left];
                }
            }
        }
        
        return max;
    }
}