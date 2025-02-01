import java.io.*;
import java.util.*;

class Main {
    static int N, M, K;
    static int[][] map;
    static List<Fireball> fireballs = new ArrayList<>();
    static class Fireball{
        int r;
        int c;
        int m;
        int s;
        int d;

        public Fireball(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 파이어볼 M개 발사
        // 위치, 질량, 방향, 속력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            Fireball fireball = new Fireball(r, c, m, s, d);
            fireballs.add(fireball);
        }

        for (int k = 0; k < K; k++) {
            // 1. move
            move();

            // 2. 2개이상의 파이어볼이 있는 칸에서 뭔가 일어남
            find();
        }

        int answer = 0;
        for (Fireball fireball:fireballs) {
            answer += fireball.m;
        }

        System.out.println(answer);


        br.close();
    }

    private static void find() {
        List<Fireball>[][] map = new ArrayList[N][N];

        for (int i = 0; i < fireballs.size(); i++) {
            Fireball fireball = fireballs.get(i);

            int r = fireball.r;
            int c = fireball.c;

            if (map[r][c] == null) {
                map[r][c] = new ArrayList<>();
            }

            map[r][c].add(fireball);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == null) continue;
                int size = map[i][j].size();
                if (size >= 2) {
                    // 1. 파이어볼이 합쳐짐
                    // 2. 4개로 나뉘어짐
                    // 3. 질량,속력,방향
                    int r = i;
                    int c = j;
                    int nm = 0;
                    int ns = 0;

                    int[] direction = new int[map[i][j].size()];
                    int directionIdx = 0;
                    for (Fireball fireball: map[i][j]) {
                        nm += fireball.m;
                        ns += fireball.s;

                        direction[directionIdx++] = fireball.d;

                        fireballs.remove(fireball);
                    }

                    // 새 질량
                    nm /= 5;

                    if (nm == 0){
                        // 사라짐.
                        continue;
                    }

                    // 새 속력
                    ns /= size;

                    boolean isEven = direction[0] % 2 == 0; // 짝수
                    boolean isTrue = true;
                    for (int k = 1; k < size; k++) {
                        if ((isEven && direction[k] % 2 == 0)
                                || (!isEven && direction[k] % 2 != 0)) {
                            continue;
                        }
                        else{
                            isTrue = false;
                            break;
                        }
                    }

                    int nd = 0;

                    if (isTrue){
                        // 0,2,4,6
                        // go
                    }
                    else{
                        // 1,3,5,7
                        nd = 1;
                    }

                    // 새로만든 4개의 불공 넣기
                    for (int k = 0; k < 4; k++) {
                        // 0 1 2 3
                        // 0 2 4 6
                        Fireball fireball = new Fireball(r, c, nm, ns, nd);
                        fireballs.add(fireball); // 새 파이어볼 대입
                        nd += 2;
                    }
                }
            }
        }
    }

    private static void move() {
        // 연결되어있다가 다른 쪽으로 나온다는 뜻
        for (int i = 0; i < fireballs.size(); i++){
            Fireball fireball = fireballs.get(i);

            int r = fireball.r;
            int c = fireball.c;
            int d = fireball.d;
            int s = fireball.s;
            int m = fireball.m;

            int ns = s % N;

            for (int k = 0; k < ns; k++) {
                int nr = r + dx[d];
                int nc = c + dy[d];

                if (isRange(nr, nc)) {

                }
                else{
                    if (nr < 0){ // -1
                        nr += N;
                    }
                    if (nc < 0){ // -1
                        nc += N;
                    }
                    if (nr > N - 1){ // N
                        nr -= N;
                    }
                    if (nc > N - 1) { // N
                        nc -= N;
                    }
                }

                r = nr;
                c = nc;
            }


            fireball = new Fireball(r, c, m, s, d);
            fireballs.set(i, fireball);
        }
    }

    private static boolean isRange(int r, int c) {
        return 0 <= r && r <= N - 1 && 0 <= c && c <= N - 1;
    }

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
}