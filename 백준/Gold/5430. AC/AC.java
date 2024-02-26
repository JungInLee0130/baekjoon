import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static int T, N;
    static String p;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            p = br.readLine();

            N = Integer.parseInt(br.readLine()); // 배열 수의 개수

            String str = br.readLine(); // [12,324,] 이런형태로 주어짐

            // [] -> 이거 빼고 copy 여기서 시간초과 날수도있음 . 10,20,30
            String nums = String.copyValueOf(str.toCharArray(),1, str.length() - 2);

            // 정수 배열로 변환
            toDeque(nums);

            isError = false;

            int pointer = 0; // 앞. 맨 처음
            // p배열 명령어 파악
            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) == 'R') {
                    if (pointer == 0) {
                        pointer = deque.size() - 1;
                    }
                    else{
                        pointer = 0;
                    }
                }
                if (p.charAt(i) == 'D'){
                    D(pointer);
                }
            }

            if (!isError) {
                if (deque.isEmpty()) {
                    System.out.println("[]");
                }
                else {
                    StringBuilder sb = new StringBuilder();
                    sb.append('[');
                    if (pointer == 0) {
                        int length = deque.size() - 1;
                        for (int i = 0; i < length; i++) {
                            sb.append(deque.pollFirst() + ",");
                        }
                        sb.append(deque.pollFirst());
                    }
                    else{
                        int length = deque.size() - 1;
                        for (int i = 0; i < length; i++) {
                            sb.append(deque.pollLast() + ",");
                        }
                        sb.append(deque.pollLast());
                    }
                    sb.append(']');

                    System.out.println(sb.toString());
                }
            }
            else{
                System.out.println("error");
            }
        }
    }

    static Deque<Integer> deque;
    private static void toDeque(String nums) {
        deque = new ArrayDeque<>();

        String[] split = nums.split(",");

        for (int i = 0; i < N; i++) {
            deque.add(Integer.parseInt(split[i]));
        }
    }

    private static void toArrayList(String str) {
        arrayList = new ArrayList<>();

        String[] split = str.split(",");

        for (int i = 0; i < N; i++) {
            arrayList.add(Integer.parseInt(split[i]));
        }
    }

    static boolean isError;
    private static void D(int pointer) {
        if (deque.isEmpty()) {
            isError = true;
            return;
        }

        if (pointer == 0) {
            deque.pollFirst();
        }
        else{
            deque.pollLast();
        }
    }

    static ArrayList<Integer> arrayList;

    private static ArrayList<Integer> R() {
        ArrayList<Integer> nArr = new ArrayList<>();

        for (int i = arrayList.size() - 1; i >= 0; i--) {
            nArr.add(arrayList.get(i));
        }

        return nArr;
    }
}