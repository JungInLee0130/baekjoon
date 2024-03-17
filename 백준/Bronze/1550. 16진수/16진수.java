import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static String str;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 16진수 -> 10진수
        // 0000 -> 2진수 -> 10진수
        str = br.readLine();

        int len = str.length();
        long answer = 0;
        for (int i = len - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if (c == 'A') {
                int num = 10;
                answer += num * Math.pow(16, len - 1 - i);
            }
            else if (c == 'B') {
                int num = 11;
                answer += num * Math.pow(16, len - 1 - i);
            }
            else if (c == 'C') {
                int num = 12;
                answer += num * Math.pow(16, len - 1 - i);
            }
            else if (c == 'D') {
                int num = 13;
                answer += num * Math.pow(16, len - 1 - i);
            }
            else if (c == 'E') {
                int num = 14;
                answer += num * Math.pow(16, len - 1 - i);
            }
            else if (c == 'F') {
                int num = 15;
                answer += num * Math.pow(16, len - 1 - i);
            }
            else{
                int num = c - '0';
                answer += num * Math.pow(16, len - 1 - i);
            }
        }

        System.out.println(answer);
    }
}