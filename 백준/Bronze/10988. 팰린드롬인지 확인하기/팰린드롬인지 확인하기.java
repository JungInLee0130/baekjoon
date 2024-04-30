import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class Main {
    static String str;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        str = br.readLine();

        int len = str.length();
        boolean isPel = true;
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) == str.charAt(len - 1 - i)) {
                continue;
            }
            isPel = false;
            break;
        }

        if (isPel) {
            System.out.println(1);
        }
        else{
            System.out.println(0);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a > b) {
            parent[a] = b;
        }
        else{
            parent[b] = a;
        }
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    static int[] parent;
}