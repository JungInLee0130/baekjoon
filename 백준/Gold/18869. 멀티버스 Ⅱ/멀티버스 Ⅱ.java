import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int M, N;
    static List<List<Integer>> planets;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        planets = new ArrayList<>(); // M개의 우주, N개의 행성

        for (int i = 0; i < M; i++) { // 100
            st = new StringTokenizer(br.readLine());

            List<Integer> planetList = new ArrayList<>();
            Set<Integer> planetSet = new HashSet<>();
            for (int j = 0; j < N; j++) { // 1만
                int size = Integer.parseInt(st.nextToken()); // 100만이하
                planetList.add(size); // size 대입
                planetSet.add(size); // 좌표 압축 : 등수 매기기
            }

            List<Integer> setList = planetSet.stream()
                    .collect(Collectors.toList());
            Collections.sort(setList); // 정렬

            // 몇 번째인지 이분탐색으로 찾음
            for (int j = 0; j < N; j++) {
                // planetList.get(j)의 원소가 몇번째냐
                int idx = Collections.binarySearch(setList, planetList.get(j));
                planetList.set(j, idx); // 몇번째인지로 대체
            }
            planets.add(planetList);
        }

        // 정렬하면 인덱스 순서가 같다는 점을 이용
        // 같은수가 있어도 인덱스 다르게 나옴.
        // 이분탐색으로 시간복잡도 감소시킴

        int cnt = 0;
        for (int i = 0; i < M; i++) {
            for (int j = i + 1; j < M; j++) { // 두 우주를 선택.
                if (Arrays.equals(planets.get(i).toArray(), planets.get(j).toArray())){
                    cnt++;
                }
            }
        }

        System.out.println(cnt);

        bw.flush();
        bw.close();
        br.close();
    }
}