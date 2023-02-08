import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	private static int L,C;
	static char[] letter;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 최소 한개의 모음,두개의 자음
		// 알파벳 오름차순 배열
		// C가지
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		// 문자 대입 끝
		letter = new char[C];
		for (int i = 0; i < C; i++) {
			letter[i] = st.nextToken().charAt(0); 
		}
		// letter -> 사전순 나열 -> 그냥 arrays.sort 쓰면됨.
		Arrays.sort(letter);
		// 조건
		// 1. 오름차순
		// 2. 최소 한개의 모음
		// 3. 최소 두개의 자음
		// 소거법 사용
		// C개에서 L개만큼 선택,순서있게 나열 -> Permutation
		isSelected = new boolean[C];
		alpha = new char[L];
		Combi(0,0);
		
		
		bw.flush();
		br.close();
		bw.close();
	}
	private static boolean[] isSelected;
	private static char[] alpha;
	private static void Combi(int cnt, int start) {
		if (cnt == L) { // L개 선택하면 이제 검사.
			// 1. 오름차순 해결.-> 조합
			// 2. 자음, 모음검사
			if(isVowel()) {
				print();
			}
			return;
		}
		for (int i = start; i < C; i++) { // 0~C-1까지 검사
			alpha[cnt] = letter[i];
			// 애초에 오름차순은 안됨. 그럼 꺼꾸로 가면안되는데? 조합?
			Combi(cnt + 1, i + 1);
			alpha[cnt] = '0';
		}
	}
	private static void print() {
		for (int i = 0; i < alpha.length; i++) {
			System.out.print(alpha[i]);
		}
		System.out.println();
	}
	private static boolean isVowel() {
		int count1 = 0;
		int count2 = 0;
		for (int i = 0; i < alpha.length; i++) {
			if (alpha[i] == 'a' 
					|| alpha[i] == 'e'
					|| alpha[i] == 'i'
					|| alpha[i] == 'o'
					|| alpha[i] == 'u') {
				count1++;
                continue;
			}
			count2++;
		}
		
		if (count1 >= 1 && count2 >= 2) {
			return true;
		}
		return false;
	}
}
