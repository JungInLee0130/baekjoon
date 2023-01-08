import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        BigInteger result = combination(n, m);

        bw.write(String.valueOf(result));

        bw.flush();
        br.close();
        bw.close();
    }

    private static BigInteger factorial(int N) {
        if (N == 0) {
            return new BigInteger("1");
        }
        BigInteger fac = new BigInteger("1");
        for (int i = 1; i <= N; i++) {
            fac = fac.multiply(BigInteger.valueOf(i));
        }
        return fac;
    }

    private static BigInteger combination(int n, int m) {
        return factorial(n).divide(factorial(m).multiply(factorial(n - m)));
    }
}
