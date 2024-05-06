import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int T;
    static int[] parent, level;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());


        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int F = Integer.parseInt(br.readLine());

            parent = new int[F * 2];
            level = new int[F * 2];

            for (int i = 0; i < F * 2; i++) {
                parent[i] = i;
                level[i] = 1;
            }

            int idx = 0;
            Map<String, Integer> map = new HashMap<>();

            for (int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                String a = st.nextToken();
                String b = st.nextToken();

                if (!map.containsKey(a)) {
                    map.put(a, idx++);
                }
                if (!map.containsKey(b)) {
                    map.put(b, idx++);
                }

                sb.append(union(map.get(a), map.get(b)) + "\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
            level[x] += level[y];

            //level[y] = 1; // 사실 여기 다시 접근할일 없음. 왜 1넣어주는지도 모르겠음.
        }
        return level[x];
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }


}