import java.io.*;
import java.nio.Buffer;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[] nums = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            int min = 1_000_000;
            int max = -1_000_000;
            for (int j = 0; j < N; j++) {
                nums[j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);
            }

            sb.append(min + " " + max + "\n");
        }

        System.out.println(sb);

        br.close();
    }
}