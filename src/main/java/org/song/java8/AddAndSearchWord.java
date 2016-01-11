package org.song.java8;

/**
 * Purpose of this class is to
 */
public class AddAndSearchWord {
    private final TrieNode root = new TrieNode();
    // Adds a word into the data structure.

    public void addWord(String word) {
        root.insert(word);
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return root.contains(word);
    }

    private static class TrieNode {
        // storing 26 lower case alphabet.
        private final static char ROOT_CHAR = ' ';

        private final TrieNode[] children = new TrieNode[26];
        private final char value;
        private int childCount = 0;
        boolean isWord = false;



        // root TrieNode has a null c.
        public TrieNode(char c) {
            this.value = Character.toLowerCase(c);
        }

        public TrieNode() {
            value = ROOT_CHAR;
        }

        public void insert(String s){
            if (s == null || s.isEmpty()){
                return;
            }

            insert(s.toCharArray());
        }

        private void insert (char[] chars){

            TrieNode currentTrie = this;
            int i = 0;
            while(i < chars.length){
                char currentChar = chars[i];
                TrieNode trie = currentTrie.children[currentChar-'a'];
                if (trie == null){
                    trie = new TrieNode(currentChar);
                    currentTrie.children[currentChar-'a'] = trie;
                    currentTrie.childCount++;
                }
                currentTrie = trie;
                i++;
            }
            currentTrie.isWord = true;
        }

        /**
         * Return whether the trie contains the word s no matter the final node has no children.
         * @param s
         * @return
         */
        public boolean contains(String s){
            if (s==null || s.isEmpty()){
                return false;
            }
            return locateThePrefix(0, s.toCharArray());
        }

        /**
         * find the trie contains the last character if the prefix exists
         * @param chars
         * @return: the trie node representing the last character in the prefix otherwise null.
         */
        private boolean locateThePrefix(int i, char[] chars){

            if (this.value == ROOT_CHAR){

                if (childCount == 0){
                    return false;
                }

                if (chars[i] == '.'){
                    for (TrieNode node : children){
                        if (node !=null && node.locateThePrefix(i, chars)){
                            return true;
                        }
                        return false;
                    }
                }
                else{
                    TrieNode possible = children[chars[i]-'a'];
                    return possible == null?false:possible.locateThePrefix(i, chars);
                }

            }

            if (i>=chars.length){
                return true;
            }
            else if (i==chars.length-1){
                return (chars[i] == '.' || value==chars[i]) && isWord;
            }

            if ((chars[i] == value || chars[i] == '.') && childCount>0){

                for (TrieNode node : children){
                    if (node !=null && node.locateThePrefix(i+1, chars)){
                        return true;
                    }
                }

            }

            return false;

        }
    }

    public static void main(String[] args){
        AddAndSearchWord ad = new AddAndSearchWord();
        /*
        ad.addWord("a");
        System.out.println ("return " + ad.search("a"));
        */
        /*
        ad.addWord("ab");
        System.out.println ("return " + ad.search("a."));
        */
        ad.addWord("a");
        ad.addWord("a");
        System.out.println ("return " + ad.search(".a"));

}

}
