import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, S;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int size = N / 2; // 2^40은 int범위를 초과


        int[] a = new int[1 << (N - size)];
        int[] b = new int[1 << size];

        // 둘로 나눈 부분수열의 합구하기
        for (int i = 0; i < (1 << (N - size)); i++) {
            for (int j = 0; j < N - size; j++) {
                if ((i & (1 << j)) == (1 << j)) {
                    a[i] += arr[j];
                }
            }
        }
        

        for (int i = 0; i < (1 << size); i++) {
            for (int j = 0; j < size; j++) {
                if ((i & (1 << j)) == (1 << j)) {
                    b[i] += arr[j + (N - size)];
                }
            }
        }


        Arrays.sort(a);
        Arrays.sort(b);

        int ap = 0;
        int bp = b.length - 1;

        long cnt = 0;

        while (ap < a.length && bp > -1) {
            int av = a[ap];
            int bv = b[bp];

            // 정렬했으니가 값이 같은 중복인덱스 찾기..
            // 투포인터는 범위에 해당하는 값 구할때도 용이하지만
            // 정렬을 하고 구하면 범위에 해당하는 값을 구하기 용이함.
            // 하지만 중복되는것은 구하지 못하는 단점이 있는데
            // (왼쪽인덱스가 증가했는데 알고보니 왼쪽을 증가 안시키고 오른쪽 감소, 왼쪽 감소 하면 해당값이 또 나온다거나)
            // 그래서 그걸 찾기위해 보강하는 방법같음
            if (av + bv == S) { // 값이 S인것
                long ac = 0;
                long bc = 0;

                while (ap < a.length && av == a[ap]) {
                    ac++; // 카운트.... // av랑 같은값
                    ap++;
                }

                while (bp > -1 && bv == b[bp]) {
                    bc++; // 카운트.. // bv랑 같은값
                    bp--;
                }

                cnt += ac * bc; // 카운트 곱셈(경우의수 곱셈)
            }

            // 이게
            // S보다 작음 : 큰것을 증가시킴
            // S보다 큼 : 작은것 더하기
            // 이렇게 되면 둘다 양수일때 후자가 일단걸림.
            // 둘다 음수일때 일단 전자가 걸림
            // S보다 작음 : 작은것 덜어내기
            // S보다 큼  : 큰것 덜어내기
            // 이건 되는데?
            // 어쨋든 전자로하면 시작인덱스가
            // ap = 0
            // bp = N - 1
            // 이기때문에 포인터가 바깥쪽으로 향함
            // 후자는 안쪽으로 향하면서 N만큼 돔.
            // 하.. 투포인터에대해 잘못생각한듯.
            if (av + bv < S) {
                ap++; // 작은것 덜어내기
            } else if (av + bv > S) {
                bp--; // 큰것 감소
            }
        }

        if (S == 0) cnt--; // 부분집합중에 공집합 제거
        // 예시의 경우는
        // cnt += (공집 * 공집) : 1
        // cnt += (3 * -3인경우) : 1
        // cnt == 2 - 1 == 1

        System.out.println(cnt);

        br.close();
    }

    static int answer = 0;

    static class Segment{
        long[] tree;
        int treeSize;

        public Segment(int arrSize){
            int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2)); // h = logN

            this.treeSize = (int) Math.pow(2, h + 1);

            tree = new long[treeSize];
        }

        public long init(long[] arr, int node, int start, int end){
            if (start == end) {
                return tree[node] = arr[start];
            }

            return tree[node] = init(arr, node * 2, start, (start + end) / 2)
                    + init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
        }

        public long sum(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return 0;
            }

            if (left <= start && end <= right) {
                return tree[node];
            }

            return sum(node * 2, start, (start + end) / 2, left, right)
                    + sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        }
    }
}