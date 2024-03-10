import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static BigInteger N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 10에 1000승
        // 10
        N = new BigInteger(st.nextToken());
        M = new BigInteger(st.nextToken());

        System.out.println(N.divide(M));
        System.out.println(N.mod(M));

    }
}