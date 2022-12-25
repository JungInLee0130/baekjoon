import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new LinkedList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            queue.add(Integer.parseInt(st.nextToken()));
        }
        
        Stack<Integer> spare = new Stack<>();
        ArrayList<Integer> arrayList = new ArrayList<>();
        
        int num = 1;
        while (!queue.isEmpty()) {
            Integer peek = queue.peek();
            if (num != peek) {
                if (!spare.isEmpty() && spare.peek() == num) {
                    Integer pop = spare.pop();
                    arrayList.add(pop);
                    num++;
                }
                else {
                    spare.add(peek);
                    queue.poll();
                }
            } else {
                arrayList.add(peek);
                queue.poll();
                num++;
            }
        }

        boolean isNice = true;

        while (!spare.isEmpty()) {
            Integer pop = spare.pop();

            if (pop != arrayList.get(arrayList.size() - 1) + 1) {
                isNice = false;
                break;
            }
            else {
                arrayList.add(pop);
            }
        }

        if (isNice) {
            bw.write("Nice");
        } else {
            bw.write("Sad");
        }
        

        bw.flush();
        br.close();
        bw.close();
    }
}
