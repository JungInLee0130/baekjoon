import java.util.*;

class Solution {
    static char[][] map;
    static int R,C;
    static int[] robot;
    static int[] goal;
    public int solution(String[] board) {
        int answer = 0;
        
        R = board.length;
        C = board[0].length();
        
        map = new char[R][C];
        // map 저장
        for(int i = 0; i < board.length; i++){
            map[i] = board[i].toCharArray();
        }
        
        robot = new int[2];
        goal = new int[2];
         
        // 시작, 도착점 저장
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if (map[i][j] == 'R'){
                    robot[0] = i;
                    robot[1] = j;
                }
                if (map[i][j] == 'G'){
                    goal[0] = i;
                    goal[1] = j;
                }
            }
        }
        
        min = bfs(robot[0], robot[1]);
        
        
        
        return min;
    }
    
    private static int min;
    private static int bfs(int x, int y){
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{x,y,0}); // 시작
        boolean[][] visited = new boolean[R][C];
        visited[x][y] = true;
        
        while (!que.isEmpty()){
            int[] cur = que.poll();
            int curR = cur[0];
            int curC = cur[1];
            int cnt = cur[2];
            
            for(int d = 0; d < 4; d++){
                int[] newRC = goStraight(curR,curC,d);
                int newR = newRC[0];
                int newC = newRC[1];
                
                if (visited[newR][newC]){
                    continue;
                }
                
                visited[newR][newC] = true;
                que.add(new int[]{newR,newC, cnt + 1});
                //System.out.println(newR + " " + newC);
                
                if (newR == goal[0] && newC == goal[1]){
                    return cnt + 1;
                }
            }

        }
        return -1;
    }
    
    private static int[] goStraight(int r, int c, int d){
        while (true){
            int newR = r + dr[d];
            int newC = c + dc[d];
            
            if (isOut(newR,newC)) break;
            if (map[newR][newC] == 'D') break;
            
            r = newR;
            c = newC;
        }
        
        return new int[]{r,c};
    }
    
    private static boolean isOut(int r, int c){
        return 0 > r || r >= R || 0 > c || c >= C;
    }
    
    private static int[] dr = {-1,1,0,0};
    private static int[] dc = {0,0,-1,1};
}