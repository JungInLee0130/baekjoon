import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {
    static int R, C, M;
    static class Shark{
        int x;
        int y;
        int s;
        int d;
        int z;

        public Shark(int x, int y, int s, int d, int z) {
            this.x = x;
            this.y = y;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Shark shark = (Shark) o;
            return x == shark.x && y == shark.y && s == shark.s && d == shark.d && z == shark.z;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, s, d, z);
        }
    }

    static List<Shark> sharks = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());

            // !. 방향전환: 쉽게 변경해줌.
            if (d == 1){
                d = 2;
            }
            else if (d == 2){
                d = 1;
            }

            Shark shark = new Shark(r, c, s, d, z);

            sharks.add(shark);
        }

        map = new ArrayList[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = new ArrayList<>();
            }
        }


        solve();

        System.out.println(totalSize);

        br.close();
    }

    static List<Shark>[][] map;
    static int totalSize = 0;
    private static void solve() {
        init();

        // 1. 낚시꾼의 이동
        for (int c = 0; c < C; c++) {
            // 2.가장 가까운 상어를 잡는다.
            // !.행순회
            for (int r = 0; r < R; r++) {
                if (map[r][c].size() != 0) {
                    Shark shark = map[r][c].get(0);

                    totalSize += shark.z;

                    map[r][c].remove(shark);
                    sharks.remove(shark);
                    break; // !.1번만 잡는다.
                }
            }
            // 3. 상어가 이동한다.
            moveShark();
            // 3-1. 상어가 두마리있는경우는 한마리로 만들어준다.
            huntSharks();
        }
    }

    private static void huntSharks() {
        // 두마리있는곳 순회
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j].size() >= 2) {
                    List<Shark> sharkList = map[i][j];

                    Shark maxShark = sharkList.get(0);
                    for (int k = 1; k < sharkList.size(); k++) {
                        Shark shark = sharkList.get(k);
                        if (shark.z > maxShark.z) {
                            maxShark = shark;
                        }
                    }

                    for (int k = sharkList.size() - 1; k >= 0; k--) {
                        Shark shark = sharkList.get(k);

                        if (maxShark.equals(shark)) continue;

                        sharks.remove(shark); // !.원본에서 삭제해야지.... 멍청아...ㅠㅠ
                        map[i][j].remove(shark);
                    }
                }
            }
        }
    }

    // 위 아래 오른쪽 왼쪽
    // !. 방향전환
    // 위, 오른쪽, 아래, 왼쪽으로 변경
    // 이렇게 변경하면 d` = (d + 2) % 4 이렇게 변경가능
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    private static void moveShark() {
        List<Shark>[][] copyMap = new ArrayList[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                copyMap[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < sharks.size(); i++) {
            Shark shark = sharks.get(i);

            int x = shark.x;
            int y = shark.y;
            int s = shark.s;
            int d = shark.d;
            int z = shark.z;

            int rs = s;

            // !. 생각했던 원래 상태 그대로인것 기준으로 %
            if (d == 0 || d == 2) {
                rs = s % ((R - 1) * 2);
            }
            else if (d == 1 || d == 3){
                rs = s % ((C - 1) * 2);
            }

            int[] point = adjustAll(x, y, d, rs);
            int rx = point[0];
            int ry = point[1];
            int rd = point[2];

            Shark nShark = new Shark(rx, ry, s, rd, z);

            map[x][y].remove(shark);

            sharks.set(i, nShark);
            copyMap[rx][ry].add(nShark);
        }

        map = copyMap;
    }

    // !. 속력에 나머지, 나머지는 불규칙하므로 반복문으로
    private static int[] adjustAll(int x, int y, int d, int rs) {
        for (int s = 0; s < rs; s++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (willOut(nx, ny)) {
                // 1. 값을 원래대로 해줌.
                x -= dx[d]; // 방향 반대로해서 이동시킴.
                y -= dy[d];
                // 2. 방향은 반대로 바꿔줌.
                d = (d + 2) % 4;
                continue;
            }

            x = nx;
            y = ny;
        }

        return new int[]{x, y, d};
    }

    // 앞으로 이동할 좌표가 범위를 넘어서는지
    private static boolean willOut(int nx, int ny) {
        return 0 > nx || nx > R - 1 || 0 > ny || ny > C - 1;
    }

    private static void init() {
        for (int i = 0; i < M; i++) {
            Shark shark = sharks.get(i);

            int x = shark.x;
            int y = shark.y;

            map[x][y].add(shark);
        }
    }
}