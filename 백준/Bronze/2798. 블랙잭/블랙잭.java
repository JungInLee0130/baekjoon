
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] black;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 21을 넘지않음
		// N장의 카드 -> M 외침
		// N중에 3장 고르기
		// 카드의 합이 M이 넘지않으면서 M과 최대한 가깝게
		// 3장의 합 출력
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		black = new int[N];
		for (int i = 0; i < N; i++) {
			black[i] = Integer.parseInt(st.nextToken());
		}
		
		// M하고 차중에 가장 작은것
		combination(0, 0, 0);
		
		bw.write(String.valueOf(answer));
		
		bw.flush();
		br.close();
		bw.close();
	}
	private static int answer = 0; 
	private static void combination(int cnt, int start, int sum) {
		if (cnt == 3) {
			int sub = M - sum;
			if (sub >= 0 && sub < min) { // 더 작으면 저장하고
				min = sub;
				answer = sum;
			}
			return;
		}
		for (int i = start; i < N; i++) {
			combination(cnt + 1, i + 1, sum + black[i]);
		}
	}
}
