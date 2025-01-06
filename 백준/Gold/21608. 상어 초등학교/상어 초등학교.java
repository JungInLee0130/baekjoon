import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {
    static int N;
    static int[][] map;
    static int len;
    static LinkedHashMap<Integer, int[]> hashmap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 자리 배치
        // 좋아하는 학생이 가장많은 인접한곳.
        // 인접한 곳중에 가장 많이 빈곳.
        // 행이 가장 작은 곳.
        // 열이 가장 작은 곳.

        // 2. 만족도 구하기
        // 인접한 칸에 앉아있는 좋아하는 학생 수 : 0, 1, 10, 100, 1000까지 상승 (0,1,2,3,4)
        // 만족도의 총 합 구하기.

        N = Integer.parseInt(br.readLine());

        len = (int) Math.pow(N, 2);

        map = new int[N][N];

        hashmap = new LinkedHashMap<>();

        for (int i = 1; i <= len; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()); // num

            int[] likes = new int[4];
            for (int j = 0; j < 4; j++) {
                likes[j] = Integer.parseInt(st.nextToken());
            }

            hashmap.put(num, likes);
        }

        // 1. 자리 정하기
        setLocation();
        sumSatisfactions();

        System.out.println(answer);


        br.close();
    }

    static int answer = 0;

    private static void sumSatisfactions() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if (!isRange(nx,ny)) continue;

                    for (int num : hashmap.get(map[i][j])) {
                        if (num == map[nx][ny]){
                            cnt++;
                        }
                    }
                }

                if (cnt >= 1) {
                    answer += Math.pow(10, cnt - 1);
                }
            }
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    private static void setLocation() {
        int cnt = 0;
        for (Map.Entry<Integer, int[]> entry:hashmap.entrySet()) {
            int num = entry.getKey();
            int[] values = entry.getValue();

            if (cnt == 0){
                map[1][1] = num;
            }

            else{
                int[][] likes = new int[N][N];

                // 1. 좋아하는 사람이 가장 많은 지점
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (map[i][j] != 0) {
                            plusLikesIfEquals(likes, values, i, j);
                        }
                    }
                }

                // maxlikes 구하기
                int maxLikes = 0;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        maxLikes = Math.max(maxLikes, likes[i][j]);
                    }
                }


                // maxPoint 넣기
                List<Point> maxPoints = new ArrayList<>();
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (likes[i][j] == maxLikes && map[i][j] == 0) { // 넣을수있는 곳이여야함.
                            maxPoints.add(new Point(i, j)); // maxLikes 지점 add
                        }
                    }
                }

                // 칸이 여러개일 경우, 비어있는 칸이 많은 곳
                for (Point point :maxPoints) {
                    for (int d = 0; d < 4; d++) {
                        int nx = point.x + dx[d];
                        int ny = point.y + dy[d];

                        if (!isRange(nx,ny)) continue;

                        if (map[nx][ny] == 0) point.emptyPlaceCount++;
                    }
                }


                Collections.sort(maxPoints, new Comparator<Point>() {
                    @Override
                    public int compare(Point o1, Point o2) {
                        if (Integer.compare(o2.emptyPlaceCount, o1.emptyPlaceCount) == 0){
                            if (Integer.compare(o1.x, o2.x) == 0){
                                return Integer.compare(o1.y, o2.y); // 열이 작은 순
                            }
                            return Integer.compare(o1.x, o2.x); // 행이 작은순
                        }
                        return Integer.compare(o2.emptyPlaceCount, o1.emptyPlaceCount); // 빈 공간이 많은 순
                    }
                });

                Point point = maxPoints.get(0);
                map[point.x][point.y] = num;
            }

            cnt++;
        }
    }

    static class Point{
        int x;
        int y;

        int emptyPlaceCount;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void plusLikesIfEquals(int[][] likes, int[] values, int x, int y) {
        for (int e : values) {
            if (map[x][y] == e) { // 일치하면 사방에 likes++
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (!isRange(nx,ny)) continue;
                    if (map[nx][ny] > 0) continue; // 이미 차있음.

                    likes[nx][ny]++;
                }
            }
        }
    }

    private static boolean isRange(int nx, int ny) {
        return 0 <= nx && nx <= N - 1 && 0 <= ny && ny <= N - 1;
    }
}