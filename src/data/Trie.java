package data;

/**
 * @program: PhoesLeeCode
 * @className: Trie
 * @description: 前缀树-数组存储
 * @author: lov.moran
 * @date 2020-07-17 11:30
 */
public class Trie {

    private boolean isLeaf;
    private Trie[] children;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        this.children = new Trie[26];
        this.isLeaf = false;
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        insertChar(word, 0, this);
    }

    public void insertChar(String word, int n, Trie trie) {
        if (n == word.length()) {
            this.isLeaf = true;
            return;
        }
        char input = word.charAt(n);
        if (trie.children[input - 'a'] == null) {
            trie.children[input - 'a'] = new Trie();
        }
        insertChar(word, n + 1, trie.children[input - 'a']);
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        int len = word.length();
        Trie tmp = this;
        for (int i = 0; i < len; i++) {
            char c = word.charAt(i);
            if (tmp.children[c - 'a'] == null) {
                return false;
            }
            tmp = tmp.children[c - 'a'];
        }
        return tmp.isLeaf;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String word) {
        int len = word.length();
        Trie tmp = this;
        for (int i = 0; i < len; i++) {
            char c = word.charAt(i);
            if (tmp.children[c - 'a'] == null) {
                return false;
            }
            tmp = tmp.children[c - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
