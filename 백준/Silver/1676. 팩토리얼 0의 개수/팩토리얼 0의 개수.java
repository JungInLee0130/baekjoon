import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 500이하

        int two = 0;
        int five = 0;
        for (int i = N; i >= 1; i--) { // N -> 1까지 2,5의 개수를 카운트
            two += countTwo(i);
            five += countFive(i);
        }

        System.out.println(Math.min(two, five));
    }

    private static int countFive(int n) {
        int times = 0;
        while (n >= 0) {
            if (n % 5 == 0) {
                n /= 5;
                times++;
            }
            else{
                break;
            }
        }
        return times;
    }

    private static int countTwo(int n) {
        int times = 0;
        while (n >= 0) {
            if (n % 2 == 0) {
                n /= 2;
                times++;
            }
            else{
                break;
            }
        }
        return times;
    }
}