import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int N, M;
    static int[] parent;
    static ArrayList<Integer>[] friends;
    static ArrayList<Integer>[] enemies;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N + 1]; // 1 ~ N
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        friends = new ArrayList[N + 1]; // 1 ~ N
        enemies = new ArrayList[N + 1]; // 1 ~ N

        for (int i = 1; i <= N; i++) {
            friends[i] = new ArrayList<>();
            enemies[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // F : friend E : enemy
            // 유니온 파인드
            // 최대 팀 수 구하기
            String s = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (s.equals("F")) {
                friends[a].add(b);
            } else if (s.equals("E")) {
                enemies[a].add(b);
                enemies[b].add(a);
            }
        }

        for (int i = 1; i <= N; i++) {
            if (enemies[i].isEmpty()) {
                continue;
            }
            Integer enemy = enemies[i].get(0);

            for (int j = 1; j < enemies[i].size(); j++) {
                Integer friend = enemies[i].get(j);
                if (friends[enemy].contains(friend)) {
                    continue;
                }
                friends[enemy].add(friend);
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < friends[i].size(); j++) {
                Integer friend = friends[i].get(j);

                int x = find(i);
                int y = find(friend);
                if (x != y) {
                    union(x, y);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            parent[i] = find(i);
        }

        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            hashSet.add(parent[i]);
        }

        bw.write(String.valueOf(hashSet.size()));

        bw.flush();
        bw.close();
        br.close();
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x < y) {
            parent[y] = x;
        }
        else{
            parent[x] = y;
        }
    }

    private static int find(int x) {
        if (x != parent[x]) return parent[x] = find(parent[x]);
        return x;
    }
}