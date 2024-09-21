import java.util.*;
class Solution {
    // 그래프 그려도될꺼같은데
    static List<List<Integer>> graph;
    static int N;
    public int solution(int[] info, int[][] edges) {        
        N = info.length;

        // 1. 이진 트리 완성
        // 그래프로 대신 형성
        graph = new ArrayList<>();
        for (int i = 0; i < N; i++){
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < edges.length; i++){
            int parentNum = edges[i][0];
            int num = edges[i][1];
            
            graph.get(parentNum).add(num);
        }        
        // 양 > 늑대 유지하며 탐색
        List<Integer> next = new ArrayList<>();
        dfs(info, next, 0, 0, 0);          
        
        return max;
    }
    
    static int max = 0;
    private static void dfs(int[] info, List<Integer> list, int start, int sheepCount, int wolfCount){
        if (info[start] == 0){
            sheepCount++;
        }
        else{
            wolfCount++;
        }
        // 늑대의 수가 양보다 크거나 같아지면 : return
        if (sheepCount <= wolfCount){
            return;
        }
        
        // 양의 수가 더 크면 그대로 진행
        max = Math.max(max, sheepCount);
        
        List<Integer> next = new ArrayList<>(list);
        if (!graph.get(start).isEmpty()){
            next.addAll(graph.get(start));
        }
        next.remove(Integer.valueOf(start));
        
        for(int n : next){
            dfs(info, next, n, sheepCount, wolfCount);
        }
    }
}