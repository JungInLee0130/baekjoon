
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 비트마스킹 사용
public class Main {
	static int N, r, c;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		// r행 c열
		int len = 1 << N;
		
		Z(len, 0, 0); // 16
		
		
		bw.write(String.valueOf(num));
		
		br.close();
		bw.flush();
		bw.close();
	}
	private static int num = 0;
	private static void Z(int N, int row, int column) {
		if (N == 1) {
			return;
		}
		int size = N >> 1;
		if (r < row + size && c < column + size) { // 1사분면
			Z(size, row, column); // N / 2, sum + 0 * 
		}
		if (r < row + size && c >= column + size) { // 2사분면
			num += N * N / 4;
			Z(N >> 1, row, column + size); // N / 2, sum + 0 *
		} 
		if (r >= row + size && c < column + size) { // 3사분면
			num += N * N / 4 * 2;
			Z(N >> 1, row + size, column); // N / 2, sum + 0 *
		} 
		if (r >= row + size && c >= column + size){ // 4사분면
			num += N * N / 4 * 3;
			Z(N >> 1, row + size, column + size); // N / 2, sum + 0 *
		}
	}
}
