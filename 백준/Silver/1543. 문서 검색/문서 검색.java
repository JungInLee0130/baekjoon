
import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static String str;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine(); // 2500자 이하

        String s = br.readLine();

        int strlen = str.length();
        int slen = s.length();

        // 최대 몇번 등장? 중복 안됨.

        //System.out.println("abc".compareTo("bcd"));
        int cnt = 0;

        for (int i = 0; i <= strlen - slen; i++) {
            String substring = str.substring(i, i + slen);
            //System.out.println(substring);
            int num = s.compareTo(substring); // slen까지의 문자열을 잘라 비교 // i, i + 3
            if (num == 0) {
                cnt++;
                i = i + slen - 1; // i = i + 3
            }
        }

        System.out.println(cnt);


    }
}