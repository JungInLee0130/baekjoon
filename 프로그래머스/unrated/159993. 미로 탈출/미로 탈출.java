import java.util.*;

class Solution {
    static int R, C;
    static char[][] miro;
    static int[] start;
    static int[] labber;
    static int[] end;
    public int solution(String[] maps) {
        R = maps.length; // String[] 에는 length, String에는 length()
        C = maps[0].length();
        
        // String[] -> char[][] 변환
        miro = new char[R][C];
        for(int i = 0; i < R; i++){
            miro[i] = maps[i].toCharArray();
        }
        
        start = new int[2];
        labber = new int[2];
        end = new int[2];
        for(int i = 0; i < R; i++){
            for (int j = 0; j < C; j++){
                if (miro[i][j] == 'S'){
                    start[0] = i;
                    start[1] = j;
                }
                if (miro[i][j] == 'E'){
                    end[0] = i;
                    end[1] = j;
                }
                if (miro[i][j] == 'L'){
                    labber[0] = i;
                    labber[1] = j;
                }
            }
        }
        
        // S -> L
        int a = bfs(start[0], start[1], labber[0], labber[1]);
        
        // -1이면 그냥
        if (a == -1) return -1;
        
        // L -> E
        int b = bfs(labber[0], labber[1], end[0], end[1]);
        
        if (b == -1) return -1;
        
        // 둘다 -1이 아니면
        return a + b;
    }
    
    private static int[] dr = {-1,1,0,0};
    private static int[] dc = {0,0,-1,1};
    
    private static int bfs(int x, int y, int destR, int destC){
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {x,y,0}); // x,y,cnt : 몇번만에 가는지
        boolean[][] visited = new boolean[R][C];
        visited[x][y] = true; // 시작점 true
        
        while (!que.isEmpty()){
            int[] cur = que.poll();
            int curR = cur[0];
            int curC = cur[1];
            int cnt = cur[2];
            
            for(int d = 0; d < 4; d++){
                int newR = curR + dr[d];
                int newC = curC + dc[d];
                
                // 밖으로 나가는경우
                if (isOut(newR,newC)) continue;
                // 벽인경우
                if (miro[newR][newC] == 'X') continue;
                // 중복방문
                if (visited[newR][newC]) continue;
                
                visited[newR][newC] = true;
                que.add(new int[] {newR, newC, cnt + 1});
                
                if (newR == destR
                   && newC == destC){
                    return cnt + 1;
                }
            }
        }
        return -1; // 도달하지 못한경우
    }
    
    private static boolean isOut(int x, int y){
        return x < 0 || x >= R || y < 0 || y >= C;
    }
}