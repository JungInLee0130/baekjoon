import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int f = Integer.parseInt(st.nextToken());

        int upsideX = (c * e - f * b);
        int upsideY = (a * f - c * d);
        int downside = (a * e - b * d);

        if (downside == 0 || upsideX % downside != 0 || upsideY % downside != 0) {
            System.out.println(-1);
            return;
        }

        int x = upsideX / downside;
        int y = upsideY / downside;


        System.out.println(x + " " + y);


        br.close();
    }
}