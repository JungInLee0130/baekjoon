import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        ArrayList<Integer> arrayList = new ArrayList<>();
        // 최소 힙
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            //arrayList.add(Integer.parseInt(br.readLine()));
            priorityQueue.add(Integer.parseInt(br.readLine()));
        }

        //Collections.sort(arrayList);

        ArrayList<Integer> result = new ArrayList<>();

        // 시간 초과 -> 우선순위큐 : 최소힙 -> 정렬을 안해도되서 시간 대폭 감소. O(log n)
        /*while (arrayList.size() > 1) {
            int one = arrayList.get(0) + arrayList.get(1);
            arrayList.add(one);
            arrayList.remove(0);
            arrayList.remove(0);


            result.add(one);

            Collections.sort(arrayList);
        }*/

        while (priorityQueue.size() > 1) {
            int one = priorityQueue.poll() + priorityQueue.poll();
            priorityQueue.add(one);

            result.add(one);
        }

        int sum = 0;
        for (int i = 0; i < result.size(); i++) {
            sum += result.get(i);
        }

        bw.write(String.valueOf(sum));

        bw.flush();
        br.close();
        bw.close();
    }
}
