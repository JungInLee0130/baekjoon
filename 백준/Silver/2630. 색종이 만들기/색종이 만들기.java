
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int[][] paper;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		N = Integer.parseInt(br.readLine());
		
		paper = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 색종이 완성
		
		color(N, 0, 0);
		
		System.out.println(white);
		System.out.println(blue);
		  
		
		
		br.close();
		bw.flush();
		bw.close();
	}
	private static int blue = 0;
	private static int white = 0;
	private static void color(int n, int row, int column) {
		boolean isOk = true;
		int color = paper[row][column];
		int size = n / 2;
		for (int i = row; i < row + n; i++) {
			for (int j = column; j < column + n; j++) {
				if (paper[row][column] == paper[i][j]) {
					continue;
				}
				isOk = false;
				break;
			}
		}
		if (!isOk) {
			color(size, row, column);
			color(size, row, column + size);
			color(size, row + size, column);
			color(size, row + size, column + size);
		} else {
			if (paper[row][column] == 1) {
				blue++;
			} else {
				white++;
			}
		}
	}
}
