import java.util.*;
class Solution {
    private int max = 0;
    private boolean[] visited;
    private int dungeonCount;
    public int solution(int k, int[][] dungeons) {
        dungeonCount = dungeons.length; // 던전의 개수 : 최대 8개
        visited = new boolean[dungeonCount];
        
        dfs(dungeons, 0, k, 0); // 초기 : 현재 인덱스 0, 현재 체력 k로 시작
        
        return max;
    }
    private void dfs(int[][] dungeons, int curIdx, int curHp, int maxCount) {
        // 현재 피로도 : k
        for (int i = 0; i < dungeonCount; i++){
            int minHp = dungeons[i][0];
            int subHp = dungeons[i][1];
            
            if (visited[i]) continue; // 한번만 방문가능
            if (curHp < minHp) continue; // 현재 피로도가 더 적으면 못돔.
            visited[i] = true; // 방문체크
            dfs(dungeons, i, curHp - subHp, maxCount + 1); // i 방문, subHp 감소
            visited[i] = false; // 초기화
        }
        
        max = Math.max(maxCount, max);
    }
}