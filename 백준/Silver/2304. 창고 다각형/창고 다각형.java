import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] stores;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        stores = new int[N][2];
        int idx = 0;
        int maxH = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            stores[i][0] = Integer.parseInt(st.nextToken());
            stores[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(stores, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0]; // 위치순으로
            }
        });

        for (int i = 0; i < N; i++) {
            if (maxH < stores[i][1]) {
                idx = i;
                maxH = stores[i][1];
            }
        }

        int l = stores[0][0];
        int h = stores[0][1];

        int sum = 0;
        for (int i = 1; i <= idx; i++) {
            if (h <= stores[i][1]) { // 더크면 h 커짐
                int sub = stores[i][0] - l; // 가로
                int a = sub * h;
                sum += a; // 면적 더하고
                //System.out.println(a);
                l = stores[i][0];
                h = stores[i][1]; // 갱신
            }
        }

        l = stores[N - 1][0];
        h = stores[N - 1][1];

        for (int i = N - 2; i >= idx; i--) {
            if (h <= stores[i][1]) {
                int sub = l - stores[i][0];
                int a = sub * h;
                sum += a;
                //System.out.println(a);
                l = stores[i][0];
                h = stores[i][1];
            }
        }

        sum += maxH;

        System.out.println(sum);


        bw.flush();
        br.close();
        bw.close();
    }
}
