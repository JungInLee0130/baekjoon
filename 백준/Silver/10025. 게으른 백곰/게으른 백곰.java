import java.io.*;
import java.util.*;

class Main {
    static int N, K;
    static int[] G;
    static int[] X;
    static class Ice{
        int g;
        int x;

        public Ice(int g, int x) {
            this.g = g;
            this.x = x;
        }
    }
    static Ice[] ices;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int max = 0;
        ices = new Ice[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int g = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            ices[i] = new Ice(g, x);
            max = Math.max(max, x);
        }

        Arrays.sort(ices, new Comparator<Ice>() {
            @Override
            public int compare(Ice o1, Ice o2) {
                return o1.x - o2.x;
            }
        });

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            map.put(ices[i].x, ices[i].g);
        }

        int left = 0;
        int right = 2 * K;

        int answer = 0;

        for (int i = 0; i < N; i++) {
            if (0 <= ices[i].x && ices[i].x <= 2 * K) {
                answer += ices[i].g;
            }
        }

        int count = answer;

        while (left - 1 <= max && right - 1 <= max){
            int sub = right - left;

            if (sub < 2 * K) {
                if (map.containsKey(right + 1)){
                    count += map.get(right + 1);
                }
                right += 1;
            } else if (sub > 2 * K) {
                if (map.containsKey(left)){
                    count -= map.get(left);
                }
                left += 1;
            }

            if (sub == 2 * K){
                answer = Math.max(count, answer);

                if (map.containsKey(left)){
                    count -= map.get(left);
                }

                left += 1;
            }
        }

        System.out.println(answer);


        br.close();
    }

    private static int getCount(int left, int right, int answer) {
        return answer;
    }
}