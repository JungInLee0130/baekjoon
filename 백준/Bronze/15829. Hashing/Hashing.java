import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int L = Integer.parseInt(br.readLine());

        String s = br.readLine();

        char[] chars = s.toCharArray();

        // a == 1 ~ z == 26
        BigInteger summation = new BigInteger("0");
        for (int i = 0; i < L; i++) {
            int alphaToNum = chars[i] - 'a' + 1;
            BigInteger square = new BigInteger("1");
            for (int j = 0; j < i; j++) {
                square = square.multiply(BigInteger.valueOf(31));
            }
            square = square.multiply(BigInteger.valueOf(alphaToNum));
            summation = summation.add(square);
            summation = summation.remainder(BigInteger.valueOf(1234567891));
        }

        bw.write(String.valueOf(summation));

        bw.flush();
        br.close();
        bw.close();
    }
}
