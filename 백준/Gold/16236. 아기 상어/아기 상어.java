import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static class Shark{
        int x;
        int y;
        int weight;
        int second;
        int cnt;
        public Shark(int x, int y, int weight, int second, int cnt) {
            this.x = x;
            this.y = y;
            this.weight = weight;
            this.second = second;
            this.cnt = cnt;
        }
    }
    static class Fish implements Comparable<Fish>{
        int x;
        int y;
        int weight;
        int distance;

        public Fish() {

        }

        public Fish(int x, int y, int weight, int distance) {
            this.x = x;
            this.y = y;
            this.weight = weight;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            Fish fish = (Fish) o;
            return x == fish.x && y == fish.y && weight == fish.weight;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, weight, distance);
        }

        @Override
        public int compareTo(Fish o) {
            if (this.distance == o.distance) {
                if (this.x == o.x) {
                    return this.y - o.y;
                }
                return this.x - o.x;
            }
            return this.distance - o.distance;
        }
    }
    private static Shark shark;
    private static ArrayList<Fish> fishList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        fishList = new ArrayList<>();
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    map[i][j] = 0;
                    shark = new Shark(i, j, 2, 0, 0);
                }
                if (map[i][j] >= 1 && map[i][j] <= 6) {
                    fishList.add(new Fish(i, j, map[i][j], 0));
                }
            }
        }

        while (!fishList.isEmpty()) {
            fishPriorityQueue = new PriorityQueue<>();
            // 최소 몸무게를 추출
            for (int i = 0; i < fishList.size(); i++) {
                // 상어의 몸무게가 더 커야함.
                if (shark.weight > fishList.get(i).weight) {
                    bfs(shark, fishList.get(i));
                }
            }

            if (fishPriorityQueue.isEmpty()) {
                break;
            }

            minWeightFish = fishPriorityQueue.poll();

            for (int i = 0; i < fishList.size(); i++) {
                if (minWeightFish.equals(fishList.get(i))) {
                    fishList.remove(minWeightFish);
                }
            }
            /*System.out.println();
            for (Fish fish: fishList) {
                System.out.print(fish.weight + " ");
            }
            System.out.println();*/

            shark.x = minWeightFish.x;
            shark.y = minWeightFish.y;
            map[minWeightFish.x][minWeightFish.y] = 0;
            shark.cnt++;
            // 몸무게 증가
            if (shark.cnt == shark.weight) {
                shark.cnt = 0;
                shark.weight++;
            }
            shark.second += minWeightFish.distance;

            /*System.out.println();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();*/
        }

        bw.write(String.valueOf(shark.second));




        bw.flush();
        br.close();
        bw.close();
    }

    private static PriorityQueue<Fish> fishPriorityQueue;
    private static Fish minWeightFish;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, 1, -1};
    private static void bfs(Shark shark, Fish fish) {
        // 상어와 fish들간의 거리구하기 : 거리중 최솟값을 먹는다. -> BFS
        // 거리만 생각.
        // 자신보다 무게가 많이 나가는 칸은 지나갈수없음.
        Queue<Fish> queue = new LinkedList<>();
        queue.add(new Fish(shark.x, shark.y, shark.weight, 0));
        boolean[][] visited = new boolean[N][N];
        visited[shark.x][shark.y] = true;

        while (!queue.isEmpty()) {
            Fish poll = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = poll.x + dr[d];
                int nc = poll.y + dc[d];

                // 범위 밖이면
                if (!check(nr,nc)) continue;
                // 중복방문이면
                if (visited[nr][nc]) continue;
                // 상어의 몸무게보다 거쳐가는 fish의 몸무게가 더 크면
                if (poll.weight < map[nr][nc]) continue;
                // 거리 하나 증가
                visited[nr][nc] = true;
                queue.add(new Fish(nr, nc, poll.weight, poll.distance + 1));
                if (nr == fish.x
                        && nc == fish.y) {
                    fishPriorityQueue.add(new Fish(nr, nc, map[nr][nc], poll.distance + 1));
                    return;
                }
            }
        }
    }

    private static boolean check(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

}
/* 크기가 같으면 지나갈수는 있음.
* 거리가 가장 가까운 물고기를 먹으러감.
* 가장 위에있는 물고기, 가장 왼쪽에있는 물고기를 먹음.
* 자신의 크기와 같은 수의 고기를 먹을때마다 1증가
* 더이상 먹을수없으면 끝
* 최대 시간초 리턴.
*
* NxN 크기
* 0
* 1 2 3 4 5 6
* 9
* */