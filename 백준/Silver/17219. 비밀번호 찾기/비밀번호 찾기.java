import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static HashMap<String, String> pwMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        pwMap = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String[] sp = br.readLine().split("\\s");

            pwMap.put(sp[0], sp[1]);
        }

        for (int i = 0; i < M; i++) {
            sb.append(pwMap.get(br.readLine()) + "\n");
        }

        System.out.println(sb.toString());
    }
}