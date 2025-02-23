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
        
        // 대소관계의 개수를 저장한 dp배열
        int[] dp = new int[N];
        int[] sum = new int[N];
        
        Arrays.fill(dp, 1);
        for (int i = 0; i < N; i++) {
        	sum[i] = A[i]; // 자기자신
        }
        
        for (int i = 0; i < N; i++) {
        	for (int j = 0; j < i; j++) {
        		if (A[i] > A[j]) {
        			dp[i] += 1; // 증감연산자는 쓰지말자..
        			sum[i] = Math.max(sum[i], sum[j] + A[i]);
        		}
        	}
        }
        
        /*for (int i = 0; i < N; i++) {
        	//System.out.print(dp[i] + " "); // 이거 최대값 찾으면 최장 부분수열의 최대 길이를 찾을수있음.
        	System.out.print(sum[i] + " "); // 이거 최대값 찾으면 최장 부분수열의 최대값을 찾을수있음.
        }
        
        System.out.println();*/
        
        int max = 0;
        for (int i = 0; i < N; i++) {
        	max = Math.max(max, sum[i]);
        }
        
        System.out.println(max);


        br.close();
    }
}