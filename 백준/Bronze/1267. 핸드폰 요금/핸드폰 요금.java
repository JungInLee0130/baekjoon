import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] call;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        call = new int[N];
        for (int i = 0; i < N; i++) {
            call[i] = Integer.parseInt(st.nextToken());
        }

        // Y : 30/10 M:60/15
        int price1 = 0;
        int price2 = 0;

        for (int i = 0; i < N; i++) {
            price1 = price1 + (call[i] / 30 * 10);
            price1 = price1 + 10; // 기본요금

            price2 = price2 + (call[i] / 60 * 15);
            price2 = price2 + 15;
        }

        if (price1 > price2) {
            System.out.println("M " + price2);
        } else if (price2 > price1) {
            System.out.println("Y " + price1);
        } else{
            System.out.println("Y " + "M " + price1);
        }
    }
}