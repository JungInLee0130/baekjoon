
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static ArrayList<Point> houses;
    static ArrayList<Point> chickens;
    static class Point{
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 맵 그리기
        map = new int[N + 1][N + 1]; // 1,1 부터 시작
        houses = new ArrayList<>();
        chickens = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) { // 치킨집
                    chickens.add(new Point(i, j));
                }
                if (map[i][j] == 1) { // 집
                    houses.add(new Point(i, j));
                }
            }
        }

        chickenLen = chickens.size();
        // 초기화
        min = Integer.MAX_VALUE;
        arr = new int[M];
        dfs(0,0);

        bw.write(String.valueOf(min));

        bw.flush();
        br.close();
        bw.close();
    }

    private static int min;
    private static int[] arr;
    private static int chickenLen;
    private static void dfs(int cnt, int start) {
        if (cnt == M) {
            // 최소 치킨거리를 구함.
            min = Math.min(cal(), min);
            return;
        }

        for (int i = start; i < chickenLen; i++) { // 조합
            arr[cnt] = i;
            dfs(cnt + 1, i + 1);
            //arr[cnt] = 0
        }
    }

    private static int cal() {
        int distance = Integer.MAX_VALUE / 1000;
        int sum = 0;
        for (int i = 0; i < houses.size(); i++) { // house
            distance = Integer.MAX_VALUE / 1000;
            for (int j = 0; j < M; j++) { // chicken
                Point house = houses.get(i);
                Point chicken = chickens.get(arr[j]);

                // M개의 치킨집중 가장작은 distance를 구함
                distance = Math.min(Math.abs(chicken.r - house.r) + Math.abs(chicken.c - house.c), distance);
            }
            // 각각의 집의 치킨거리 출력 : sum에 더함
            sum += distance;
        }
        return sum;
    }

}

/*
 * (1,1) 부터 시작
 * 각 치킨집까지 거리중 가장 짧은쪽이 각 집의 치킨거리임.
 * 그냥 M개를 뽑음.
 * 치킨 거리 구하고, 최솟값 갱신 방향으로
 * */