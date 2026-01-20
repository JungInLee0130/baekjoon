import java.util.*;
class Solution {
    private int max = 0;
    private boolean[] visited;
    private int dungeonCount;
    
    public int solution(int k, int[][] dungeons) {
        dungeonCount = dungeons.length;
        visited = new boolean[dungeonCount];
        
        dfs(dungeons, k, 0); // 초기 : 현재 인덱스 0, 현재 체력 k로 시작
        
        return max;
    }
    
    private void dfs(int[][] dungeons, int curHp, int depth) {
        max = Math.max(depth, max);
 
        for (int i = 0; i < dungeonCount; i++){
            int minHp = dungeons[i][0];
            int subHp = dungeons[i][1];
            
            if (visited[i]) continue;       // 한번만 방문가능
            if (curHp < minHp) continue;    // 현재 피로도가 더 적으면 못돔.
            visited[i] = true;              // 방문체크
            dfs(dungeons, curHp - subHp, depth + 1); // i 방문, subHp 감소
            visited[i] = false;             // 복구
        }
    }
}