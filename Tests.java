import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tests {
	public static void main(String[] arg) {
		AVLTree tree = new AVLTree();
		AVLTree tree2 = new AVLTree();
		tree.insert(3, "3");
		tree.insert(1, "1");
		tree.insert(7, "7");
		tree.insert(6, "6");
		tree.insert(2, "2");
		tree.insert(15, "15");
		tree.insert(10, "!!!");
		tree.insert(10, "26");
		tree2.insert(21, "3");
		tree2.insert(52, "1");
		tree2.insert(31, "7");
		tree2.insert(718, "6");
		tree2.insert(100, "2");
		tree2.insert(81, "15");
		tree2.insert(42, "!!!");
		tree2.insert(73, "26");
		tree2.insert(91, "7");
		tree2.insert(728, "6");
		tree2.insert(103, "2");
		tree2.insert(81, "15");
		tree2.insert(421, "!!!");
		tree2.insert(714, "26");
		tree2.insert(301, "7");
		tree2.insert(715, "6");
		tree2.insert(150, "2");
		tree2.insert(411, "15");
		tree2.insert(442, "!!!");
		tree2.insert(713, "26");
		tree2.insert(391, "7");
		tree2.insert(738, "6");
		tree2.insert(109, "2");
		tree2.insert(834, "15");
		tree2.insert(432, "!!!");
		tree2.insert(61, "26");
		System.out.println(Arrays.toString(tree.keysToArray()));
		System.out.println(Arrays.toString(tree.infoToArray()));
		System.out.println(Arrays.toString(tree2.keysToArray()));
		System.out.println(Arrays.toString(tree2.infoToArray()));
		System.out.println("This is Min");
		System.out.println(tree.min());

		System.out.println(tree.delete(1));
		AVLTree.IAVLNode node2 = tree.goTo(7);
		System.out.println(node2.isRealNode());
		System.out.println("This is Min");
		System.out.println(tree.min());

		System.out.println(tree.getRoot().getKey());
		System.out.println(tree.getRoot().getSize());

		System.out.println(Arrays.toString(tree.keysToArray()));
		System.out.println(Arrays.toString(tree.infoToArray()));

		System.out.println(tree.delete(3));
		System.out.println(tree.getRoot().getKey());
		System.out.println(tree.getRoot().getSize());

		System.out.println(Arrays.toString(tree.keysToArray()));
		System.out.println(Arrays.toString(tree.infoToArray()));

		System.out.println(tree.delete(1));
		System.out.println(tree.getRoot().getKey());
		System.out.println(tree.getRoot().getSize());

		System.out.println(Arrays.toString(tree.keysToArray()));
		System.out.println(Arrays.toString(tree.infoToArray()));

		System.out.println(tree.delete(1));
		System.out.println(tree.getRoot().getKey());
		System.out.println(tree.getRoot().getSize());

		System.out.println(Arrays.toString(tree.keysToArray()));
		System.out.println(Arrays.toString(tree.infoToArray()));

		System.out.println(tree.delete(6));
		System.out.println(tree.getRoot().getKey());
		System.out.println(tree.getRoot().getSize());

		System.out.println(Arrays.toString(tree.keysToArray()));
		System.out.println(Arrays.toString(tree.infoToArray()));

		System.out.println(tree.insert(4, "wow"));
		System.out.println(tree.getRoot().getKey());
		System.out.println(tree.getRoot().getSize());

		System.out.println(Arrays.toString(tree.keysToArray()));
		System.out.println(Arrays.toString(tree.infoToArray()));

		System.out.println(tree.delete(15));
		System.out.println(tree.getRoot().getKey());
		System.out.println(tree.getRoot().getSize());
		System.out.println("This is Min");
		System.out.println(tree.min());

		System.out.println(Arrays.toString(tree.keysToArray()));
		System.out.println(Arrays.toString(tree.infoToArray()));
		System.out.println("This is Min Tree 2");
		System.out.println(tree2.min());
		AVLTree seventeen = new AVLTree();
		seventeen.insert(17, "no");
		AVLTree.IAVLNode x = seventeen.goTo(17);
		System.out.println("before join t1");
		System.out.println(Arrays.toString(tree.keysToArray()));
		System.out.println(Arrays.toString(tree.infoToArray()));
		System.out.println("before join t2");
		System.out.println(Arrays.toString(tree2.keysToArray()));
		System.out.println(Arrays.toString(tree2.infoToArray()));
		assertValidAVLTree(tree2);
		assertValidAVLTree(tree);
		tree2.join(x, tree);
		System.out.println("HOR");
		System.out.println(Arrays.toString(tree2.keysToArray()));
		System.out.println(Arrays.toString(tree2.infoToArray()));
		System.out.println(tree2);
		assertValidAVLTree(tree2);

		AVLTree[] trees = tree2.split(17);
		System.out.println("the split is finished and these are the results");
		System.out.println("the smaller tree");
		System.out.println(Arrays.toString(trees[0].keysToArray()));
		System.out.println(Arrays.toString(trees[0].infoToArray()));
		System.out.println("the bigger tree");
		System.out.println(Arrays.toString(trees[1].keysToArray()));
		System.out.println(Arrays.toString(trees[1].infoToArray()));
		assertValidAVLTree(trees[0]);
		assertValidAVLTree(trees[1]);
		System.out.println("finished");
		AVLTree tra = new AVLTree();
		tra.insert(17, "no");
		System.out.println(Arrays.toString(tra.keysToArray()));
		assertValidAVLTree(tra);
		tra.insert(2, "1o");
		assertValidAVLTree(tra);
		tra.insert(3, "no");
		assertValidAVLTree(tra);
		tra.delete(2);
		assertValidAVLTree(tra);
		AVLTree[] trees2 = tra.split(17);
		System.out.println(Arrays.toString(trees2[0].keysToArray()));
		System.out.println(Arrays.toString(trees2[0].infoToArray()));
		System.out.println(Arrays.toString(trees2[1].keysToArray()));
		System.out.println(Arrays.toString(trees2[1].infoToArray()));
		assertValidAVLTree(trees2[0]);
		assertValidAVLTree(trees2[1]);
		trees2[0].delete(3);
		System.out.println(Arrays.toString(trees2[0].keysToArray()));
		assertValidAVLTree(trees2[0]);

		// AVLTree.IAVLNode x=new AVLNode(17,"ka");

	}

	public static void assertValidAVLTree(AVLTree tree) {
		int size = tree.size();
		int height = tree.getRoot() == null ? -1 : tree.getRoot().getHeight();
		String min = tree.min();
		String max = tree.max();
		boolean empty = tree.empty();

		int[] keys = tree.keysToArray();
		String[] values = tree.infoToArray();

		assertTrue(keys.length == values.length);

		if (!empty) {
			assertEquals(min, values[0]);
			assertEquals(max, values[values.length - 1]);
		}

		/* Assert sorted */
		for (int i = 1; i < keys.length; ++i) {
			assertTrue(keys[i - 1] < keys[i]);
		}

		/* <Key : Pair(Value, isChecked)> dictionary */
		Map<Integer, Pair<String, Boolean>> data = IntStream.range(0, keys.length).boxed()
				.collect(Collectors.toMap(i -> keys[i], i -> new Pair<String, Boolean>(values[i], false)));

		AVLTree.IAVLNode node = tree.getRoot();

		assertSame(node == null, empty);
		if (node == null) {
			// Empty tree
			assertSame(-1, height);
			assertSame(0, size);
			assertSame(0, keys.length);
			assertSame(0, values.length);
			assertNull(max);
			assertNull(min);
			return;
		}

		assertSame(height, node.getHeight());

		Queue<AVLTree.IAVLNode> bfs = new LinkedList<>();
		bfs.add(node);

		int count = 0;

		// Traverse the entire tree using BFS, verify the validity of each node
		// encountered
		while (!bfs.isEmpty()) {
			node = bfs.remove();
			assertNotSame(null, node);

			if (!node.isRealNode()) {
				// Verify properties of virtual node
				assertSame(-1, node.getKey());
				assertNull(node.getValue());
				assertSame(0, node.getSize());
				assertSame(-1, node.getHeight());
				continue;
			}

			count++;
			bfs.add(node.getLeft());
			bfs.add(node.getRight());

			/* Verify current node */

			// Heights
			int cHeight = node.getHeight();
			assertTrue(cHeight >= 0);
			assertTrue(cHeight <= height);

			// Connectivity of left/right child
			if (node.getLeft().isRealNode()) {
				assertSame(node, node.getLeft().getParent());
			}
			if (node.getRight().isRealNode()) {
				assertSame(node, node.getRight().getParent());
			}

			// Heights diff
			int lDiff = cHeight - node.getLeft().getHeight();
			int rDiff = cHeight - node.getRight().getHeight();
			assertTrue((lDiff == 1 && rDiff == 1) || (lDiff == 2 && rDiff == 1) || (lDiff == 1 && rDiff == 2),
					String.format("Found a %d,%d node", lDiff, rDiff));

			// Key and value
			int key = node.getKey();
			String value = node.getValue();
			assertTrue(data.containsKey(key));
			assertEquals(data.get(key).a, value);
			assertFalse(data.get(key).b); // key is unique
			data.get(key).b = true;
		}

		// Assert size() returns the actual amount of nodes in the tree
		assertSame(size, count);

		// Assert every key actually exists in the key
		data.forEach(
				(key, pair) -> assertTrue(pair.b, String.format("Node with the following key is missing: %d", key)));
	}
}