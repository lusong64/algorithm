package org.song.java8;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Purpose of this class is to
 */

public class TrieNode {
    // storing 26 lower case alphabet.
    private final TrieNode[] children = new TrieNode[26];
    private final char value;
    private int childCount = 0;
    boolean isWord = false;

    // root TrieNode has a null c.
    public TrieNode(char c) {
        this.value = Character.toLowerCase(c);
    }

    public TrieNode() {
        value = ' ';
    }

    public void insert(String s){
        if (s == null || s.isEmpty()){
            System.out.println("insert: skip insertion on a null string or an empty string.");
            return;
        }

        insert(s.toCharArray());
    }

    private void insert (char[] chars){

        TrieNode currentTrie = this;
        int i = 0;
        while(i < chars.length){
            char currentChar = Character.toLowerCase(chars[i]);
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
            System.out.println("find: returning false on a null string or an empty string.");
            return false;

        }
        TrieNode prefix = locateThePrefix(s.toCharArray());
        return prefix!= null && prefix.isWord;
    }

    public boolean containsPrefix(String s){

        if (s==null || s.isEmpty()){
            System.out.println("find: returning false on a null string or an empty string.");
            return false;

        }
        return locateThePrefix(s.toCharArray()) != null;

    }

    private TrieNode locateThePrefix(String s){
        return locateThePrefix(s.toCharArray());
    }
    /**
     * find the trie contains the last character if the prefix exists
     * @param chars
     * @return: the trie node representing the last character in the prefix otherwise null.
     */
    private TrieNode locateThePrefix(char[] chars){
        int i = 0;
        TrieNode currentTrie = this;
        while (i < chars.length){
            char currentChar=Character.toLowerCase(chars[i]);
            TrieNode trie = currentTrie.children[currentChar - 'a'];
            if (trie == null){
                return null;
            }
            currentTrie = trie;
            i++;
        }

        return currentTrie;
    }

    public Collection<String> findAllWordsStartWithPrefix(String prefix){
        List<String> result = new LinkedList<String>();
        TrieNode trie = null;
        if (prefix == null || prefix.isEmpty()){
            System.out.println("Returning all words that are stored in the trie as the prefix is null or empty.");
            trie = this;
        }
        else{
            trie = locateThePrefix(prefix);
        }

        if (trie == null){
            return result;
        }

        trie.composeWords(result, prefix!=null?prefix.substring(0, prefix.length()-1):"");
        return result;
    }

    private void composeWords(List<String> result, String prefix){
        String newPrefix = String.format("%s%c", prefix, value);
        if (this.isWord){
            result.add(newPrefix);
        }

        if (childCount == 0){
            return;
        }
        for (TrieNode trie : children){
            if (trie != null){
                trie.composeWords(result, newPrefix);
            }
        }

    }


    public static final void main(String[] args){
        // building trie and adding words;
        TrieNode root = new TrieNode();
        String[] toBeAdded = {"word", "ward", "a", "aloha", "brain", "bryan", "ware", "life", "WAr", "war", "c", "cia"};
        for (String s : toBeAdded){
            root.insert(s);
        }


        String prefix = "war";
        System.out.println("The prefix of " + prefix + (root.containsPrefix(prefix)?" exists.":" does not exist."));

        String word = toBeAdded[4];
        System.out.println(String.format("The trie contais %s: %b", word, root.contains(word)));

        testFindWord(root, "war");
        testFindWord(root, "a");
        testFindWord(root, null);

    }

    private static void testFindWord(TrieNode root, String prefix){
        Collection<String> findResult = root.findAllWordsStartWithPrefix(prefix);
        System.out.println("The words having prefix with "+ prefix + " is/are: ");
        for (String s : findResult ){
            System.out.println(s);
        }

    }
}
