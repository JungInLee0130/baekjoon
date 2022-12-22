import java.io.*;

class Count{
    int zeroCount = 0;
    int oneCount = 0;

    public Count() {
    }

    public Count(int zeroCount, int oneCount) {
        this.zeroCount = zeroCount;
        this.oneCount = oneCount;
    }

    public int getZeroCount() {
        return zeroCount;
    }

    public void setZeroCount(int zeroCount) {
        this.zeroCount = zeroCount;
    }

    public int getOneCount() {
        return oneCount;
    }

    public void setOneCount(int oneCount) {
        this.oneCount = oneCount;
    }
}

public class Main {
    private static int zeroCount = 0;
    private static int oneCount = 0;
    private static Count[] dp;
    private static int N;
    private static int fibonacchi(int n) {
        if (n == 0) {
            zeroCount++;
            return 0;
        } else if (n == 1) {
            oneCount++;
            return 1;
        }
        else {
            return fibonacchi(n - 1) + fibonacchi(n - 2);
        }
    }

    private static Count fibonacchi2(int N) {
        for (int i = 2; i <= N; i++) {
            int zero = dp[i - 2].getZeroCount() + dp[i - 1].getZeroCount();
            int one = dp[i - 2].getOneCount() + dp[i - 1].getOneCount();

            dp[i] = new Count(zero, one);
        }
        return dp[N];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());

            dp = new Count[N + 1];
            /*bw.write(String.valueOf(dp.length) + "\n");
            bw.flush();*/

            if (N == 0) {
                dp[0] = new Count(1, 0);
                bw.write(String.valueOf(1) + " " + String.valueOf(0) + "\n");
            } else if (N == 1) {
                dp[1] = new Count(0, 1);
                bw.write(String.valueOf(0) + " " + String.valueOf(1) + "\n");
            } else {
                dp[0] = new Count(1, 0);
                dp[1] = new Count(0, 1);

                fibonacchi2(N);
                bw.write(String.valueOf(dp[N].getZeroCount()) + " " + String.valueOf(dp[N].getOneCount()) + "\n");
            }

        }


        //zeroCount = 0;
        //oneCount = 0;

        bw.flush();
        br.close();
        bw.close();
    }
}
