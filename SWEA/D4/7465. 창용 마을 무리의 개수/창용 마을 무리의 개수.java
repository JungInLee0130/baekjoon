import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution {
    static int N, M;
    static int T;
    static int[][] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /*몇개의 무리 존재?
        *1. 사람의 수 N, 알고있는 사람의 관계수 M
        * 2. M개의 줄 : 서로 알고있는 사람의 번호
        * 
        * 출력 : 무리의 개수
        * */

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            list = new int[M][2];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());

                list[i][0] = start;
                list[i][1] = end;
            }


            // 유니온 파인드
            makeSet();
            union();


            // 한번더 부모 연결해주고
            for (int i = 1; i < N + 1; i++) {
                parent[i] = find(i);
            }

            HashSet<Integer> hashSet = new HashSet<>();
            for (int i = 1; i < N + 1; i++) {
                hashSet.add(parent[i]);
            }

            bw.write(String.valueOf("#" + t + " " + hashSet.size()) + "\n");

        }


        bw.flush();
        br.close();
        bw.close();
    }

    private static void union() { // 퀵 유니온 파인드
        for (int i = 0; i < M; i++) {
            int from = list[i][0];
            int to = list[i][1];

            int fromParent = find(from);
            int toParent = find(to);

            // fromParent가 rank가 크면 : 작은쪽을 큰쪽으로
            if (rank[fromParent] > rank[toParent]) {
                parent[toParent] = fromParent;
            }
            else{
                parent[fromParent] = toParent;
                if (rank[fromParent] == rank[toParent]) {
                    rank[toParent]++;
                }
            }
        }
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        else return parent[x] = find(parent[x]);
    }

    private static int[] parent;
    private static int[] rank;
    private static void makeSet() {
        parent = new int[N + 1];
        rank = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
}