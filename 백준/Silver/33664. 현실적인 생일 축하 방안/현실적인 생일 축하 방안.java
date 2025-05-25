import java.io.*;
import java.util.*;

class Main {
    static int N, M;
    static long B;
    static Map<String, Long> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        B = Long.parseLong(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            long price = Long.parseLong(st.nextToken());

            map.put(name, price);
        }

        long price = 0;
        boolean isAcceptable = true;
        for (int i = 0; i < M; i++) {
            String name = br.readLine();

            price += map.get(name);

            if (price > B) {
                isAcceptable = false;
                break;
            }
        }

        if (isAcceptable) {
            System.out.println("acceptable");
        }
        else{
            System.out.println("unacceptable");
        }


        br.close();
    }
}