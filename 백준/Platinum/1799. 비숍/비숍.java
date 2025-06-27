import java.io.*;
import java.util.*;

class Main {
    static int N;
    static int[][] chess;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        chess = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                chess[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 10x10 = 100
        //dfs?
        selected = new int[N][N];
        occupied = new int[N][N]; // 놓을수없는 지점이 나올때마다 +1
        //for (int i = 0; i < N; i++) {
          //  for (int j = 0; j < N; j++) {
                //dfs(i, j, 0);
        blackDfs(0, 0, 0);
        whiteDfs(0, 1, 0);
            //}
        //}

        System.out.println(blackMax + whiteMax);

        br.close();
    }

    private static void whiteDfs(int r, int c, int count) {
        while (r <= N - 1) {
            while (c <= N - 1) {
                // 체스를 둘수있고 && OCCUPIED가 아님
                if (chess[r][c] == POSSIBLE && occupied[r][c] == EMPTY) {
                    selected[r][c] = LOCATED;
                    setOccupied(r, c);
                    whiteDfs(r, c, count + 1);
                    rollback(r, c);
                    selected[r][c] = EMPTY;
                }
                c += 2;
            }
            r++;
            if (r % 2 == 0) {
                c = 1;
            } else {
                c = 0;
            }
        }

        if (r == N) {
            if (whiteMax < count) {
                /*for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        System.out.print(selected[i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println();*/
                whiteMax = count;
            }
        }

    }

    static int[][] occupied;
    static int[][] selected;
    private static void blackDfs(int r, int c, int count) {
        while (r <= N - 1) {
            while (c <= N - 1) {
                // 체스를 둘수있고 && OCCUPIED가 아님
                if (chess[r][c] == POSSIBLE && occupied[r][c] == EMPTY) {
                    selected[r][c] = LOCATED;
                    setOccupied(r, c);
                    blackDfs(r, c, count + 1);
                    rollback(r, c);
                    selected[r][c] = EMPTY;
                }
                c += 2;
            }
            r++;
            if (r % 2 == 0) {
                c = 0;
            } else {
                c = 1;
            }
        }

        if (r == N) {
            if (blackMax < count) {
                /*for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        System.out.print(selected[i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println();*/
                blackMax = count;
            }
        }
    }

    static int blackMax = 0;
    static int whiteMax = 0;

    private static void rollback(int r, int c) {
        occupied[r][c] -= OCCUPIED;

        int mr = r + 1;
        int mc = c + 1;

        while (mr <= N - 1 && mc <= N - 1) {
            occupied[mr][mc] -= OCCUPIED;

            mr += 1;
            mc += 1;
        }

        mr = r - 1;
        mc = c + 1;

        while (mr >= 0 && mc <= N - 1) {
            occupied[mr][mc] -= OCCUPIED;

            mr -= 1;
            mc += 1;
        }

        mr = r - 1;
        mc = c - 1;

        while (mr >= 0 && mc >= 0) {
            occupied[mr][mc] -= OCCUPIED;

            mr -= 1;
            mc -= 1;
        }

        mr = r + 1;
        mc = c - 1;

        while (mr <= N - 1 && mc >= 0) {
            occupied[mr][mc] -= OCCUPIED;

            mr += 1;
            mc -= 1;
        }
    }

    private static void setOccupied(int r, int c) {
        occupied[r][c] += OCCUPIED;

        int mr = r + 1;
        int mc = c + 1;

        while (mr <= N - 1 && mc <= N - 1) {
            occupied[mr][mc] += OCCUPIED;

            mr += 1;
            mc += 1;
        }

        mr = r - 1;
        mc = c + 1;

        while (mr >= 0 && mc <= N - 1) {
            occupied[mr][mc] += OCCUPIED;

            mr -= 1;
            mc += 1;
        }

        mr = r - 1;
        mc = c - 1;

        while (mr >= 0 && mc >= 0) {
            occupied[mr][mc] += OCCUPIED;

            mr -= 1;
            mc -= 1;
        }

        mr = r + 1;
        mc = c - 1;

        while (mr <= N - 1 && mc >= 0) {
            occupied[mr][mc] += OCCUPIED;

            mr += 1;
            mc -= 1;
        }
    }

    static final int POSSIBLE = 1;
    static final int LOCATED = 1;
    static final int OCCUPIED = 2;
    static final int EMPTY = 0;
}