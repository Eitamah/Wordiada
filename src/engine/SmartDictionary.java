//package engine;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class SmartDictionary {
//
//  class TrieNode {
//    char value;
//    List<TrieNode> children = new ArrayList<>();
//    String word;
//
//    public TrieNode() {
//    }
//
//    public TrieNode(char val) {
//      this.value = val;
//    }
//
//    public void add(char[] array) {
//      add(array, 0);
//    }
//
//    public void add(char[] array, int offset) {
//      for (TrieNode child : children) {
//        if (child.value == array[offset]) {
//          child.add(array, offset + 1);
//          return;
//        }
//      }
//      TrieNode trieNode = new TrieNode(array[offset]);
//      children.add(trieNode);
//      if (offset < array.length - 1) {
//        trieNode.add(array, offset + 1);
//      } else {
//        trieNode.word = new String(array);
//      }
//    }    
//  }
//
//  private TrieNode root = new TrieNode();
//
//  public LongestWord() {
//    List<String> asList = Arrays.asList("abacus", "deltoid", "gaff", "giraffe",
//        "microphone", "reef", "qar");
//    for (String word : asList) {
//      root.add(word.toCharArray());
//    }
//  }
//
//  public String search(char[] cs) {
//    return visit(root, cs);
//  }
//
//  public String visit(TrieNode n, char[] allowedCharacters) {
//    String bestMatch = null;
//    if (n.children.isEmpty()) {
//      // base case, leaf of the trie, use as a candidate
//      bestMatch = n.word;
//    }
//
//    for (TrieNode child : n.children) {
//      if (contains(allowedCharacters, child.value)) {
//        // remove this child's value and descent into the trie
//        String result = visit(child, remove(allowedCharacters, child.value));
//        // if the result wasn't null, check length and set
//        if (bestMatch == null || result != null
//            && bestMatch.length() < result.length()) {
//          bestMatch = result;
//        }
//      }
//    }
//    // always return the best known match thus far
//    return bestMatch;
//  }
//
//  private char[] remove(char[] allowedCharacters, char value) {
//    char[] newDict = new char[allowedCharacters.length - 1];
//    int index = 0;
//    for (char x : allowedCharacters) {
//      if (x != value) {
//        newDict[index++] = x;
//      } else {
//        // we removed the first hit, now copy the rest
//        break;
//      }
//    }
//    System.arraycopy(allowedCharacters, index + 1, newDict, index,
//        allowedCharacters.length - (index + 1));
//
//    return newDict;
//  }
//
//  private boolean contains(char[] allowedCharacters, char value) {
//    for (char x : allowedCharacters) {
//      if (value == x) {
//        return true;
//      }
//    }
//    return false;
//  }
//
//  public static void main(String[] args) {
//    LongestWord lw = new LongestWord();
//    String longestWord = lw.search(new char[] { 'a', 'e', 'f', 'f', 'g', 'i',
//        'r', 'q' });
//    // yields giraffe
//    System.out.println(longestWord);
//  }
//
//}