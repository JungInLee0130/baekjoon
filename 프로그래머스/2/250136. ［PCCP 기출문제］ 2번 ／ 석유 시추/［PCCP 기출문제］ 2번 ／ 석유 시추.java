import java.util.*;

class Solution {
    static int R,C;
    static int[][] Land;
    static int max;
    public int solution(int[][] land) {
        R = land.length;
        C = land[0].length;
        Land = land;
        
        max = 0;
        
        // 1이 석유가 있는곳
        init();
        
        setColor();
        
        setMax();
        
        return max;
    }
    
    static int getSum(Set<Integer> set){
        Iterator<Integer> iter = set.iterator();
            
        int sum = 0;
        while (iter.hasNext()){
            int num = iter.next();
            sum += map.get(num);
        }
        
        return sum;
    }
    
    static void setMax(){
        for (int c = 0; c < C; c++){
            Set<Integer> set = new HashSet<>();
            for (int r = 0; r < R; r++){
                if (Land[r][c] > 0){
                    set.add(Land[r][c]);    
                }
            }
            
            int sum = 0;
            
            if (set.size() > 0){
                sum = getSum(set);
            }
            
            max = Math.max(sum, max);
        }
    }
    
    static void setColor(){
        visited = new boolean[R][C];
        map = new HashMap<>();
        int cnt = 1;
        for (int r = 0;r < R; r++){
            for (int c = 0; c < C; c++){
                if (!visited[r][c] && Land[r][c] == -1){
                    floodfill(r,c,cnt);
                    cnt++;
                }
            }
        }
    }
    
    static void init(){
        for (int r = 0;r < R; r++){
            for (int c = 0; c < C; c++){
                if (Land[r][c] == 1){
                    Land[r][c] = -1;
                }
            }
        }
    }
    
    static boolean[][] visited;
    
    static class Point{
        int x;
        int y;
        
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static Map<Integer, Integer> map;
    
    static void floodfill(int r, int c, int num){
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(r,c));
        Land[r][c] = num;
        visited[r][c] = true;
        
        int count = 1;
        
        while (!que.isEmpty()){
            Point cur = que.poll();
            
            for (int d = 0; d < 4; d++){
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                
                if (isOut(nx,ny)) continue;
                if (visited[nx][ny]) continue;
                if (Land[nx][ny] == 0) continue;
                if (Land[nx][ny] > 0) continue;
                
                que.add(new Point(nx,ny));
                Land[nx][ny] = num;
                count++;
            }
        }
        
        map.put(num, count);
        //System.out.println(map.get(num));
    }
    
    static boolean isOut(int nx, int ny){
        return 0 > nx || nx > R - 1
            || 0 > ny || ny > C - 1;
    }
}