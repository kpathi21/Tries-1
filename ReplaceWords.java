import java.util.List;

public class ReplaceWords {
    TrieNode root;

    static class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            this.children = new TrieNode[26];
        }
    }

    private void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
        }

        curr.isEnd = true;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        this.root = new TrieNode();
        for (String s : dictionary) {
            insert(s);
        }

        StringBuilder res = new StringBuilder();

        String[] splitArr = sentence.split(" "); // O(M*L)
        for (String word : splitArr) { //O(M)
            res.append(getShortestVersion(word));
            res.append(" ");
        }

        return res.toString().trim();
    }

    private String getShortestVersion(String word) { //O(L)
        TrieNode curr = root;

        StringBuilder sb = new StringBuilder();

        for (char c : word.toCharArray()) {
            if (curr.children[c - 'a'] == null)
                return word;

            curr = curr.children[c - 'a'];
            sb.append(c);

            if (curr.isEnd) {
                return sb.toString();
            }

        }
        return word;
    }
}

//TC: O(N*L) + O(M*L) - Trie + sentence processing
// SC: O(N*L) + O(M*L)
