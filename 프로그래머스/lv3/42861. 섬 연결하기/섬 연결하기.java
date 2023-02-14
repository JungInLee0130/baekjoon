import java.util.*;


class Solution {
    static class Vertex implements Comparable<Vertex>{
        int num;
        int weight;

        public Vertex(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex v) {
            return this.weight - v.weight;
        }
    }
    private static ArrayList<ArrayList<Vertex>> graph;
    private static int max;
    private static boolean[] visited;
    public int solution(int n, int[][] costs) {
        max = 0;
        // costs[N][0] : 시작점
        // costs[N][1] : 끝점
        // costs[N][2] : 가중치

        // 그래프 연결
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) { // n : 간선의 개수
            int start = costs[i][0];
            int end = costs[i][1];
            int weight = costs[i][2];

            graph.get(start).add(new Vertex(end, weight));
            graph.get(end).add(new Vertex(start, weight));
        }


        for (int i = 0; i < n; i++) {
            visited = new boolean[n];

            max = Math.max(dijkstra(n, i), max);
        }

        return max;
    }

    private static int[] minWeight;
    private int dijkstra(int n, int start) {
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        minWeight = new int[n];
        Arrays.fill(minWeight, Integer.MAX_VALUE);
        priorityQueue.add(new Vertex(start, 0));
        minWeight[start] = 0;


        while (!priorityQueue.isEmpty()) {
            Vertex poll = priorityQueue.poll();
            int pollNum = poll.num;
            int pollWeight = poll.weight;


            if (visited[pollNum]) continue;
            visited[pollNum] = true;

            if (allvisited()) {
                return minWeight[pollNum];
            }


            for (Vertex endVertex: graph.get(pollNum)) {
                // 끝점 정보들
                int endNum = endVertex.num;
                int endWeight = endVertex.weight;

                // 끝점 최단거리가 endWeight 보다 크면
                if (minWeight[endNum] > pollWeight + endWeight && !visited[endNum]) {
                    minWeight[endNum] = pollWeight + endWeight;
                    priorityQueue.add(new Vertex(endNum, pollWeight + endWeight));
                }
            }
        }


        return 0;
    }

    private boolean allvisited() {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
}