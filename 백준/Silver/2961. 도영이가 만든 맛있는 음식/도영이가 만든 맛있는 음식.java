
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int min = Integer.MAX_VALUE;
	static int[] S;
	static int[] B;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());

		S = new int[N];
		B = new int[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			S[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		// n개의 조합 중 S, B
		// 가장 작은 차이
		arrS = new int[N];
		arrB = new int[N];
		powerSet(0,1,0);

		bw.write(String.valueOf(min));
		
		bw.flush();
		br.close();
		bw.close();
	}
	private static int arrS[];
	private static int arrB[];
	private static void powerSet(int cnt, int mul, int sum) {
		if (cnt == N) {
			if (sum == 0) return;
			min = Math.min(Math.abs(mul - sum), min);
			return;
		}
		
		powerSet(cnt + 1, mul * S[cnt], sum + B[cnt]);
		powerSet(cnt + 1, mul, sum);
		
	}
}
