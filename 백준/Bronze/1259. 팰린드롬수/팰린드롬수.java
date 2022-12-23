import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String NStr = br.readLine();
        while (!NStr.equals("0")) {
            char[] charArray = NStr.toCharArray();

            boolean isPel = true;
            for (int i = 0; i < charArray.length; i++) {
                if (charArray[i] == charArray[charArray.length - 1 - i]) {
                }
                else{
                    isPel = false;
                    break;
                }
            }

            if (isPel) {
                bw.write("yes\n");
            } else {
                bw.write("no\n");
                isPel = true;
            }

            NStr = br.readLine();
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
