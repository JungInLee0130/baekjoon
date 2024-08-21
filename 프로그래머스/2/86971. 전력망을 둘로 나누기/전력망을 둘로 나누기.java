import java.util.*;
class Solution {
    static int min = Integer.MAX_VALUE;
    static int N;
    static ArrayList<ArrayList<Integer>> graph;
    public int solution(int n, int[][] wires) {
        int answer = -1;
        
        N = n;
        
        graph = new ArrayList<>();
        
        for (int i = 0; i <= n; i++){ // 1 ~ n
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < n - 1; i++){
            int s = wires[i][0];
            int e = wires[i][1];
            
            graph.get(s).add(e);
            graph.get(e).add(s);
        }
        
        for (int i = 0; i < n - 1; i++){
            int sa = wires[i][0];
            int sb = wires[i][1];
            
            int countA = getCount(sa,sb);
            int countB = getCount(sb,sa);
            
            min = Math.min(min, Math.abs(countB - countA));
        }
        
        
        return min;
    }
    
    static int getCount(int sa, int sb){
        Queue<Integer> que = new LinkedList<>();
        que.add(sa);
        boolean[] visited = new boolean[N + 1]; // 1 ~ N
        visited[sa] = true;
        visited[sb] = true; // 못가게 해놓기
        int count = 1;
        
        while (!que.isEmpty()){
            Integer poll = que.poll();
            
            for(Integer end:graph.get(poll)){
                if (!visited[end]){
                    visited[end] = true;
                    que.add(end);
                    count++;
                }
            }
        }
        
        return count;
    }
}