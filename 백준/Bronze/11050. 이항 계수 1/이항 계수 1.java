import java.io.*;
import java.util.StringTokenizer;

public class Main {
    // 반복문으로 구현 : 재귀는 나올때도 곱하면서 나와서 너무 비효율적
    public static int factorial(int n) {
        int fac = 1;
        for (int i = 2; i <= n; i++) {
            fac *= i;
        }
        return fac;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int result = factorial(N) / (factorial(N - K) * factorial(K));

        bw.write(String.valueOf(result));

        bw.flush();
        br.close();
        bw.close();
    }
}
