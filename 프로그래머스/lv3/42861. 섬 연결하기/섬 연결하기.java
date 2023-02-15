import java.util.*;


class Solution {
    static int total;
    static ArrayList<ArrayList<Vertex>> graph;
    
    static class Vertex implements Comparable<Vertex>{
        int num;
        int weight;

        public Vertex(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.weight - o.weight;
        }
    }
   
    public int solution(int n, int[][] costs) {
          graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge: costs) {
            int from = edge[0];
            int to = edge[1];
            int cost = edge[2];

            graph.get(from).add(new Vertex(to, cost));
            graph.get(to).add(new Vertex(from, cost));
        }

        prim(0, n);
        return total;
    }
    private void prim(int start, int n) {
        boolean[] visited = new boolean[n];

        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.add(new Vertex(start, 0));

        while (!pq.isEmpty()) {
            Vertex poll = pq.poll();
            int pollNum = poll.num;
            int pollWeight = poll.weight;

            if (visited[pollNum]) continue;
            visited[pollNum] = true;

            total += pollWeight;

            for (Vertex endVertex : graph.get(pollNum)) {
                if (visited[endVertex.num]) continue;
                pq.add(endVertex);
            }
        }
    }
    
}