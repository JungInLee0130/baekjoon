import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int N, K;
    static class Jewel{
        int weight;
        int price;

        public Jewel(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Jewel[] jewels = new Jewel[N]; // 1 ~ N

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int weight = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(weight, price);
        }

        int[] max_weight = new int[K]; // 1 ~ K
        for (int i = 0; i < K; i++) {
            max_weight[i] = Integer.parseInt(br.readLine());
        }


        // 그리디, 우선순위 큐
        // 보석 : 무게는 오름차순, 무게가 같으면, 가격 내림차순
        Arrays.sort(jewels, new Comparator<Jewel>() {
            @Override
            public int compare(Jewel o1, Jewel o2) {
                if (o1.weight == o2.weight) {
                    return Integer.compare(o2.price, o1.price);
                }
                return Integer.compare(o1.weight, o2.weight);
            }
        });

        // 가방 : 무게별로 오름차순
        Arrays.sort(max_weight);

        // 우선순위 큐 : 가방에 대해서 보석이 무게가 작거나 같은것은 전부 담음.
        // 가격 내림차순임.
        // poll한 첫번째 원소가 가장 무게가 큰것임.
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        // 범위 long
        long result = 0;
        for (int i = 0, j = 0; i < K; i++) { // 30만
            // 특이한게, j = 0으로 선언후 한번도 안드감.
            // 가방보다 무거운거 나오면 바로 break.
            // 그 인덱스부터 다음 시작.
            while (j < N && jewels[j].weight <= max_weight[i]) { // 30만 이하
                pq.offer(jewels[j++].price);
            }

            if (!pq.isEmpty()) {
                result += pq.poll();
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}