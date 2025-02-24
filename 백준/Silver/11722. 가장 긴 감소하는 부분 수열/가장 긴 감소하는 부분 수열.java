import java.io.*;
import java.util.*;

class Main {
    static StringTokenizer st;
    static int N;
    static int[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        
        st = new StringTokenizer(br.readLine());
        
        A = new int[N]; // 1000이하
        for (int i = 0; i < N; i++) {
        	A[i] = Integer.parseInt(st.nextToken()); // 1000이하
        }
        
        // 가장 긴 감소하는 부분수열의 길이
        
        int[] lens = new int[N];

        Arrays.fill(lens, 1);
        
        for (int i = 0; i < N; i++) {
        	for (int j = 0; j < i; j++) {
        		if (A[i] < A[j]) {
        			lens[i] = Math.max(lens[j] + 1, lens[i]);
        		}
        	}
        }
        
        int max = 0;
        
        for (int i = 0; i < N; i++) {
        	max = Math.max(max, lens[i]);
        }

        System.out.println(max);

        br.close();
    }
}