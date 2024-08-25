import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Flower> flowers;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        flowers = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int smsd = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());
            int emed = Integer.parseInt(st.nextToken()) * 100 + Integer.parseInt(st.nextToken());

            flowers.add(new Flower(smsd, emed));
        }

        Collections.sort(flowers);

        int max = 0; int count = 0;
        int start = 301;
        int idx = 0;

        while (start < 1201) {
            boolean isFinded = false;

            for (int i = idx; i < N; i++) {
                // 시작일보다 큼. break
                if (start < flowers.get(i).startDate) break;

                if (flowers.get(i).startDate <= start && max < flowers.get(i).endDate) {
                    max = flowers.get(i).endDate;
                    idx = i + 1;
                    isFinded = true;
                }
            }
            if (isFinded) {
                start = max;
                count++;
            }
            else{
                break;
            }
        }

        if (max < 1201) System.out.println(0);
        else System.out.println(count);
    }
    static class Flower implements Comparable<Flower>{
        int startDate;
        int endDate;

        public Flower(int startDate, int endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }

        @Override
        public int compareTo(Flower flower) {
            if (this.startDate - flower.startDate == 0) {
                return this.endDate - flower.endDate;
            }
            return this.startDate - flower.startDate;
        }
    }
}