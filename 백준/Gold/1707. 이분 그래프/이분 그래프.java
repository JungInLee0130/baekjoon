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
    private static boolean[] visited;
    private static String[] color;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            // 무방향 그래프
            visited = new boolean[V + 1];
            color = new String[V + 1];
            Arrays.fill(color, "none");
            Arrays.fill(visited, false);

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

             for (int j = 1; j <= V; j++) {
                if (graphList.get(j).getColor().equals("none")) {
                    BFS(graphList, j,0,V,E);
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
                isBi = true;
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static boolean isBi = true;
    private static void BFS(ArrayList<Vertex> graphList, int start, int depth, int V, int E) {
        Queue<Vertex> bfsQueue = new LinkedList<>();
        Vertex startVertex = graphList.get(start);
        startVertex.setColor("blue");
        startVertex.setDepth(0);
        bfsQueue.add(startVertex);

        while (!bfsQueue.isEmpty()) {
            Vertex pollVertex = bfsQueue.poll();

            for (int end: pollVertex.endVertex) {
                
                if (graphList.get(end).getColor().equals(pollVertex.getColor())) {
                    isBi = false;
                    return;
                }
                
                
                if (graphList.get(end).getColor().equals("none")) {
                    bfsQueue.add(graphList.get(end));
                }


                if (pollVertex.getColor().equals("red") && graphList.get(end).getColor().equals("none")) {
                    graphList.get(end).setColor("blue");
                } else if (pollVertex.getColor().equals("blue") && graphList.get(end).getColor().equals("none")){
                    graphList.get(end).setColor("red");
                }
            }
        }
    }
}
