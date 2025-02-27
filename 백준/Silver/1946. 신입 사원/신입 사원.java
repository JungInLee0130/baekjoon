import java.io.*;
import java.util.*;

class Main {
    static int T;
    static int N;
    static class Rank{
        int num;
        int a;
        int b;

        public Rank(int num, int a, int b) {
            this.num = num;
            this.a = a;
            this.b = b;
        }
    }
    static Rank[] ranks;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            ranks = new Rank[N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken()); // 순위
                int b = Integer.parseInt(st.nextToken()); // 순위
                
                ranks[i] = new Rank(i, a, b); // num, 순위a, 순위b
            }

            Rank[] rankOne = Arrays.copyOf(ranks, N);
            Rank[] rankTwo = Arrays.copyOf(ranks, N);

            Arrays.sort(rankOne, new Comparator<Rank>(){
                @Override
                public int compare(Rank r1, Rank r2){
                    return r1.a - r2.a; // 순위 a : 오름차순
                }
            });

            /*for (int i = 0; i < N; i++) {
                System.out.print(rankOne[i].a + " ");
            }
            System.out.println();*/

            Arrays.sort(rankTwo, new Comparator<Rank>(){
                @Override
                public int compare(Rank r1, Rank r2){
                    return r1.b - r2.b; // 순위b 오름차순
                }
            });

            /*for (int i = 0; i < N; i++) {
                System.out.print(rankOne[i].a + " ");
            }
            System.out.println();*/

            boolean[] isPossible = new boolean[N]; // 0 ~ N
            
            int min = rankOne[0].b;

            for (int i = 1; i < N; i++) {
                Rank cur = rankOne[i];
                if (min > cur.b){
                    min = rankOne[i].b;
                    isPossible[cur.num] = true;
                }
            }

            int count = 1;
            for (int i = 0; i < N; i++) {
                if (isPossible[i]) count++;
            }

            sb.append(count + "\n");
        }

        System.out.print(sb);


        br.close();
    }
}