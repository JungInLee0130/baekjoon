import java.io.*;
import java.nio.Buffer;
import java.util.*;

public class Main {
    static class Person {
        int curMoney;
        int stockCount;

        public Person(int curMoney, int stockCount) {
            this.curMoney = curMoney;
            this.stockCount = stockCount;
        }
    }

    static int[] stockPrice = new int[15]; // 1 ~ 14
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int money = Integer.parseInt(br.readLine());

        Person Jun = new Person(money, 0);
        Person Sung = new Person(money, 0);

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= 14; i++) {
            stockPrice[i] = Integer.parseInt(st.nextToken());
        }

        // 준현
        for (int i = 1; i <= 14; i++) {
            int q = Jun.curMoney / stockPrice[i];
            int r = Jun.curMoney % stockPrice[i];
            Jun.curMoney = r;
            Jun.stockCount += q;
        }

        // 성민
        for (int i = 4; i <= 14; i++) {
            // 3일 연속 상승 -> 전량 매도
            if (stockPrice[i - 3] < stockPrice[i - 2]
                    && stockPrice[i - 2] < stockPrice[i - 1]) {
                Sung.curMoney += stockPrice[i] * Sung.stockCount;
                Sung.stockCount = 0;
            }
            // 3일 연속 하락
            if (stockPrice[i - 3] > stockPrice[i - 2]
                    && stockPrice[i - 2] > stockPrice[i - 1]) {
                int q = Sung.curMoney / stockPrice[i];
                int r = Sung.curMoney % stockPrice[i];

                Sung.stockCount += q;
                Sung.curMoney = r;
            }
        }

        int totalJun = Jun.curMoney + Jun.stockCount * stockPrice[14];
        int totalSung = Sung.curMoney + Sung.stockCount * stockPrice[14];
        if (totalJun == totalSung) {
            System.out.println("SAMESAME");
        } else if (totalJun > totalSung) {
            System.out.println("BNP");
        } else {
            System.out.println("TIMING");
        }

        br.close();
    }
}