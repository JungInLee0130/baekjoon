import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int D,N;
    static int[] d;
    static int[] pizza;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        D = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        d = new int[D];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < D; i++) {
            int h =  Integer.parseInt(st.nextToken());
            min = Math.min(min, h);
            d[i] = min;
        }

        pizza = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pizza[i] = Integer.parseInt(st.nextToken());
        }

        isPizza = new boolean[D];
        height = D - 1;
        for (int i = 0; i < N; i++) {
            if (!pos(pizza[i])) {
                height = -1;
                break;
            }
        }

        System.out.println(height + 1);


        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean pos(int size) {
        for (int i = height; i >= 0; i--) {
            if (d[i] >= size) {
                height = i;
                d[i] = 0;
                isPizza[i] = true;
                return true;
            }
        }

        return false;
    }

    static boolean[] isPizza;
    static int height;
}