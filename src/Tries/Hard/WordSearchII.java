package Tries.Hard;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearchII {
	public static void main(String[] args){
		Solution start = new Solution();
		char[][] board = {
				new char[]{'o','a','a','n'},
				new char[]{'e','t','a','e'},
				new char[]{'i','h','k','r'},
				new char[]{'i','f','l','v'},
		};
		String[] words = new String[]{
				"oath","pea","eat","rain","hklf","hf"
		};

		List<String> res = start.findWords(board, words);
		String output = "Output: [";
		for (String word : res) {
			output += word + ", ";
		}
		output += "]";
		System.out.println(output);
	}

}

class Solution {
	public List<String> findWords(char[][] board, String[] words) {
		Trie trie = new Trie();
		for (String word : words) {
			trie.addWord(word);
		}
		return trie.search(board);
	}
}


class Trie {
	TrieNode root;
	Set<String> res;

	public Trie() {
		this.root = new TrieNode();
		res = new HashSet<>();
	}

	public List<String> search(char[][]board) {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				res.addAll(
						findWords(
								root, board, row, col, new boolean[board.length][board[0].length], new HashSet<>(), ""
						));
			}
		}
		return new ArrayList<>(res);
	}

	public Set<String> findWords(TrieNode root, char[][] board, int row, int col, boolean[][] visited, Set<String> wordsFound, String word) {
		// Check if visited and edge of board
		if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
			return wordsFound;
		}
		if (visited[row][col]) {
			return wordsFound;
		}

		char ch = board[row][col];
		if (root.children[ch - 'a'] == null) {
			// no word found
			return wordsFound;
		}
		word += ch;
		// We create a deep copy of the visited array because any modification to visited affects all other word searches.
		boolean[][] visitedCopy = new boolean[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				visitedCopy[i][j] = visited[i][j];
			}
		}
		visitedCopy[row][col] = true;

		if (root.children[ch - 'a'].isWord) {
			wordsFound.add(word);
		}

		// search the board in every direction
		TrieNode cur = root.children[ch - 'a'];
		wordsFound.addAll(findWords(cur, board, row + 1, col, visitedCopy, new HashSet<>(), word));  // down
		wordsFound.addAll(findWords(cur, board, row - 1, col, visitedCopy, new HashSet<>(), word));  // up
		wordsFound.addAll(findWords(cur, board, row, col + 1, visitedCopy, new HashSet<>(), word));  // right
		wordsFound.addAll(findWords(cur, board, row, col - 1, visitedCopy, new HashSet<>(), word));  // left

		return wordsFound;
	}

	public void addWord(String word) {
		TrieNode cur = root;
		for (char c : word.toCharArray()) {
			if (cur.children[c - 'a'] == null) {
				cur.children[c - 'a'] = new TrieNode();
			}
			cur = cur.children[c - 'a'];
		}
		cur.isWord = true;
	}
}

class TrieNode {
	public TrieNode[] children;
	public boolean isWord;

	public TrieNode() {
		children = new TrieNode[26];
		isWord = false;
	}
}