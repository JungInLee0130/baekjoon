import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int N;
    static long S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        S = Long.parseLong(br.readLine());

        int num = 1;
        long end = 0;
        while (true) {
            end += num;
            if (end > S) break; // 더했는데 end보다 크면 num까지 더한것중에 하나빼면됨.
            num++;
        }

        System.out.println(num - 1);


        bw.flush();
        bw.close();
        br.close();
    }
}