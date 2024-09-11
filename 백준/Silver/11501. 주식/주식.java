import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            long sum = 0;
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int max = arr[N - 1];
            for (int i = N - 2; i >= 0; i--) {
                if (max < arr[i]) {
                    max = arr[i];
                }
                else{
                    sum += max - arr[i];
                }
            }
            
            System.out.println(sum);
        }

        bw.flush();
        bw.close();
        br.close();
    }
}