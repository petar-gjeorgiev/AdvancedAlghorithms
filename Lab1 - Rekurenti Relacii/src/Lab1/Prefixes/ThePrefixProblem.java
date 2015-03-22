package Lab1.Prefixes;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ThePrefixProblem {

	public static void main(String[] args) throws Exception {
		int i;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Trie t = new Trie();

		for (i = 0; i < N; i++) {
			t.insert(br.readLine());
		}

		String[] numbers = br.readLine().split(" ");

		br.close();

		int D = Integer.parseInt(numbers[0]);
		int M = Integer.parseInt(numbers[1]);
		t.countPrefixes(t.root, D, M);

		System.out.println(Counter.COUNT);
	}
}

class Counter {
	static int COUNT = 0;
}

class TrieNode {

	int value;
	int count;
	TrieNode next[];

	TrieNode() {
		next = new TrieNode[26];
		count = 1;
	}
}

class Trie {

	TrieNode root;
	List<String> prefixes;

	Trie() {
		root = new TrieNode();
		prefixes = new ArrayList<String>();
	}

	void insert(String s) {
		insertR(root, s);
	}

	void countPrefixes(TrieNode tn, int D, int M) {

		if (D == 0) {
			if (tn.count >= M) {
				Counter.COUNT++;
			}
			return;
		}

		for (int i = 0; i < tn.next.length; i++) {
			if (tn.next[i] != null) {
				countPrefixes(tn.next[i], D - 1, M);
			}
		}
	}

	void insertR(TrieNode tn, String s) {
		if (s.length() == 0) {
			return;
		}

		char c = s.charAt(0);
		int ci = (int) c - (int) 'a';

		if (tn.next[ci] == null) {
			tn.next[ci] = new TrieNode();
		} else {
			tn.next[ci].count++;
		}

		insertR(tn.next[ci], s.substring(1));
	}

}