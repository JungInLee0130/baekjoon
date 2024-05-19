import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int N, M;
    static LinkedList<Integer> linkedList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        linkedList = new LinkedList<>();

        for (int i = 0; i <= N; i++) {
            linkedList.add(i);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int j = start; j <= end; j++) {
                arrayList.add(linkedList.get(j));
            }
            Collections.reverse(arrayList);

            int times = end - start + 1;
            while (times > 0) {
                linkedList.remove(start);
                times--;
            }

            for (int j = start; j <= end; j++) {
                linkedList.add(j, arrayList.get(j - start));
            }
        }


        /*linkedList.subList(1, linkedList.size())
                .forEach(e -> System.out.print(e  + " "));*/

        linkedList.stream().filter(e -> 1 <= e && e <= linkedList.size() - 1)
                        .forEach(e -> System.out.print(e  + " "));


        bw.flush();
        bw.close();
        br.close();
    }
}