import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int A, B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken()); // 시작
        B = Integer.parseInt(st.nextToken()); // 끝

        // A랑 B가 각각 어디있는지 알아내기
        // 그냥 인덱스가 1000까지있는거
        int[] nums = new int[1001]; // 1 ~ 1000
        int index = 1;
        int s = 1;
        nums[1] = 1;
        for (int i = 2; i <= B; i++) {
            if (i > index) {
                s = s + 1;
                index = index + s;
            }
            nums[i] = nums[i - 1] + s;
            //System.out.println(nums[i]);
        }

        nums[0] = 0;
        System.out.println(nums[B] - nums[A - 1]);
        /*for (int i = 2; i <= 1000; i++) {
            int index = (int)Math.pow(s, s);
            if (i < index) {
                nums[i] = s;
            }
            else{
                s++; // 인덱스 초과하면 s++
            }
        }

        // 다 대입후
        int sum = 0;
        for (int i = A; i <= B; i++) {
            sum += i;
        }

        System.out.println(sum);*/

        // 그냥 브론즈인 이유가있음... 근데 누적합으로 풀어도되고 뭐...


        
    }
}