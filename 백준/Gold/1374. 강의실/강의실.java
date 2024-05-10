import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int N;
    static PriorityQueue<Integer> pq;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>();
        // 시작시간, 종료시간 : 0이상 10억이하
        arr = new int[N][3];
        for (int i = 0; i < N; i++) { // 10만개
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    Integer.compare(o1[2], o2[2]);
                }
                return Integer.compare(o1[1], o2[1]); // 시작시간 오름차순
            }
        });

        pq.offer(arr[0][2]);

        for (int i = 1; i < N; i++) {
            if (pq.peek() <= arr[i][1]) { // 시작시간이 같거나 크면
                pq.poll(); // poll

            }
            pq.offer(arr[i][2]); // 어짜피 대입하는건 똑같음.
        }

        bw.write(String.valueOf(pq.size()));

        bw.flush();
        bw.close();
        br.close();
    }
}