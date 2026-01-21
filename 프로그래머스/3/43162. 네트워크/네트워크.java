import java.util.*;

class Solution {
    private List<List<Integer>> graph;
    public int solution(int n, int[][] computers) {
        graph = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        
        // 인접 행렬 -> 연결 그래프
        for (int i = 0; i < computers.length; i++) {
            for (int j = i + 1; j < computers[i].length; j++) {
                if (computers[i][j] == 1) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);    // 무방향그래프
                }
            }
        }
        
        isVisited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (isVisited[i]) continue;
            count += bfs(i, n);    
        }
        
        return count;
    }
    private boolean[] isVisited;
    private int bfs(int start, int n) {
        Queue<Integer> que = new LinkedList<>();
        que.add(start); // 시작점 : 0
        int count = 1;  // 네트워크 : 1        
        isVisited[start] = true;
        
        while (!que.isEmpty()) {
            Integer poll = que.poll();
            
            for(Integer e : graph.get(poll)){
                if (isVisited[e]) continue;
                isVisited[e] = true;
                que.add(e);
            }
        }
        
        return count;
    }
}