import java.io.*;
import java.util.*;

public class Main {
    static String S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        S = br.readLine();

        int len = S.length();


        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < len; i++) { // idx // n
            for (int j = i; j < len; j++) { // 끝까지 // n, n-1, n-2, n-3,....,1
                set.add(S.substring(i, j + 1));
            }
        }

        System.out.println(set.size());

        bw.flush();
        bw.close();
        br.close();
    }
}