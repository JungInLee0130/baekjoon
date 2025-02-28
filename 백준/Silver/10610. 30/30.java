import java.io.*;
import java.util.*;

class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        int sum = 0;
        boolean hasZero = false;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            int n = str.charAt(i) - '0';
            if (n == 0) {
                hasZero = true;
            }
            sum += n;
            list.add(n);
        }

        if (sum % 3 == 0 && hasZero) {
            Collections.sort(list, Collections.reverseOrder());
            StringBuilder sb = new StringBuilder();
            for (Integer e : list){
                sb.append(e);
            }
            System.out.println(sb);
        }
        else{
            System.out.println(-1);
        }



        br.close();
    }
}