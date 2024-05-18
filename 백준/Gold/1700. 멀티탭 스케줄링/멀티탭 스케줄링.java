import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static int N, K;
    static int[] order;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        order = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }


        boolean[] isUse = new boolean[101];
        int put = 0; // 현재 꽃혀있는 콘센트
        int ans = 0; // 콘센트를 뺀 횟수
        for (int i = 0; i < K; i++) {
            int temp = order[i];

            if (!isUse[temp]) { // 사용중이지 않으면
                if (put < N) { // 공간있음
                    isUse[temp] = true;
                    put++;
                }
                else{ // 공간 없음 : 나중에도 사용되는지 여부 검사
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    for (int j = i; j < K; j++) {
                        int afterOrder = order[j];
                        if (isUse[afterOrder] && !arrayList.contains(afterOrder)) { // 현재 꽃혀있는 콘센트
                            arrayList.add(afterOrder);
                        }
                    }

                    if (arrayList.size() != N) { // N보다 작은 경우 : N보다 적게 사용됨
                        for (int j = 0; j < 101; j++) {
                            if (isUse[j] && !arrayList.contains(j)) { // 재사용되지않는 콘센트 제거
                                isUse[j] = false;
                                break;
                            }
                        }
                    }
                    else{ // 같음
                        int remove = arrayList.get(arrayList.size() - 1); // 가장 나중에 사용되는 인덱스 제거
                        isUse[remove] = false;
                    }

                    isUse[temp] = true; // 꽃기
                    ans++; // 공간없어서 뽑았기 때문에 ans++
                }
            }
        }

        bw.write(String.valueOf(ans));

        bw.flush();
        bw.close();
        br.close();
    }
}