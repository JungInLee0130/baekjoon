import java.util.*;
class Solution {
    static List<List<Point>> graph;
    static Point[] points;
    static class Point{
        int num;
        int depth;
        
        public Point(int num, int depth){
            this.num = num;
            this.depth = depth;
        }
    }
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        points = new Point[n + 1];
        for (int i = 1; i <= n; i++){
            points[i] = new Point(i, 0);
        }
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < edge.length; i++){
            int start = edge[i][0];
            int end = edge[i][1];
            
            graph.get(start).add(new Point(end, 0));
            graph.get(end).add(new Point(start, 0));
        }
        
        maxDepth = bfs(n);
        int count = 0;
        for (int i = 1; i <= n; i++){
            if (points[i].depth == maxDepth){
                count++; 
            }
        }
        
        return count;
    }
    
    static int maxDepth = 0;
    static int bfs(int n){
        Queue<Point> que = new LinkedList<>();
        que.add(new Point(1, 0));
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        
        while (!que.isEmpty()){
            Point poll = que.poll();
            
            maxDepth = Math.max(maxDepth, poll.depth);
            
            for (Point p:graph.get(poll.num)){
                if (visited[p.num]) continue;
                visited[p.num] = true;
                que.add(new Point(p.num, poll.depth + 1));
                points[p.num].depth = poll.depth + 1;
            }
        }
        return maxDepth;
    }
}