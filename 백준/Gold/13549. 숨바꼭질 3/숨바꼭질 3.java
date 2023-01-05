
import java.io.*;
import java.util.*;

class Vertex implements Comparable<Vertex> {
    int num;
    int weight; // time

    public Vertex(int num, int weight) {
        this.num = num;
        this.weight = weight;
    }

    public int getNum() {
        return num;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Vertex o) {
        return this.weight - o.weight;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Vertex vertex = BFS(N, K);

        bw.write(String.valueOf(vertex.getWeight() + "\n"));

        bw.flush();
        br.close();
        bw.close();
    }

    // 최소시간 : priorityQueue
    private static Vertex BFS(int N, int K) {
        boolean[] visited = new boolean[200001]; // 1 ~ 200000
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Vertex(N, 0));

        while (!priorityQueue.isEmpty()) {
            Vertex poll = priorityQueue.poll();
            int pollNum = poll.getNum();
            int pollWeight = poll.getWeight();

            if (pollNum == K) {
                return poll;
            }

            if (visited[pollNum]) {
                continue;
            }
            visited[pollNum] = true;

            Vertex[] calculatePoll = new Vertex[3];
            calculatePoll[0] = new Vertex(pollNum + 1, pollWeight + 1); // + 1
            calculatePoll[1] = new Vertex(pollNum - 1, pollWeight + 1); // + 1
            calculatePoll[2] = new Vertex(pollNum * 2, pollWeight); // + 0

            // K와 같은지 체크
            // 주어진범위 내, 미방문
            for (int i = 0; i < 3; i++) {
                if (0 <= calculatePoll[i].getNum() && calculatePoll[i].getNum() <= 100000
                        && !visited[calculatePoll[i].getNum()]) {
                    priorityQueue.add(calculatePoll[i]);
                }
            }
        }
        return new Vertex(N, 0);
    }
}
