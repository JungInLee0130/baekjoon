import java.util.*;

class Solution {
    private int count = 0;
    public int solution(int n, int[][] computers) {
        isVisited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (isVisited[i]) continue;
            count += 1;
            isVisited[i] = true;
            dfs(i, n, computers);    
        }
        
        return count;
    }
    private boolean[] isVisited;
    private void dfs(int start, int n, int[][] computers) {
        for (int i = 0; i < n; i++){
            if (isVisited[i]) continue;
            if (start == i) continue;
            if (computers[start][i] != 1) continue; 
            isVisited[i] = true;
            dfs(i, n, computers); // end : i + 1(end이자 다음 함수에서는 start)
        }
    }
}