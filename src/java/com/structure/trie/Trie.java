package com.structure.trie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {
    private static class TrieNode {
        private Map<Character, TrieNode> nodes;
        private boolean isWord;

        TrieNode() {
            nodes = new HashMap<>();
            isWord = false;
        }
    }

    private TrieNode root;

    private Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode insertRoot = root;
        for(char ch : word.toCharArray()) {
            if(!insertRoot.nodes.containsKey(ch)) {
                insertRoot.nodes.put(ch, new TrieNode());
            }
            insertRoot = insertRoot.nodes.get(ch);
        }
        insertRoot.isWord = true;
    }

    public void insert(List<String> words) {
        words.forEach(this::insert);
    }

    public boolean search(String word) {
        TrieNode searchRoot = root;
        for(char ch : word.toCharArray()) {
            if(!searchRoot.nodes.containsKey(ch)) {
                return false;
            }
            searchRoot = searchRoot.nodes.get(ch);
        }
        return searchRoot.isWord;
    }

    public TrieNode getPrefixNode(String prefix) {
        TrieNode getPrefixRoot = root;
        for(char ch : prefix.toCharArray()) {
            if(!getPrefixRoot.nodes.containsKey(ch)) {
                return null;
            }
            getPrefixRoot = getPrefixRoot.nodes.get(ch);
        }
        return getPrefixRoot;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("Hello");
        trie.insert("Hellowww");
        trie.insert("Helqqq");

        System.out.println(trie.search("Hello"));
        System.out.println(trie.search("Hellowww"));
    }
}
