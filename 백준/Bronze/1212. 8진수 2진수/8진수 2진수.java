import org.w3c.dom.ls.LSOutput;

import javax.naming.LimitExceededException;
import java.io.*;
import java.util.*;

public class Main {
    static String eightNum;

    // 0 1 2 3 4 5 6 7
    static String[] twoFormat = {"000", "001", "010", "011", "100", "101", "110", "111"};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 길이가 333,333까지 됨.
        eightNum = br.readLine();

        int len = eightNum.length();

        // 8진수 -> 2진수
        // 000 000 000 000 000 000
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            char c = eightNum.charAt(i);
            int num = c - '0';

            if (i == 0) { // 첫자리
                if (num == 0) {
                    sb.append("0");
                } else if (num == 1) {
                    sb.append("1");
                } else if (num == 2) {
                    sb.append("10");
                } else if (num == 3) {
                    sb.append("11");
                } else{
                    sb.append(twoFormat[num]);
                }
            }
            else {
                sb.append(twoFormat[num]);
            }
        }

        System.out.println(sb.toString());
    }
}