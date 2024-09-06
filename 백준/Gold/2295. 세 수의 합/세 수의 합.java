import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int N;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        nums = new int[N]; // 1000개
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine()); // 2억이하
        }

        twoSums = new int[N * (N + 1) / 2];
        int k = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                twoSums[k++] = nums[i] + nums[j]; // 두수의 합을 저장해 놓음.
            }
        }

        Arrays.sort(twoSums);


        max = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (binarySearch(nums[i] - nums[j], k)){
                    max = Math.max(max, nums[i]);
                }
            }
        }

        System.out.println(max);

        bw.flush();
        bw.close();
        br.close();
    }

    private static int[] twoSums;

    private static int max;

    private static boolean binarySearch(int value, int k) {
        int low = 0;
        int high = k - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            
            if (twoSums[mid] == value){
                return true;
            }
            else if (twoSums[mid] > value) {
                high = mid - 1; // 줄이기
            } else {
                low = mid + 1; // 늘리기
            }
        }
        return false;
    }

}