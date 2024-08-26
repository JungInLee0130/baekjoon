import java.util.*;
class Solution {
    static ArrayList<ArrayList<Vertex>> graph;
    static int[] dist;
    static class Vertex implements Comparable<Vertex>{
        int end;
        int fee;
        
        public Vertex(int end, int fee){
            this.end = end;
            this.fee = fee;
        }
        
        @Override
        public int compareTo(Vertex vertex){
            return Integer.compare(this.fee, vertex.fee);
        }
    }
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        graph = new ArrayList<>();
        
        for (int i = 0; i <= n; i++){
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < fares.length; i++){
            int start = fares[i][0];
            int end = fares[i][1];
            int fee = fares[i][2];
            
            graph.get(start).add(new Vertex(end, fee));
            graph.get(end).add(new Vertex(start, fee));
        }
        
        N = n;
        
        int[] distS = dijk(s);
        
        int[] distA = dijk(a);
        
        int[] distB = dijk(b);
        
        answer = 2_000_001;
        for(int i = 1; i <= N; i++){
            answer = Math.min(distS[i] + distA[i] + distB[i], answer);
        }
        
        
        return answer;
    }
    
    static int N;
    static final int INF = 20_000_001;
    private static int[] dijk(int start){
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.add(new Vertex(start, 0));
        
        while (!pq.isEmpty()){
            Vertex curVertex = pq.poll();
            
            int curNum = curVertex.end;
            int curFee = curVertex.fee;
                        
            for (Vertex endVertex : graph.get(curNum)){
                int eend = endVertex.end;
                int endFee = endVertex.fee;
                
                if (dist[eend] > dist[curNum] + endFee){
                    dist[eend] = dist[curNum] + endFee;
                    pq.add(new Vertex(eend, dist[curNum] + endFee));
                }
            }
        }
        return dist;
    }
}