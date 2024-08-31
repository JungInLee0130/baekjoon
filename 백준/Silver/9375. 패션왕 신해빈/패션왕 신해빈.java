import java.io.*;
import java.util.*;

public class Main {
    static int N, T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            Map<String, Integer> map = new HashMap<>();

            N = Integer.parseInt(br.readLine());

            // map : 종류, 숫자
            for (int i = 0; i < N; i++) {
                String[] sp = br.readLine().split("\\s");
                map.put(sp[1], map.getOrDefault(sp[1], 1) + 1); // getorDefault + 1
            }

            // 종류, 숫자 다 모집
            int mul = 1;
            for (Map.Entry<String, Integer> entry:map.entrySet()) {
                int value = entry.getValue();
                mul *= value;
            }

            sb.append(mul - 1 + "\n");
        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();
    }
}