import java.io.*;
import java.util.*;

class Main {
    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int k = solve(M, N, x, y);
            sb.append(k + "\n");
        }

        System.out.print(sb);

        br.close();
    }

    private static int solve(int M, int N, int x, int y) {
        int k = 1;

        int a = 1;
        int b = 1;

        int na = a;
        int nb = b;

        boolean isPossible = false;

        if (na == x && nb == y){
            return 1;
        }

        if (M < N) {
            na = x; // x번째
            nb = x;
            k = x;

            int sub = N - M;

            int gcd = getGCD(M, N);

            isPossible = false;

            while (true){
                if (k > gcd){
                    break;
                }
                if (nb == y){
                    isPossible = true;
                    break;
                }
                nb = nb - sub;
                if (nb <= 0){
                    int temp = nb;
                    nb = N + temp;
                }
                k += M;
            }

            if (isPossible) return k;
            return -1;
        }

        else{
            nb = y; // y번째
            na = y;
            k = y;

            int sub = M - N;

            int gcd = getGCD(M, N);

            isPossible = false;
            while (true){
                if (k > gcd){
                    break;
                }
                if (na == x){
                    isPossible = true;
                    break;
                }
                na = na - sub;
                if (na <= 0){
                    int temp = na;
                    na = M + temp;
                }
                k += N;
            }

            if (isPossible) return k;
            return -1;
        }
    }

    private static int getGCD(int M, int N) {
        int k = 1;

        if (M > N){
            int temp = M;
            M = N;
            N = temp;
        }


        while (true) {
            if (M == 1 || N == 1) {
                k *= M;
                k *= N;
                break;
            }
            for (int i = 2; i <= M; i++) {
                if (M % i == 0 && N % i == 0) {
                    M /= i;
                    N /= i;
                    k *= i;
                    break;
                } else if (M % i == 0) {
                    M /= i;
                    k *= i;
                    break;
                } else if (N % i == 0) {
                    N /= i;
                    k *= i;
                    break;
                }
            }
        }


        return k;
    }
}