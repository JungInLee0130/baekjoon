import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

class Vertex{
    int num;
    ArrayList<Integer> endVertex;
    String color;
    int depth;

    public Vertex() {
    }

    public Vertex(int num, ArrayList<Integer> endVertex, String color, int depth) {
        this.num = num;
        this.endVertex = endVertex;
        this.color = color;
        this.depth = depth;
    }

    public int getNum() {
        return num;
    }

    public String getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            // 무방향 그래프
            // 두번째 시도: 인접 리스트
            ArrayList<Vertex> graphList = new ArrayList<>();
            graphList.add(new Vertex());
            for (int j = 1; j <= V; j++) {
                graphList.add(new Vertex(j, new ArrayList<>(), "none", 20001));
            }
            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                // 무방향 그래프
                graphList.get(start).endVertex.add(end);
                graphList.get(end).endVertex.add(start);
            }

            // BFS로 시도 -> 같은 점 방문하면 NO로

            /*1.전체 vertex 순환 -> none 검사*/
            for (int j = 1; j <= V; j++) {
                if (graphList.get(j).getColor().equals("none")) {
                    BFS(graphList, j, V);
                }
                if (!isBi) {
                    break;
                }
            }


            if (isBi){
                bw.write("YES\n");
            }
            else{
                bw.write("NO\n");
                /*3. isBi 초기화*/
                isBi = true;
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static boolean isBi = true;
    private static void BFS(ArrayList<Vertex> graphList, int start, int V) {
        /*2. queue 지역변수로 -> 테스트케이스 때문*/
        Queue<Vertex> bfsQueue = new LinkedList<>();
        Vertex startVertex = graphList.get(start);
        startVertex.setColor("blue");
        startVertex.setDepth(0);
        bfsQueue.add(startVertex);

        while (!bfsQueue.isEmpty()) {
            Vertex pollVertex = bfsQueue.poll();

            for (int endElement: pollVertex.endVertex) {
                graphList.get(endElement);
                int nextDepth = pollVertex.getDepth() + 1;

                if (graphList.get(endElement).getColor().equals(pollVertex.getColor())) {
                    isBi = false;
                    return;
                }

                /*4. none인것만 queue에 담음*/
                if (graphList.get(endElement).color.equals("none")) {
                    bfsQueue.add(graphList.get(endElement));
                }

                if (nextDepth % 2 != 0) { // 홀수면 red
                    graphList.get(endElement).setColor("red");
                    graphList.get(endElement).setDepth(nextDepth);
                } else { // 짝수면 blue
                    graphList.get(endElement).setColor("blue");
                    graphList.get(endElement).setDepth(nextDepth);
                }
            }
        }
    }
}

/*잘못된거는 없는데,
* 1. none인것만 queue에 담기
* 2. vertex를 돌면서 none인것 확인
* 3. queue는 지역변수로 -> 테스트케이스 때문*/