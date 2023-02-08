
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 아홉명의 난쟁이
		// 일곱 난쟁이 키의합이 100
		// 키는 100이 넘지않음.
		// 일곱 난쟁이 키 오름차순 출력
		height = new int[9];
		arr = new int[8];
		for (int i = 0; i < 9; i++) {
			height[i] = Integer.parseInt(br.readLine());
		}
		
		combination(0,0,0);
		
		bw.flush();
		br.close();
		bw.close();
	}

	private static int[] arr;
	private static int[] height;
	private static boolean isPrint = false;
	private static void combination(int cnt, int start, int sum) {
		if (cnt == 7 && !isPrint) {
			if (sum == 100) {
				isPrint = true;
				print();
			}
			return;
		}
		for (int i = start; i < 9; i++) {
			arr[cnt] = height[i];
			combination(cnt + 1, i + 1, sum + height[i]);
		}
	}
	private static void print() {
		Arrays.sort(arr);
		for (int i = 1; i < 8; i++) {
			System.out.println(arr[i]);
		}
	}
}
