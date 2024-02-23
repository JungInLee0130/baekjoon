import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int arr[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 이분 탐색 : 정리느낌
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(0);

        for (int i = 0; i < N; i++) {
            int num = arr[i];

            if (num > ans.get(ans.size() - 1)) {
                ans.add(num);
            } else {
                // 이분탐색을 통해 적절한 자리 찾기 -> 대체
                int left = 0;
                int right = ans.size() - 1;

                while (left < right) {
                    int mid = (left + right) / 2;

                    if (num > ans.get(mid)) {
                        left = mid + 1;
                    } else{
                        right = mid;
                    }
                }
                ans.set(right, num);
            }
        }

        System.out.println(ans.size() - 1);
    }
}