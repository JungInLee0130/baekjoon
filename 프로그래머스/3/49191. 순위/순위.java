class Solution {
    static int[][] adj;
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        // n - 1개의 승패 정보가 있어야한다는 것을 생각
        
        // 플로이드 워셜
        adj = new int[n][n];
        
        // 1. 그냥 결과를 업데이트
        for (int i = 0; i < results.length; i++){
            int start = results[i][0] - 1;
            int end = results[i][1] - 1;
            
            adj[start][end] = 1;
            adj[end][start] = -1;
        }
        
        // 2. 중간노드를 거쳐 갈수있는 결과를 업데이트
        for (int k = 0; k < n; k++){
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    if (adj[i][k] == 1 && adj[k][j] == 1){
                        adj[i][j] = 1;
                        adj[j][i] = -1;
                    }
                    else if (adj[i][k] == -1 && adj[k][j] == -1){
                        adj[i][j] = -1;
                        adj[j][i] = 1;
                    }
                }
            }
        }
        
        for (int i = 0; i < n; i++){
            int cnt = 0;
            for (int j = 0; j < n; j++){
                if (adj[i][j] != 0) cnt++;
            }
            if (cnt == n - 1){
                answer++;
            }
        }
        
        return answer;
    }
}