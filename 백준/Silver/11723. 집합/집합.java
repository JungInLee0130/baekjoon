import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        M = Integer.parseInt(br.readLine());

        int[] arr = new int[21]; // 1 ~ 20
        StringBuilder sb = new StringBuilder();
        while (M-- > 0) { // 3백만
            StringTokenizer st = new StringTokenizer(br.readLine());

            String str = st.nextToken();

            // s <- x
            if (str.equals("add")) { // 1
                int n = Integer.parseInt(st.nextToken());

                if (arr[n] == 0) {
                    arr[n] = n;
                }
            }
            if (str.equals("remove")) { // 1
                int n = Integer.parseInt(st.nextToken());

                if (arr[n] != 0) {
                    arr[n] = 0;
                }
            }

            if (str.equals("check")) { // 1
                int n = Integer.parseInt(st.nextToken());

                if (arr[n] != 0) {
                    sb.append(1 + "\n");
                } else {
                    sb.append(0 + "\n");
                }
            }
            if (str.equals("toggle")) { // 1
                int n = Integer.parseInt(st.nextToken());

                if (arr[n] != 0) {
                    arr[n] = 0;
                }
                else{
                    arr[n] = n;
                }
            }
            if (str.equals("all")) { // 20
                for (int i = 1; i <= 20; i++) {
                    arr[i] = i;
                }
            }
            if (str.equals("empty")) { // 20
                for (int i = 1; i <= 20; i++) {
                    arr[i] = 0;
                }
            }
        }
        
        bw.write(sb.toString());
        
        // 20 * 3000000 = 6천만

        bw.flush();
        bw.close();
        br.close();
    }
}