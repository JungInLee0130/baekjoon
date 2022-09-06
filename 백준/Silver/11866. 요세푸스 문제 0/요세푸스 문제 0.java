import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }

        ArrayList<Integer> array = new ArrayList<>();

        while (!queue.isEmpty()) {
            for (int i = 1; i <= K - 1; i++) {
                Integer poll = queue.poll();
                queue.add(poll);
            }
            Integer poll = queue.poll();
            array.add(poll);
        }

        bw.write("<");
        for (int i = 0; i < array.size() - 1; i++) {
            bw.write(String.valueOf(array.get(i)) + ", ");
        }
        bw.write(String.valueOf(array.get(array.size() - 1)) + ">\n");

        bw.flush();
        br.close();
        bw.close();
    }
}
