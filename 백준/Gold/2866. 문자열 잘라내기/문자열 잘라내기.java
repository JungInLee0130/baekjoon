import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static String[] strs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken()); // 2이상 1000이하

        strs = new String[R];
        for (int i = 0; i < R; i++) {
            strs[i] = br.readLine();
        }

        binarySearch();

        System.out.println(answer);


        bw.flush();
        br.close();
        bw.close();
    }

    private static int answer = 0;
    private static void binarySearch() {
        int left = 0;
        int right = R - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            Map<String, Integer> map = new HashMap<>();
            for (int c = 0; c < C; c++) {
                StringBuilder sb = new StringBuilder();
                for (int r = mid + 1; r < R; r++) {
                    sb.append(strs[r].charAt(c));
                }
                String str = sb.toString();
                map.put(str, map.getOrDefault(str,0) + 1);
            }

            if (map.size() < C) {
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }
        answer = Math.max(left, right);
    }
}