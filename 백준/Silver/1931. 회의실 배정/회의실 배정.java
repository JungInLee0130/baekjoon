import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        long[][] meeting = new long[N][2]; // [2] : 길이
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            meeting[i][0] = Long.parseLong(st.nextToken());
            meeting[i][1] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(meeting, new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                if (o1[1] == o2[1]) {
                    return (int) (o1[0] - o2[0]);
                }
                return (int) (o1[1] - o2[1]);
            }
        });
        int count = 1;
        long[] meet = meeting[0];
        for (int i = 1; i < N; i++) {
            if (meet[1] <= meeting[i][0]) {
                meet = meeting[i];
                count++;
            }
        }

        bw.write(String.valueOf(count));

        bw.flush();
        br.close();
        bw.close();
    }


}
