import java.io.*;
import java.util.*;

class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        String str = br.readLine();

        StringTokenizer st = new StringTokenizer(str, ".|:#");

        long sum = 0;
        while (true) {
            String s = null;

            try {
                s = st.nextToken();
            } catch (NoSuchElementException e){
                break;
            }

            //System.out.println(s);

            sum += Long.parseLong(s);
        }

        System.out.println(sum);

        br.close();
    }
}