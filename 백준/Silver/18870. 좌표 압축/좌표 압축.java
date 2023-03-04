import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(bf.readLine());
		int[] number = new int[N];
		int[] count = new int[N];
		String numbers = bf.readLine();
		String[] split = numbers.split(" ");
		for (int i = 0; i < N; i++) {
			number[i] = Integer.parseInt(split[i]);
		}
		int[] number_temp = number.clone();
		Arrays.sort(number_temp);
		int[] count_temp = new int[N];
		count_temp[0] = 0;
		int less_number_count = 0;
		for (int i = 1; i < N; i++) {
			if (number_temp[i - 1] == number_temp[i]) {
				count_temp[i] = less_number_count;
			}
			else {
				less_number_count++;
				count_temp[i] = less_number_count;		
			}
		}
		
		HashMap<Integer,Integer> map = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			map.put(number_temp[i], count_temp[i]);
		}
		
		Set <Integer> keySet = map.keySet();
		for (int i = 0; i < N; i++) {
			bw.write(String.valueOf(map.get(number[i])) + " ");
		}
		bw.flush();
		bw.close();
		bf.close();
	}
}
