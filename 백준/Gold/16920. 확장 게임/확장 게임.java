import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static int N, M, P;
    static char[][] map;
    static int[] p;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        st = new StringTokenizer(br.readLine());

        p = new int[P + 1];
        for (int i = 1; i <= P; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }

        startList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                if ('1'<= map[i][j] && map[i][j] <= '9') {
                    startList.add(new Point(map[i][j] - '0', i, j, 0));
                }
            }
        }

        // 순서대로 p만큼 확장.
        bfs();

        int[] answer = new int[P + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if ('1' <= map[i][j] && map[i][j] <= '9') {
                    answer[map[i][j] - '0']++;
                }
            }
        }

        for (int i = 1; i <= P; i++) {
            System.out.print(answer[i] + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static final int TOTAL_MAX = 2000;
    static boolean[][] visited;
    private static void bfs() {
        Queue<Point> que = new LinkedList<>();
        visited = new boolean[N][M];
        Collections.sort(startList);

        // 처음부터 다 놓는게 아니라 같은수만 골라낸다.
        for (Point start:startList) {
            visited[start.x][start.y] = true;
            que.add(start);
        }

        // 같은 수는 동시에 진행시켜야한다.
        while (!que.isEmpty()) {
            Queue<Point> nq = new LinkedList<>();
            Point curPoll = que.poll();
            nq.add(curPoll); // num
            // que에서 같은것만 뽑아서 넣어줌.

            while (!que.isEmpty() && que.peek().num == curPoll.num) {
                Point samePoll = que.poll();
                nq.add(samePoll);
            }

            while (!nq.isEmpty()) {
                Point poll = nq.poll();

                int step = p[poll.num]; // 가로 세로 합해서 step 칸 이동가능

                for (int d = 0; d < 4; d++) {
                    int nx = poll.x + dx[d];
                    int ny = poll.y + dy[d];

                    if (isRange(nx, ny) && map[nx][ny] == '.' && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        map[nx][ny] = (char) (poll.num + '0');

                        // 다음 step이 들어가면 안됨.
                        if (poll.step + 1 < step) {
                            nq.add(new Point(poll.num, nx, ny, poll.step + 1));
                        } else {
                            que.add(new Point(poll.num, nx, ny, 0));
                        }
                    }
                }
            }
        }
    }

    private static boolean isRange(int nx, int ny) {
        return 0 <= nx && nx <= N - 1 && 0 <= ny && ny <= M - 1;
    }

    static class Point implements Comparable<Point>{
        int num; // castle num
        int x;
        int y;

        int step;

        public Point(int num, int x, int y, int step) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.step = step;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.num, o.num);
        }
    }
    static List<Point> startList;
}