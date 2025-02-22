import java.io.*;
import java.util.*;

class Main {
    static StringTokenizer st;
    static int[][] A;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clouds = new ArrayList<>();
        clouds.add(new Cloud(N - 1, 0));
        clouds.add(new Cloud(N - 1, 1));
        clouds.add(new Cloud(N - 2, 0));
        clouds.add(new Cloud(N - 2, 1));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            // 1. 이동
            move(d, s);
            // 2. 물의양 + 1
            plus();
            // 3. 물복사
            replicate();
            // 4. 물의 양이 2이상인 칸에 구름 생성, 물 - 2
            make();
        }

        int sum = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                sum += A[r][c];
            }
        }

        System.out.println(sum);


        br.close();
    }

    private static void make() {
        List<Cloud> nClouds = new ArrayList<>();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                Cloud curCloud = new Cloud(r, c);
                if (A[r][c] >= 2 && !clouds.contains(curCloud)) {
                    nClouds.add(new Cloud(r, c));
                    A[r][c] -= 2;
                }
            }
        }

        clouds = nClouds;
    }

    private static void replicate() {
        for (int i = 0; i < clouds.size(); i++) {
            Cloud curCloud = clouds.get(i);

            int r = curCloud.r;
            int c = curCloud.c;

            // 대각선 방향의 개수 파악
            // 1 3 5 7
            int count = 0;
            for (int d = 1; d <= 7; d += 2) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (isOut(nr,nc)) continue; // 경계밖이면 x
                if (A[nr][nc] >= 1){
                    count++;
                }
            }

            A[r][c] += count; // count만큼 물 더해줌
        }
    }

    private static void disappear() {

    }

    private static void plus() {
        for (int i = 0; i < clouds.size(); i++) {
            Cloud curCloud = clouds.get(i);

            int r = curCloud.r;
            int c = curCloud.c;

            A[r][c] += 1;
        }
    }

    static List<Cloud> clouds;
    static class Cloud{
        int r;
        int c;

        public Cloud(int r, int c) {
            this.r = r;
            this.c = c;
        }

		@Override
		public int hashCode() {
			return Objects.hash(c, r);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Cloud other = (Cloud) obj;
			return c == other.c && r == other.r;
		}

        
    }

    // 1. move
    private static void move(int d, int s) {
        for (int i = 0; i < clouds.size(); i++) {
            Cloud curCloud = clouds.get(i);

            int r = curCloud.r;
            int c = curCloud.c;
            // d, s
            int cs = s;

            while (cs > 0) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (isOut(nr, nc)) { // 바구니는 연결되어있음
                    if (nr < 0){ // -1
                        nr = N - 1;
                    }
                    if (nr > N - 1){ // N
                        nr = 0;
                    }
                    if (nc < 0) {
                        nc = N - 1;
                    }
                    if (nc > N - 1) {
                        nc = 0;
                    }
                }

                // 좌표 반영
                r = nr;
                c = nc;

                cs--;
            }

            clouds.set(i, new Cloud(r, c));
        }
    }

    private static boolean isOut(int nr, int nc) {
        return 0 > nr || nr > N - 1 || 0 > nc || nc > N - 1;
    }

    static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
}