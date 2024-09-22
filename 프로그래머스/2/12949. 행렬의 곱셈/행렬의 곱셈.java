class Solution {
    static int A,B,C,D;
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = {};
        
        A = arr1.length;
        B = arr1[0].length;
        
        C = arr2.length;
        D = arr2[0].length;
        
        answer = new int[A][D];
        
        for (int j = 0; j < B; j++){
            answer[0][0] += arr1[0][j] * arr2[j][0];
        }
        
        for (int j = 0; j < B; j++){
            answer[0][1] += arr1[0][j] * arr2[j][1];
        }
        
        for (int i = 0; i < A; i++){
            for (int j = 0; j < D; j++){
                answer[i][j] = cal(arr1, arr2, i, j);
            }
        }
        
        return answer;
    }
    
    private static int cal(int[][] arr1, int[][] arr2, int i, int j){
        int res = 0;
        for (int k = 0; k < B; k++){
            res += arr1[i][k] * arr2[k][j];
        }
        
        return res;
    }
}