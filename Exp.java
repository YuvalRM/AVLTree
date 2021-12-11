import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Exp {
	public static void main(String[] args) {
		// 1st EXP
		System.out.println("first exp");
		int[][] rands = new int[5][];
		int[][] rev = new int[5][];
		int[] randSw = new int[5];
		int[] revSw = new int[5];
		Random rand= new Random();
		for (int i = 0; i < 5; i++) {
			rands[i] = buildRand((int) (1000 * Math.pow(2, i + 1)));
			rev[i] = buildReverse((int) (1000 * Math.pow(2, i + 1)));
			randSw[i] = checkSwaps(rands[i]);
			revSw[i] = checkSwaps(rev[i]);
		}

		AVLTree[][] trees = new AVLTree[2][5];
		int[][] counts = new int[2][5];

		for (int i = 0; i < 5; i++) {
			AVLTree tree = new AVLTree();
			int count = 0;
			for (int j = 0; j < rands[i].length; j++) {
				count += insert(tree, rands[i][j], null);
			}
			trees[0][i] = tree;
			counts[0][i] = count;
		}

		for (int i = 0; i < 5; i++) {
			AVLTree tree = new AVLTree();
			int count = 0;
			for (int j = 0; j < rev[i].length; j++) {
				count += insert(tree, rev[i][j], null);
			}
			trees[1][i] = tree;
			counts[1][i] = count;
		}

		for (int i = 0; i < 5; i++) {
			System.out.println("Random tree " + String.valueOf(i));
			System.out.println(counts[0][i]);
			System.out.println("It's swaps are:");
			System.out.println(randSw[i]);

		}

		for (int i = 0; i < 5; i++) {
			System.out.println("Reverse tree " + String.valueOf(i));
			System.out.println(counts[1][i]);
			System.out.println("It's swaps are:");
			System.out.println(revSw[i]);
		}

		// 2nd EXP
		System.out.println("second exp");
		rands = new int[10][];

		for (int i = 0; i < 10; i++) {
			System.out.println("The "+String.valueOf(i+1) + " Tree");
			rands[i] = buildRand((int) (1000 * Math.pow(2, i + 1)));

		}
		trees = new AVLTree[2][10];
		for (int i = 0; i < 10; i++) {
			System.out.println("The "+String.valueOf(i+1) + " Tree");
			AVLTree tree = new AVLTree();
			for (int j = 0; j < rands[i].length; j++) {
				insert(tree, rands[i][j], null);
			}
			trees[0][i] = tree;
			tree = new AVLTree();
			for (int j = 0; j < rands[i].length; j++) {
				insert(tree, rands[i][j], null);
			}
			trees[1][i] = tree;
		}
		
		for (int i =0; i<10;i++) {
			System.out.println("The "+String.valueOf(i+1) + " Tree");
			assert(trees[0][i].size()==(1000 * Math.pow(2, i + 1)));
			assert(trees[1][i].size()==(1000 * Math.pow(2, i + 1)));
			int val=rand.nextInt((int) (1000 * Math.pow(2, i + 1)));
			int pred=trees[0][i].getRoot().getKey()-1;
			
			System.out.println("the random ");
			System.out.println("the max");
			boolean[] random = new boolean[0];
			System.out.println(random[1]);
			System.out.println("the mean");
			System.out.println(random[2]);
			System.out.println("the min max ");
			System.out.println("the max");
			boolean[] predo = new boolean[0];
			System.out.println(predo[1]);
			System.out.println("the mean");
			System.out.println(predo[2]);
			
			
		}

	}

	public static int insert(AVLTree tree, int k, String val) {
		int ret = search(tree, k);
		tree.insert(k, val);
		return ret;
	}

	public static int search(AVLTree tree, int key) {
		int dist = 0 ;
		AVLTree.IAVLNode node = tree.getMax();
		if(node==null) {
			return 0;
		}
		AVLTree.IAVLNode parent = node.getParent();
		
		while (parent != null && parent.getKey() > key) {
			node = parent;
			parent=node.getParent();
			dist++;
		}
		while (node != null && node.isRealNode()) {
			if (node.getKey() == key) {
				return dist;
			}
			if (node.getKey() < key) {
				node = node.getRight();
			} else {
				node = node.getLeft();
			}
			dist++;
		}
		return dist;
	}

	public static int[] buildReverse(int k) {
		int[] arr = new int[k];
		for (int i = 0; i < k; i++) {
			arr[i] = k - i;
		}
		return arr;
	}

	public static int[] buildRand(int k) {
		int[] arr = new int[k];
		List<Integer> vals = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			vals.add(i);
		}
		Random rand = new Random();
		for (int i = 0; i < k; i++) {
			int x = rand.nextInt(k - i);
			arr[i] = vals.remove(x);
		}
		return arr;
	}

	public static int checkSwaps(int[] arr) {
		int cnt = 0;
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (arr[i] > arr[j]) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	public static int depth(AVLTree tree, int k) {
		int cnt = 0;
		AVLTree.IAVLNode node = tree.getRoot();
		while (node.getKey() != k) {
			cnt++;
			if (node.getKey() > k) {
				node = node.getLeft();
			} else {
				node = node.getRight();
			}
			if (!node.isRealNode()) {
				return -1;
			}
		}
		return cnt;
	}
}
