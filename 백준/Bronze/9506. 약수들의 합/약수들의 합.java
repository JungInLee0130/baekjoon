import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            N = Integer.parseInt(br.readLine());

            if (N == -1) {
                break;
            }

            String str = divideNum(N);

            if (str != null) {
                System.out.println(str);
                continue;
            }
            System.out.println(N + " is NOT perfect.");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static String divideNum(int N) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            if (N % i == 0) {
                arrayList.add(i);
            }
        }

        int sum = 0;
        for (Integer e : arrayList) {
            sum += e;
        }

        if (sum == N) {
            StringBuilder sb = new StringBuilder();
            sb.append(N + " = ");
            for (int i = 0; i < arrayList.size() - 1; i++) {
                sb.append(arrayList.get(i) + " + ");
            }
            sb.append(arrayList.get(arrayList.size() - 1));

            return sb.toString();
        }

        return null;
    }
}