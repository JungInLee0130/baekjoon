import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }


        // 1인곳 -> 0으로 바꾼후 bfs -> 다시 원위치 -> 시간초과
        // 해결법 : 공간을 그룹화한다.
        group = new int[N][M];
        hashMap = new HashMap<>();// bfs로 그룹 찾아줘야하는듯
        int index = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // group을 한번칠하고 0이아닌것은 다시 반복할 필요없음.
                if (map[i][j] == 0 && group[i][j] == 0) {
                    hashMap.put(index, bfs(i, j, index)); // index, count 저장, group 표시
                    index++;
                }
            }
        }

        // 그룹 완료
        // 경우의 수 카운트
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 벽이 있는 곳에 한에서, 상하좌우의 구역의 개수 파악후, 해쉬맵에 있는 값을 넣어주면됨.
                sb.append(mapCount(i, j)); // 플러스 자기자신
            }
            sb.append("\n");
        }

        System.out.println(sb);
        bw.flush();
        bw.close();
        br.close();
    }

    static HashMap<Integer,Integer> hashMap;
    private static int mapCount(int x, int y) {
        int count = 1;
        HashSet<Integer> hashSet = new HashSet<>();

        if (map[x][y] == 0) return 0;
        // 사방 탐색
        for (int d = 0; d < 4; d++) {
            int nx = x + dr[d];
            int ny = y + dc[d];

            if (!check(nx,ny)) continue;
            if (map[nx][ny] == 1 || group[nx][ny] == 0) continue;

            // 근데, 중복된것일수도있음.
            hashSet.add(group[nx][ny]);
        }

        for (Integer e : hashSet) {
            count += hashMap.get(e);
        }

        return count % 10;
    }

    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private static int bfs(int x, int y, int index) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        group[x][y] = index; // 구역 표시

        int count = 1;

        while (!queue.isEmpty()) {
            Point poll = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nx = poll.x + dr[d];
                int ny = poll.y + dc[d];

                if (!check(nx,ny)) continue;
                if (map[nx][ny] == 1 || group[nx][ny] != 0) continue;
                
                queue.add(new Point(nx, ny));
                group[nx][ny] = index;
                count++;
            }
        }
        return count;
    }

    static int[][] group;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};


    private static boolean check(int nx, int ny) {
        return 0 <= nx && nx <= N - 1 && 0 <= ny && ny <= M - 1;
    }
}