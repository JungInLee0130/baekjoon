import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static class Point{
        int x;
        int y;

        int num;

        public Point(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
    static class Bridge{
        int start;
        int end;
        int cost;

        public Bridge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1. 구역 나누기 : flood fill
        int cnt = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 1이면 색칠
                if (map[i][j] == 1) {
                    map[i][j] = ++cnt;
                    bfs(i, j, cnt);
                }
            }
        }

        islandNum = cnt - 1;

        /*// 출력
        System.out.println();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();*/

        // 2. 다리 연결 : row column 중 걸리는거있는지, 모두 다리는 1로 표시
        bridges = new ArrayList<>();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] >= 2) { // 섬을 만나면
                    /*up(r,c);*/
                    down(r,c); // 아래
                    /*left(r,c);*/
                    right(r,c); // 오른쪽

                    /*// 출력
                    System.out.println();
                    for (Bridge b: bridges) {
                        System.out.println(b.start + " " + b.end + " " + b.cost);
                    }
                    System.out.println();*/
                }
            }
        }

        // 거리대로 오름차순 정렬
        Collections.sort(bridges, new Comparator<Bridge>() {
            @Override
            public int compare(Bridge o1, Bridge o2) {
                return Integer.compare(o1.cost, o2.cost);
            }
        });


        // 유니온 파인드
        makeSet();
        for (int i = 0; i < bridges.size(); i++) {
            Bridge bridge = bridges.get(i);
            int from = bridge.start;
            int to = bridge.end;
            int cost = bridge.cost;

            int fromParent = find(from);
            int toParent = find(to);

            /*// 랭크 큰거를 대입
            if (rank[fromParent] > rank[toParent]) {
                parent[toParent] = fromParent;
            } else {
                parent[fromParent] = toParent;
                if (rank[fromParent] == rank[toParent]) { // 같을때는 끝에꺼++
                    rank[toParent]++;
                }
            }
            */
            union(fromParent, toParent, cost);
        }


        //2 ~

        if (isConnected()) {
            bw.write(String.valueOf(totalCost));
        } else {
            bw.write(String.valueOf(-1));
        }


        bw.flush();
        br.close();
        bw.close();
    }

    private static boolean isConnected() {
        int temp = find(2);
        for (int i = 3; i < 2 + islandNum; i++) {
            if (temp == find(i)){
                continue;
            }
            return false;
        }
        return true;
    }

    static int totalCost;
    static int islandNum;

    private static void union(int fromParent, int toParent, int cost) {
        if (fromParent == toParent) return;
        // 다르면
        int max = Math.max(fromParent, toParent);
        int min = Math.min(fromParent, toParent);

        // cost 선택
        totalCost += cost;

        parent[max] = min;
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        else return parent[x] = find(parent[x]);
    }

    private static void makeSet() {
        parent = new int[2 + islandNum];
        rank = new int[2 + islandNum];

        for (int i = 2; i < 2 + islandNum; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    static int[] parent;
    static int[] rank;

    private static void up(int r, int c) { // 세로
        int start = map[r][c];
        int end = -1;
        int count = 0;
        for (int i = r - 1; i >= 0; i--) { // 세로
            // 0 이면 채워나가면서 count++
            if (map[i][c] == 0) {
                count++;
            }
            // 0은 아님. 다른 섬 만남
            else if (map[i][c] != start) { // 섬의 색이고, 처음이랑 같지않은색
                end = map[i][c]; // i,c
                break;
            }
        }
        // 2이상, 끝이 -1이 아니여야함.
        if (count >= 2 && end != -1){
            // 브릿지 정보 저장.
            bridges.add(new Bridge(start, end, count));
        }
    }
    private static void down(int r, int c) { // 세로
        int start = map[r][c];
        int end = -1;
        int count = 0;
        for (int i = r + 1; i < N; i++) { // 세로
            // 0 이면 채워나가면서 count++
            if (map[i][c] == 0) {
                count++;
            }
            else if (map[i][c] == start){ // 중간에 만나는 경우
                count = 0;
            }
            else if (map[i][c] != start) { // 섬의 색이고, 처음이랑 같지않은색
                end = map[i][c]; // i,c
                break;
            }
        }
        // 2이상, 자기자신 x, 바꾸지않은경우 x
        if (count >= 2 && end != start && end != -1){
            // 브릿지 정보 저장.
            bridges.add(new Bridge(start, end, count));
        }
    }

    private static void right(int r, int c) { // 가로
        int start = map[r][c];
        int end = -1;
        int count = 0;
        for (int i = c + 1; i < M; i++) {
            if (map[r][i] == 0) { // 0이면
                count++;
            }
            else if (map[r][i] == start){ // 중간에 만나는 경우
                count = 0;
            }
            // start와 색이 다르면 break;
            else if (map[r][i] != start) {
                end = map[r][i]; // r,i
                break;
            }
        }
        if (count >= 2 && end != start && end != -1){
            // start, end, count -> Bridge로 저장
            bridges.add(new Bridge(start, end, count));
        }
    }
    private static void left(int r, int c) { // 가로
        int start = map[r][c];
        int end = -1;
        int count = 0;
        for (int i = c - 1; i >= 0; i--) {
            if (map[r][i] == 0) { // 0이면
                count++;
            }
            // start와 색이 다르면 break;
            else if (map[r][i] != start) {
                end = map[r][i]; // r,i
                break;
            }
        }
        if (count >= 2 && end != -1) {
            // start, end, count -> Bridge로 저장
            bridges.add(new Bridge(start, end, count));
        }
    }
    private static ArrayList<Bridge> bridges;
    private static boolean[][] visited;

    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};
    private static void bfs(int r, int c, int cnt) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(r, c, cnt));
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            Point poll = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = poll.x + dr[d];
                int nc = poll.y + dc[d];

                // 범위에 속하지않으면 continue;
                if (!check(nr,nc)) continue;
                // 중복방문했으면
                if (visited[nr][nc]) continue;
                // 1이면 색칠
                if (map[nr][nc] == 1) {
                    map[nr][nc] = cnt;
                    visited[nr][nc] = true;
                    queue.add(new Point(nr, nc, cnt));
                }
            }
        }

    }

    private static boolean check(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}

/* NxM 땅 또는 바다
다리 건설: 모든 섬을 연결하는 다리 길이의 최솟값
*
* */
/*1. flood fill
 *2. 최소거리
 * 3. 크루스칼
 * */