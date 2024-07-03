import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static class Marble{
        int rx;
        int ry;

        int bx;
        int by;

        int cnt;

        ArrayList<Integer> route;

        public Marble(int rx, int ry, int bx, int by, int cnt, ArrayList<Integer> route) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
            this.route = route;
        }
    }
    static Marble redMarble;
    static Marble blueMarble;
    static Point exit;

    static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R + 1][C + 1];
        for (int i = 1; i <= R; i++) {
            String str = br.readLine();
            for (int j = 1; j <= C; j++) {
                map[i][j] = str.charAt(j - 1);

                if (map[i][j] == 'O') { // exit
                    exit = new Point(i, j);
                } else if (map[i][j] == 'R') {
                    // 1. 이렇게 red, blue 둘다 저장하면됨.
                    redMarble = new Marble(i, j, 0, 0, 0, null);
                } else if (map[i][j] == 'B'){
                    blueMarble = new Marble(0, 0, i, j, 0, null);
                }
            }
        }

        marbleMove();

        if (result == -1) {
            System.out.println(result);
        }
        else{
            System.out.println(result);
            System.out.println(sb);
        }

        bw.flush();
        br.close();
        bw.close();
    }

    static int result = 0;
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1}; // U,D,R,L
    private static void marbleMove() {
        // 문제 : 각각의 경우의수에따라 다른 맵을 가짐.
        Queue<Marble> que = new LinkedList<>();
        // 2. 4차원 배열
        boolean[][][][] visited = new boolean[R + 1][C + 1][R + 1][C + 1];
        que.add(new Marble(redMarble.rx, redMarble.ry, blueMarble.bx, blueMarble.by, 0, new ArrayList<>()));
        visited[redMarble.rx][redMarble.ry][blueMarble.bx][blueMarble.by] = true;

        // 한큐에 같이 넣어야할듯.
        while (!que.isEmpty()) {
            Marble poll = que.poll();

            int curRx = poll.rx;
            int curRy = poll.ry;
            int curBx = poll.bx;
            int curBy = poll.by;

            // 10번 이상이면 -1 return
            if (poll.cnt == 10) {
                break;
            }

            // 파란구슬
            // 3. 같은 방향으로 이동하니까 d반복문 한번에 묶어야함.
            for (int d = 0; d < 4; d++) {
                boolean isFinished = false;

                // willgo
                int nbx = curBx;
                int nby = curBy;
                int nrx = curRx;
                int nry = curRy;

                // 파란놈 이동
                while (map[nbx + dx[d]][nby + dy[d]] != '#') {
                    nbx += dx[d];
                    nby += dy[d];

                    // 출구 들어가면 제외
                    if (map[nbx][nby] == 'O') {
                        break;
                    }
                }

                // 빨간놈 이동
                while (map[nrx + dx[d]][nry + dy[d]] != '#') {
                    nrx += dx[d];
                    nry += dy[d];
                    // O면 성공
                    if (map[nrx][nry] == 'O') {
                        isFinished = true;
                        break;
                    }
                }

                // 파란 공이 출구에 들어가버림 (좌표 바꾸면안됨) : 파란공 + 빨간공 포함
                if (map[nbx][nby] == 'O') {
                    continue;
                }

                // 빨간공 들어가서 끝났음. : 빨간공만 들어감
                if (isFinished) {
                    result = poll.cnt + 1;
                    poll.route.add(d);
                    for (Integer e:poll.route) {
                        if (e == 0) {
                            sb.append("U");
                        }
                        if (e == 1) {
                            sb.append("D");
                        }
                        if (e == 2) {
                            sb.append("R");
                        }
                        if (e == 3) {
                            sb.append("L");
                        }
                    }
                    return;
                }

                // 둘이 겹치면 현재 좌표 기준으로 더
                if (nbx == nrx && nby == nry) {
                    if (d == 0) { // U
                        if (curRx < curBx) { // 빨간색이 더 위에있음
                            nbx = nbx + 1;
                        }
                        else{ // 같은경우는 없음.
                            nrx = nrx + 1;
                        }
                    } else if (d == 1) { // D
                        if (curRx < curBx) {
                            nrx = nrx - 1;
                        }
                        else {
                            nbx = nbx - 1;
                        }
                    } else if (d == 2) { // R
                        if (curRy < curBy) {
                            nry = nry - 1;
                        }
                        else{
                            nby = nby - 1;
                        }
                    } else { // L
                        if (curRy < curBy) {
                            nby = nby + 1;
                        }
                        else{
                            nry = nry + 1;
                        }
                    }
                }


                // 이미 방문했음, 이동 안했으면 그대로 유지
                if (visited[nrx][nry][nbx][nby]) {
                    continue;
                }

                // 이동 했음
                visited[nrx][nry][nbx][nby] = true;
                Marble nMarble = new Marble(nrx, nry, nbx, nby, poll.cnt + 1, new ArrayList<>());
                if (!poll.route.isEmpty()) {
                    copyArray(nMarble.route, poll.route);
                }
                nMarble.route.add(d);
                que.add(nMarble);

            }
        }

        result = -1;
    }

    private static void copyArray(ArrayList<Integer> newArray, ArrayList<Integer> original) {
        for (Integer e:original) {
            newArray.add(e);
        }
    }
}