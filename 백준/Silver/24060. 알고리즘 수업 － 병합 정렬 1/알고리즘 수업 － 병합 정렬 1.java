import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int K;
    private static int saveCount = 0;
    private static int saveNum;
    private static void mergeSort(int[] A, int left, int right) {
        if (left < right) { // start가 end보다 작으면
            int mid = (left + right) / 2; // 중간 구함. A 배열의 길이가 1이될때까지 분할(divide)
            mergeSort(A, left, mid); // 시작과 중간 머지소트. // (conquer)
            mergeSort(A, mid + 1, right); // 중간과 끝 머지소트
            merge(A, left, mid, right); // 결합 (combine)
        }
    }

    private static int[] sorted;
    private static void merge(int[] A, int left, int mid, int right) {
        int l = left;
        int r = mid + 1;
        int index = left;

        while (l <= mid && r <= right) { // start <= mid && mid + 1 <= end -> 두개의 배열로 나눈다고 생각하면됨.
            // A[start] <= A[mid + 1]일경우 : 각각의 첫번째 원소 비교
            if (A[l] <= A[r]){ // 첫번째 배열쪽에 있는 원소가 더 작은경우
                // tmp 임시배열에 첫번째 배열 첫원소를 넣어줌
                sorted[index] = A[l];
                index++;
                l++;
            }
            else{ // 두번째가 더 큰경우
                sorted[index] = A[r]; // 두번째 쪽을 tmp 임시배열쪽에 넣어줌
                index++;
                r++;
            }
        }
        while (l <= mid) { // 오른쪽이 먼저 채워줬을경우
            // 왼쪽거 중 남은거를 다 채워줌.
            sorted[index] = A[l];
            index++;
            l++;
        }
        while (r <= right) { // 왼쪽이 먼저 참.
            // 오른쪽 거 중 남은거를 채워줌.
            sorted[index] = A[r];
            index++;
            r++;
        }

        // 정렬된 새배열을 기존의 배열에 복사해서 옮겨줌.
        for (int i = left; i <= right; i++) {
            A[i] = sorted[i];
                saveCount++;
            if (saveCount == K) {
                saveNum = sorted[i];
            }
        }
    }

    private static int[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");

        A = new int[N];
        sorted = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        mergeSort(A,0, N - 1);

        if (saveCount < K) {
            bw.write(String.valueOf("-1"));
        }
        else{
            bw.write(String.valueOf(saveNum));
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
