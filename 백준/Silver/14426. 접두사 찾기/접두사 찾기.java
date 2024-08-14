import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static String[] Nstrs;
    static String[] Mstrs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Trie trie = new Trie();

        Nstrs = new String[N];
        for (int i = 0; i < N; i++) {
            Nstrs[i] = br.readLine();
            trie.insert(Nstrs[i]);
        }

        Mstrs = new String[M];
        int count = 0;
        for (int i = 0; i < M; i++) {
            Mstrs[i] = br.readLine();
            if (trie.search(Mstrs[i])){
                count++;
            }
        }


        System.out.println(count);


        bw.flush();
        br.close();
        bw.close();
    }
    static class Trie{
        Node root;

        public Trie(){
            this.root = new Node();
        }


        public void insert(String str){
            Node node = this.root;

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                node.child.putIfAbsent(c, new Node());

                node = node.child.get(c);
            }

            node.endOfWord = true;
        }

        boolean search(String str) {
            Node node = this.root;

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                if (node.child.containsKey(c)) {
                    node = node.child.get(c);
                }
                else{
                    return false;
                }
            }

            return true;
        }

        public boolean delete(String str) {
            boolean result = delete(this.root, str, 0);
            return result;
        }

        private boolean delete(Node node, String str, int idx) {
            char c = str.charAt(idx);

            if (!node.child.containsKey(c)) {
                return false;
            }

            Node cur = node.child.get(c);
            idx++;

            if (idx == str.length()) {
                if (!cur.endOfWord) {
                    return false;
                }

                cur.endOfWord = false; // 마지막 해제

                if (cur.child.isEmpty()) { // 자식없으면 삭제하고 아니면 놔둠.
                    node.child.remove(c);
                }
            }
            else{
                if (!this.delete(cur, str, idx)) {
                    return false;
                }
                if (!cur.endOfWord && cur.child.isEmpty()) {
                    node.child.remove(c); // 끝이 아니고, 자식이 없으면 삭제, 아니면 놔둠.
                }
            }
            return true;
        }
    }

    static class Node{
        HashMap<Character, Node> child;
        boolean endOfWord;

        public Node() {
            this.child = new HashMap<>();
            this.endOfWord = false;
        }
    }
}