import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            int C = Integer.parseInt(br.readLine()); // 센트
            // 100센트
            // 쿼터 25센트 다임 10센트 니켈 5센트 페니 1센트

            int quarter = C / 25;
            C = C % 25;
            int dime = C / 10;
            C = C % 10;
            int nikkel = C / 5;
            C = C % 5;
            int penny = C;

            System.out.println(quarter + " " + dime + " " + nikkel + " " + penny);
        }


        bw.flush();
        bw.close();
        br.close();
    }
}