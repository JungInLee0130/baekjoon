import java.io.*;
import java.util.*;

public class Main {
    static int N;
    // 문자열 단위로
    static class Trie{
        Node root;

        public Trie(){
            root = new Node();
        }

        public void insert(String[] strs){
            Node node = root;
            for (int t = 0; t < strs.length; t++){
                String str = strs[t];
                
                if (!node.map.containsKey(str)){
                    node.map.put(str, new Node());
                }
                
                node = node.map.get(str); // 다음 노드
                node.floor = t + 1; // 층 수 저장
            }
            node.isEnd = true; // 마지막
        }

        public void printAll(Node node){
            if (node.map != null) {
                List<String> list = new ArrayList<>(node.map.keySet());
                Collections.sort(list); // 알파벳 순서로 정렬

                for (String str : list) {
                    for (int i = 0; i < node.floor; i++) {
                        sb.append("--");
                    }
                    sb.append(str + "\n");
                    printAll(node.map.get(str)); // 재귀
                }
            }
        }
    }

    static StringBuilder sb;
    static class Node{
        int floor;
        Map<String, Node> map;
        boolean isEnd;

        public Node(){
            floor = 0;
            map = new HashMap<>();
            isEnd = false;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        sb = new StringBuilder();

        Trie trie = new Trie();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());

            String[] s = new String[t];

            for (int j = 0; j < t; j++){
                s[j] = st.nextToken();
            }

            trie.insert(s);
        }

        trie.printAll(trie.root);

        System.out.println(sb);

        bw.flush();
        br.close();
        bw.close();
    }
}