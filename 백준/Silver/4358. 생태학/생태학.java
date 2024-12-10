import java.io.*;
import java.util.*;

public class Main {
    static class Trie{
        Node root;

        int count;

        public Trie() {
            this.root = new Node();
        }


        public void insert(String str){
            Node node = root;
            node.map.put(str, node.map.getOrDefault(str, 0) + 1);
            count++;
        }

        // 재귀방식으로 print
        public void print(Node root){
            if (root.map != null) {
                List<String> list = new ArrayList<>(root.map.keySet());
                Collections.sort(list); // 사전순으로 정렬

                for(String s : list){
                    sb.append(s + " ");
                    sb.append(String.format("%.4f\n", (double)root.map.get(s) / count * 100.0));
                }
            }
        }
    }

    static StringBuilder sb;
    static class Node{
        Map<String, Integer> map;

        public Node() {
            this.map = new HashMap<>();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        sb = new StringBuilder();

        Trie trie = new Trie();

        // 최대 ??개까지 입력받을때, 딱히 그 수가 정해져있지않을때
        while (true) { // 이건 true로 두고
            String str = br.readLine();
            // 요기 이렇게 : null || length == 0 검증
            if (str == null || str.length() == 0) break;
            trie.insert(str);
        }

        trie.print(trie.root);

        System.out.println(sb.toString());

        bw.flush();
        br.close();
        bw.close();
    }
}