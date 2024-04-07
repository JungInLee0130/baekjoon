import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 500이하

        // 0팩토리얼인 경우
        if (N == 0) {
            System.out.println(0);
            return;
        }

        BigInteger result = new BigInteger(String.valueOf(N));

        for (int i = N - 1; i >= 1; i--) {
            result = result.multiply(new BigInteger(String.valueOf(i)));
        }

        String str = result.toString();
        //System.out.println(str);

        int len = str.length();

        int count = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (str.charAt(i) - '0' != 0) {
                break;
            }
            else{
                count++;
            }
        }

        System.out.println(count);
    }
}