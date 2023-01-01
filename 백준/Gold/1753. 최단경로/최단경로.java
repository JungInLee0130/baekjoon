import java.io.*;
import java.util.*;

class Vertex1 implements Comparable<Vertex1>{
    int end;
    int weight;

    public Vertex1(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    public int getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }

    // 가중치 최솟값 정렬 -> 사실 별의미없는듯 -> ㄴㄴ 없으면 안되는듯 : priorityQueue 정렬을 못함.
    @Override
    public int compareTo(Vertex1 o) {
        return this.weight - o.weight;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        disk = new int[V + 1];
        Arrays.fill(disk, -1);
        
        ArrayList<ArrayList<Vertex1>> graph = new ArrayList<>();
        graph.add(new ArrayList<>());
        for (int i = 1; i <= V; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            // 방향그래프
            graph.get(start).add(new Vertex1(end, weight));
        }

        BFSArrayList(graph, K);

        for (int i = 1; i <= V; i++) {
            if (i == K) { // 시작점의 최단거리 : 0
                bw.write(String.valueOf(0) + "\n");
            }
            else {
                if (disk[i] == -1) { // -1 : 끝까지 수정안됨 -> INF
                    bw.write("INF\n");
                } else { // 수정됨 : 최단거리 출력
                    bw.write(String.valueOf(disk[i]) + "\n");
                }
            }
        }


        bw.flush();
        br.close();
        bw.close();
    }
    private static int[] disk; // 최단경로 채우기
    private static void BFSArrayList(ArrayList<ArrayList<Vertex1>> graph, int K) {
        // Queue -> PriorityQueue
        PriorityQueue<Vertex1> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Vertex1(K, 0));

        while (!priorityQueue.isEmpty()) {
            Vertex1 poll = priorityQueue.poll();
            int pollNum = poll.getEnd(); // 시작점 추출
            int pollWeight = poll.getWeight();
            ArrayList<Vertex1> endVertexArrayList = graph.get(pollNum);
            if (endVertexArrayList.isEmpty()) {

            } else {
                for (Vertex1 endVertex : endVertexArrayList) {
                    int endVertexNum = endVertex.getEnd();
                    int endVertexWeight = endVertex.getWeight();
                    if (disk[endVertexNum] == -1) {
                        disk[endVertexNum] = pollWeight + endVertexWeight;
                        priorityQueue.add(new Vertex1(endVertexNum, disk[endVertexNum]));
                    } else { // 최단경로 존재
                        if (disk[endVertexNum] <= pollWeight + endVertexWeight) {
                            // 그냥 continue;
                        } else {
                            // 갱신
                            disk[endVertexNum] = pollWeight + endVertexWeight;
                            priorityQueue.add(new Vertex1(endVertexNum, disk[endVertexNum]));
                        }
                    }
                    
                }
            }
        }
    }
}


