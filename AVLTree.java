import java.util.ArrayList;
import java.util.List;

/**
 *
 * AVLTree
 *
 * An implementation of aמ AVL Tree with distinct integer keys and info.
 *
 */

public class AVLTree {
	protected static final IAVLNode virtual_leaf = new AVLNode();
	protected IAVLNode root;
	protected IAVLNode min;
	protected IAVLNode max;

	/**
	 * public AVLTree()
	 *
	 * Constructs an empty AVLTree Time complexity: O(1)
	 */
	public AVLTree() {
		this.root = virtual_leaf;
		this.min = null;
		this.max = null;
	}

	/**
	 * public boolean empty()
	 *
	 * Returns true if and only if the tree is empty. Time complexity: O(1)
	 */
	public boolean empty() {
		assert (this.root != null);
		return !(this.root.isRealNode());
	}

	/**
	 * public String search(int k)
	 *
	 * Returns the info of an item with key k if it exists in the tree. otherwise,
	 * returns null. Time complexity: O(log(n))
	 */
	public String search(int k) {
		return goTo(k).getValue();
	}

	/**
	 * public IAVLNode goTo(int k) {//Change to private
	 *
	 * Returns the Node of an item with key k if exists in the tree, otherwise,
	 * returns the virtual leaf Time complexity: O(log(n))
	 */
	public IAVLNode goTo(int k) {// Change to private
		IAVLNode node = this.root;
		while (node.isRealNode()) {
			if (node.getKey() == k) {
				return node;
			}
			node = node.getKey() < k ? node.getRight() : node.getLeft();
		}
		return virtual_leaf;
	}

	/**
	 * public int insert(int k, String i)
	 *
	 * Inserts an item with key k and info i to the AVL tree. The tree must remain
	 * valid, i.e. keep its invariants. Returns the number of re-balancing
	 * operations, or 0 if no re-balancing operations were necessary. A
	 * promotion/rotation counts as one re-balance operation, double-rotation is
	 * counted as 2. Returns -1 if an item with key k already exists in the tree.
	 * Time complexity: O(log(n))
	 */
	public int insert(int k, String i) {
		IAVLNode new_node = new AVLNode(i, k);
		if (this.empty()) {
			this.root = new_node;
			this.min = new_node;
			this.max = new_node;
			return 0;
		}
		IAVLNode node = this.root;
		while (node.isRealNode()) {// inserting the node
			if (node.getKey() == k) {
				return -1;
			}
			if (node.getKey() < k) {
				if (!node.getRight().isRealNode()) {
					node.setRight(new_node);
					new_node.setParent(node);
					break;
				}
				node = node.getRight();
			} else {
				if (!node.getLeft().isRealNode()) {
					node.setLeft(new_node);
					new_node.setParent(node);
					break;
				}
				node = node.getLeft();
			}
		}
		if (k > this.max.getKey()) {
			this.max = new_node;
		} else if (k < this.min.getKey()) {
			this.min = new_node;
		}
		this.root.setParent(null);
		int res= rebalance(node);
		return res;
	}

	/**
	 * public static boolean check_ranks(IAVLNode node) {//change to private (?)
	 *
	 * Checks the differences between the heights of the input and its right and
	 * left sons Time complexity: O(1)
	 */

	public static boolean check_ranks(IAVLNode node) {// change to private (?)
		int h1 = node.getHeight();
		int h2 = node.getLeft().getHeight();
		int h3 = node.getRight().getHeight();

		return ((h1 - h2 == 1 && (h1 - h3 == 1 || h1 - h3 == 2)) || ((h1 - h3 == 1) && (h1 - h2 == 1 || h1 - h2 == 2)));
	}

	/**
	 * public int delete(int k)
	 *
	 * Deletes an item with key k from the binary tree, if it is there. The tree
	 * must remain valid, i.e. keep its invariants. Returns the number of
	 * re-balancing operations, or 0 if no re-balancing operations were necessary. A
	 * promotion/rotation counts as one re-balance operation, double-rotation is
	 * counted as 2. Returns -1 if an item with key k was not found in the tree.
	 * Time complexity: O(log(n))
	 */
	public int delete(int k) {
		int count_mod = 0;
		// performing deletion

		IAVLNode node = goTo(k);// O(log(n))

		if (!node.isRealNode()) {
			return -1;
		}
		if (this.root.getSize() == 1) {
			this.max = null;
			this.min = null;
			this.root = virtual_leaf;
			return 1;

		} else {// O(log(n))
			if (this.max.getKey() == k) {
				this.max = predecessor(node);
			} else if (this.min.getKey() == k) {
				this.min = successor(node);
			}
		}

		IAVLNode parent = null;
		IAVLNode child = null;
		boolean flag = true;
		IAVLNode successor = successor(node);

		if (!node.getRight().isRealNode()) {
			parent = node.getParent();

			child = node.getLeft();

		}

		else if (!node.getLeft().isRealNode()) {
			parent = node.getParent();
			child = node.getRight();

		}

		else if (successor.getParent() != node) {
			flag = false;

			parent = successor.getParent();
			child = successor.getRight();
			assert (!successor.getLeft().isRealNode());
			IAVLNode node_parent = node.getParent();
			successor.setLeft(node.getLeft());
			successor.setRight(node.getRight());
			successor.setHeight(node.getHeight());
			successor.setSize(node.getSize());
			successor.setParent(node_parent);
			successor.getRight().setParent(successor);
			successor.getLeft().setParent(successor);

			if (node_parent == null) {
				this.root = successor;
				

			}

			else {
				if (node_parent.getRight() == node) {
					node_parent.setRight(successor);
				} else {
					node_parent.setLeft(successor);
				}
			}
			parent.setLeft(child);
			child.setParent(parent);
		} else {
			left_rotation(node, successor);
			assert (!node.getRight().isRealNode());
			parent = successor;

			child = node.getLeft();
		}

		if (parent == null) {
			this.root = child;
			return 1;
		}
		if (flag) {
			if (parent.getRight() == node) {
				parent.setRight(child);
			} else {
				parent.setLeft(child);
			}
			child.setParent(parent);
		}
		this.root.setParent(null);

		// fixing all the nodes up the tree just by current height and size
		AVLTree.hs_modifier(parent);
		node = parent;

		while (node != null) {// fix the rank and height of the ancestors
			IAVLNode right = node.getRight();
			IAVLNode left = node.getLeft();
			// nh=node height, rh=right height, lh= left height
			int nh = node.getHeight();// rank of our node
			int lh = left.getHeight();// rank of left node
			int rh = right.getHeight();// rank of right node
			// if the rank is correct we only need to fix the size;
			if (!check_ranks(node)) {
				if (nh - lh == 2 && nh - rh == 2) {// case 1
					AVLTree.hs_modifier(node);// fixing the rank
					count_mod++;
				} else {

					assert (!(nh - lh == 3 && nh - rh == 3));
					assert ((nh - lh == 3 && nh - rh == 1) || (nh - lh == 1 && nh - rh == 3));
					if (nh - lh == 3) {// the problem is in the right subtree
						int rrh = right.getRight().getHeight();// the height of the right node of the right node
						int lrh = right.getLeft().getHeight();// the height of the left node of the right node
						assert (!(rh - rrh == 2 && rh - lrh == 2));
						if (rh - rrh == 1 && rh - lrh == 1) {// case 2
							left_rotation(node, right);
							AVLTree.hs_modifier(node);
							AVLTree.hs_modifier(right);
							count_mod += 3;
						} else if (rh - rrh == 1 && rh - lrh == 2) {// case 3
							left_rotation(node, right);
							AVLTree.hs_modifier(node);
							AVLTree.hs_modifier(right);
							count_mod += 3;
						} else {
							assert (rh - rrh == 2 && rh - lrh == 1);// case 4
							IAVLNode rightL = right.getLeft();
							right_left_rotation(node, right, right.getLeft());
							AVLTree.hs_modifier(node);
							AVLTree.hs_modifier(right);
							AVLTree.hs_modifier(rightL);
							count_mod += 6;

						}
					} else if (nh - rh == 3) {// the problem is in the left subtree
						int rlh = left.getRight().getHeight();// the height of the right node of the right node
						int llh = left.getLeft().getHeight();// the height of the left node of the right node
						assert (!(lh - rlh == 2 && lh - llh == 2));
						if (lh - rlh == 1 && lh - llh == 1) {// case 2
							right_rotation(node, left);
							AVLTree.hs_modifier(node);
							AVLTree.hs_modifier(left);
							count_mod += 3;
						} else if (lh - rlh == 2 && lh - llh == 1) {
							right_rotation(node, left);
							AVLTree.hs_modifier(node);
							AVLTree.hs_modifier(left);
							count_mod += 3;
						} else {
							assert (lh - rlh == 1 && lh - llh == 2);
							IAVLNode leftR = left.getRight();
							left_right_rotation(node, left, left.getRight());
							AVLTree.hs_modifier(node);
							AVLTree.hs_modifier(left);
							AVLTree.hs_modifier(leftR);
							count_mod += 6;

						}
					}
				}
			}

			AVLTree.hs_modifier(node);// we need to fix the size but it will not be height modifier so no addition
			// to count_mod
			node = node.getParent();
		}

		return count_mod;
	}

	/**
	 * public AVLTree.IAVLNode predecessor(IAVLNode node) {//change to private
	 *
	 * returns the predecessor node of the given input node if exists, otherwise,
	 * returns the virtual leaf Time complexity: O(log(n))
	 */
	public AVLTree.IAVLNode predecessor(IAVLNode node) {// change to private
		if (node.getLeft().isRealNode()) {
			IAVLNode curr = node.getLeft();
			while (curr.getRight().isRealNode()) {
				curr = curr.getRight();
			}
			return curr;
		}

		if (node.getParent() != null) {// change to private
			IAVLNode curr = node.getParent();
			IAVLNode prev = node;
			while (curr.getLeft() == prev) {
				curr = curr.getParent();
				prev = prev.getParent();
				if (curr == null) {
					return virtual_leaf; // has no predecessor
				}
			}
			assert (curr.getRight() == prev);
			return curr;
		}
		return virtual_leaf;// we conclude that node is the root, and is the smallest node, therefore has no
		// predecessor
	}

	/**
	 * public IAVLNode successor(IAVLNode node) {//change to private
	 *
	 * returns the successor node of the given input node if exists, otherwise,
	 * returns the virtual leaf Time complexity: O(log(n))
	 */
	public IAVLNode successor(IAVLNode node) {// change to private
		if (node.getRight().isRealNode()) {
			IAVLNode curr = node.getRight();
			while (curr.getLeft().isRealNode()) {
				curr = curr.getLeft();
			}
			return curr;
		}

		if (node.getParent() != null) {
			IAVLNode curr = node.getParent();
			IAVLNode prev = node;
			while (curr.getRight() == prev) {
				curr = curr.getParent();
				prev = prev.getParent();
				if (curr == null) {
					return virtual_leaf;// has no successor
				}
			}
			assert (curr.getLeft() == prev);
			return curr;
		}

		return virtual_leaf;// we conclude that node is the root, and is the biggest node, therefore has no
		// successor
	}

	/**
	 * public String min()
	 *
	 * Returns the info of the item with the smallest key in the tree, or null if
	 * the tree is empty. Time complexity: O(1)
	 */
	public String min() {
		if (empty()) {
			return null;
		}
		return this.min.getValue();
	}

	/**
	 * public String max()
	 *
	 * Returns the info of the item with the largest key in the tree, or null if the
	 * tree is empty. Time complexity: O(1)
	 */
	public String max() {
		if (empty()) {
			return null;
		}
		return this.max.getValue();
	}

	/**
	 * public int[] keysToArray()
	 *
	 * Returns a sorted array which contains all keys in the tree, or an empty array
	 * if the tree is empty. Time complexity: O(n)
	 */
	public int[] keysToArray() {
		int[] res = new int[this.root.getSize()];
		keysToArrayHelp(res, this.root, 0);
		return res;

	}

	/**
	 * private int keysToArrayHelp(int[] arr, IAVLNode node, int index)
	 *
	 * the inner recursion function for keysToArray (keysToArray wraps it) Returns a
	 * sorted array which contains all keys in the tree, or an empty array if the
	 * tree is empty. Time complexity: O(n)
	 */
	protected int keysToArrayHelp(int[] arr, IAVLNode node, int index) {
		if (!node.isRealNode()) {
			return index;
		}

		index = keysToArrayHelp(arr, node.getLeft(), index);
		arr[index] = node.getKey();
		return keysToArrayHelp(arr, node.getRight(), index + 1);
	}

	/**
	 * public String[] infoToArray()
	 *
	 * Returns an array which contains all info in the tree, sorted by their
	 * respective keys, or an empty array if the tree is empty. Time complexity:
	 * O(n)
	 */
	public String[] infoToArray() {
		String[] res = new String[this.root.getSize()];
		infoToArrayHelp(res, this.root, 0);
		return res;
	}

	/**
	 * private int infoToArrayHelp(int[] arr, IAVLNode node, int index)
	 *
	 * the inner recursion function for infoToArray (infoToArray wraps it) Returns
	 * an array which contains all info in the tree, sorted by their respective
	 * keys, or an empty array if the tree is empty. Time complexity: O(n)
	 */
	private int infoToArrayHelp(String[] arr, IAVLNode node, int index) {
		if (!node.isRealNode()) {
			return index;
		}

		index = infoToArrayHelp(arr, node.getLeft(), index);
		arr[index] = node.getValue();
		return infoToArrayHelp(arr, node.getRight(), index + 1);
	}

	/**
	 * public int size()
	 *
	 * Returns the number of nodes in the tree. Time complexity: O(1)
	 */
	public int size() {
		return this.root.getSize();
	}

	/**
	 * public int getRoot()
	 *
	 * Returns the root AVL node, or null if the tree is empty Time complexity: O(1)
	 */
	public IAVLNode getRoot() {
		if (this.empty()) {
			return null;
		}
		return this.root;
	}

	/**
	 * public AVLTree[] split(int x)
	 *
	 * splits the tree into 2 trees according to the key x. Returns an array [t1,
	 * t2] with two AVL trees. keys(t1) < x < keys(t2).
	 *
	 * precondition: search(x) != null (i.e. you can also assume that the tree is
	 * not empty) postcondition: none Time complexity: O(log(n))
	 */
	public AVLTree[] split(int x) {
		AVLTree[] res = new AVLTree[2];
		AVLTree smaller = new AVLTree();
		AVLTree bigger = new AVLTree();
		IAVLNode child = goTo(x);

		IAVLNode successor = successor(child);
		IAVLNode predecessor = predecessor(child);
		IAVLNode node = child.getParent();
		boolean right = false;
		if (node != null) {
			right = child == node.getRight();
		}

		if (child.getLeft().isRealNode()) {
			child.getLeft().setParent(null);
			smaller.root = child.getLeft();
		}

		if (child.getRight().isRealNode()) {
			child.getRight().setParent(null);
			bigger.root = child.getRight();
		}

		complete_disconnect(child);

		while (node != null) {
			IAVLNode temp_node = node.getParent();

			if (right) {
				right = update_side(node);
				IAVLNode left_son = node.getLeft();
				disconnect_from_parent(left_son);
				AVLTree temp_tree = new AVLTree();
				temp_tree.root = left_son;
				complete_disconnect(node);
				smaller.to_join(node, temp_tree);
			}

			else {// child == parent.getLeft()
				right = update_side(node);
				IAVLNode right_son = node.getRight();
				disconnect_from_parent(right_son);
				AVLTree temp_tree = new AVLTree();
				temp_tree.root = right_son;
				complete_disconnect(node);
				bigger.to_join(node, temp_tree);
			}

			node = temp_node;
		}

		if (!smaller.empty()) {
			smaller.min = this.min;
			smaller.max = predecessor;
		}

		if (!bigger.empty()) {
			bigger.max = this.max;
			bigger.min = successor;
		}

		res[0] = smaller;
		res[1] = bigger;

		return res;

	}

	/**
	 * private boolean update_side(IAVLNode node)
	 *
	 * Returns true if the input node is the right son of its parent, otherwise,
	 * returns false Time complexity: O(1)
	 */
	protected boolean update_side(IAVLNode node) {
		if (node.getParent() != null) {
			return node == node.getParent().getRight();
		}
		return false;
	}

	/**
	 * private void disconnect_from_parent(IAVLNode node)
	 *
	 * Disconnects the input node from its parent if exists (does not disconnect
	 * from the right and left son) Time complexity: O(1)
	 */
	protected void disconnect_from_parent(IAVLNode node) {
		IAVLNode parent = node.getParent();

		if (node.getParent() != null) {
			if (parent.getLeft() == node) {
				parent.setLeft(virtual_leaf);
			}

			else {
				parent.setRight(virtual_leaf);
			}
		}
		node.setParent(null);
	}

	/**
	 * private void complete_disconnect(IAVLNode node)
	 *
	 * Disconnects the input node from its parent and sons (if they exist) Time
	 * complexity: O(1)
	 */
	protected void complete_disconnect(IAVLNode node) {
		disconnect_from_parent(node);
		node.setLeft(virtual_leaf);
		node.setRight(virtual_leaf);
		node.setHeight(0);
		node.setSize(1);
	}

	/**
	 * private int to_join(IAVLNode x, AVLTree t)
	 *
	 * Performs the "join" operation itself, as explained in the documentation of
	 * the next function- join Time complexity: O(log(n))
	 */
	protected int to_join(IAVLNode x, AVLTree t) {
		if (this.empty() && t.empty()) {// if they are both empty x is alone;
			this.root = x;
			AVLTree.hs_modifier(x);
			return 0;
		}
		// k == the smaller tree's rank
		AVLTree higher_tree = this.root.getHeight() < t.root.getHeight() ? t : this;
		AVLTree shorter_tree = this.root.getHeight() < t.root.getHeight() ? this : t;
		int k = shorter_tree.root.getHeight();
		int res = higher_tree.root.getHeight() - k + 1;
		if (higher_tree.root.getKey() > x.getKey()) {

			IAVLNode nodeH = higher_tree.root;
			IAVLNode nodeS = shorter_tree.root;
			if (res == 1 || res == 2) {
				x.setLeft(nodeS);
				x.setRight(nodeH);
				nodeH.setParent(x);
				nodeS.setParent(x);
				this.root = x;
				x.setParent(null);
				AVLTree.hs_modifier(x);
				return res;
			}
			this.root = nodeH;
			IAVLNode parent = null;
			while (nodeH.isRealNode() && nodeH.getHeight() > k) {

				parent = nodeH;
				nodeH = nodeH.getLeft();
			}
			assert (nodeS.getHeight() - nodeH.getHeight() == 1 || nodeS.getHeight() - nodeH.getHeight() == 0);
			x.setRight(nodeH);
			x.setLeft(nodeS);
			nodeS.setParent(x);
			nodeH.setParent(x);
			parent.setLeft(x);
			x.setParent(parent);

		} else if (higher_tree.root.getKey() < x.getKey()) {
			IAVLNode nodeH = higher_tree.root;
			IAVLNode nodeS = shorter_tree.root;
			if (res == 1 || res == 2) {
				x.setLeft(nodeH);
				x.setRight(nodeS);
				nodeH.setParent(x);
				nodeS.setParent(x);
				this.root = x;
				x.setParent(null);
				AVLTree.hs_modifier(x);
				return res;
			}
			this.root = nodeH;
			IAVLNode parent = null;
			while (nodeH.isRealNode() && nodeH.getHeight() > k) {
				parent = nodeH;
				nodeH = nodeH.getRight();
			}
			assert (nodeH.getHeight() - nodeS.getHeight() == -1 || nodeH.getHeight() - nodeS.getHeight() == 0);
			x.setRight(nodeS);
			x.setLeft(nodeH);
			nodeS.setParent(x);
			nodeH.setParent(x);
			parent.setRight(x);
			x.setParent(parent);

		}
		AVLTree.hs_modifier(x);
		assert (x.getHeight() == k + 1);

		this.rebalance(x);
		return res;
	}

	/**
	 * public int join(IAVLNode x, AVLTree t)
	 *
	 * joins t and x with the tree. Returns the complexity of the operation
	 * (|tree.rank - t.rank| + 1).
	 *
	 * precondition: keys(t) < x < keys() or keys(t) > x > keys(). t/tree might be
	 * empty (rank = -1). postcondition: none Time complexity: O(log(n))
	 */
	public int join(IAVLNode x, AVLTree t) {
		x.setHeight(0);
		x.setSize(1);
		x.setLeft(virtual_leaf);
		x.setRight(virtual_leaf);
		x.setParent(null);
		// we assume this is a valid tree
		if (t.empty() && this.empty()) {
			this.root = x;
			this.max = x;
			this.min = x;
			return 1;
		} else if (this.empty()) {

			this.max = t.max.getKey() > x.getKey() ? t.max : x;
			this.min = t.min.getKey() < x.getKey() ? t.min : x;

		} else if (t.empty()) {
			this.max = this.max.getKey() > x.getKey() ? this.max : x;
			this.min = this.min.getKey() < x.getKey() ? this.min : x;
		} else {
			this.max = this.max.getKey() > t.max.getKey() ? this.max : t.max;
			this.min = this.min.getKey() < t.min.getKey() ? this.min : t.min;
		}
		return to_join(x, t);

	}

	/**
	 * private int rebalance(IAVLNode node)
	 *
	 * Performs the rebalance steps for the "join" and "insert" functions. Returns
	 * the number of rebalance operations needed Time complexity: O(log(n))
	 */
	protected int rebalance(IAVLNode node) {// the input node is the grandfather of the node we start to rebalance from
		int count_mod = 0;

		while (node != null) {
			IAVLNode left = node.getLeft();
			IAVLNode right = node.getRight();
			int h = node.getHeight();
			int lh = node.getLeft().getHeight();
			int rh = node.getRight().getHeight();
			if (h == lh || h == rh) {

				if ((h - rh == 1) || (h - lh == 1)) {// case 1 from the lecture
					AVLTree.hs_modifier(node);
					count_mod++;
				}

				else {// cases 2 and 3 from the lecture (with all symmetric cases)
					assert (h - lh == 2 || h - rh == 2);

					if (h == lh) {// left
						if (lh - left.getLeft().getHeight() == 1 && lh - left.getRight().getHeight() == 1) {
							right_rotation(node, left);
							count_mod += 2;
						} else if (lh - left.getLeft().getHeight() == 1) {
							right_rotation(node, left);
							count_mod += 2;
						} else {
							left_right_rotation(node, left, left.getRight());
							count_mod += 5;
						}
					} else {// right
						if (rh - right.getLeft().getHeight() == 1 && rh - right.getRight().getHeight() == 1) {
							left_rotation(node, right);
							count_mod += 2;
						} else if (rh - right.getRight().getHeight() == 1) {
							left_rotation(node, right);
							count_mod += 2;
						} else {
							right_left_rotation(node, right, right.getLeft());
							count_mod += 5;
						}
					}
				}

			}
			AVLTree.hs_modifier(node);
			// we need to fix the size but it will not be height modifier so no addition
			// to count_mod
			node = node.getParent();
		}
		return count_mod;
	}

	/**
	 * private boolean right_rotation(IAVLNode parent, IAVLNode child)
	 *
	 * Performs the right rotation, as presented in class Returns false in case of
	 * failure, otherwise, returns true. Time complexity: O(1)
	 */
	private boolean right_rotation(IAVLNode parent, IAVLNode child) {
		if (!((parent.getLeft() == child) && (child.getParent() == parent)) || (parent == null) || child == null) {
			assert (false);
			return false;
		}

		IAVLNode temp = child.getRight();
		IAVLNode prevParent = parent.getParent();
		child.setParent(prevParent);
		parent.setParent(child);
		parent.setLeft(temp);
		child.setRight(parent);
		temp.setParent(parent);
		AVLTree.hs_modifier(parent);
		AVLTree.hs_modifier(child);

		if (prevParent != null) {
			boolean parent_on_right = prevParent.getRight() == parent;
			if (parent_on_right) {
				prevParent.setRight(child);
			} else {
				prevParent.setLeft(child);
			}

			AVLTree.hs_modifier(parent);
		} else {
			this.root = child;
		}
		if (this.root == parent) {
			this.root = child;
		}

		return true;
	}

	/**
	 * private boolean left_rotation(IAVLNode parent, IAVLNode child)
	 *
	 * Performs the left rotation, as presented in class Returns false in case of
	 * failure, otherwise, returns true. Time complexity: O(1)
	 */
	private boolean left_rotation(IAVLNode parent, IAVLNode child) {
		if (!((parent.getRight() == child) && (child.getParent() == parent)) || (parent == null) || (child == null)) {
			assert (false);
			return false;
		}

		IAVLNode temp = child.getLeft();
		IAVLNode prevParent = parent.getParent();
		child.setParent(prevParent);
		parent.setParent(child);
		parent.setRight(temp);
		child.setLeft(parent);
		temp.setParent(parent);
		AVLTree.hs_modifier(parent);
		AVLTree.hs_modifier(child);

		if (prevParent != null) {
			boolean parent_on_right = prevParent.getRight() == parent;
			if (parent_on_right) {
				prevParent.setRight(child);
			} else {
				prevParent.setLeft(child);
			}

			AVLTree.hs_modifier(parent);
		} else {
			this.root = child;
		}
		if (this.root == parent) {
			this.root = child;
		}

		return true;
	}

	/**
	 * private boolean left_right_rotation(IAVLNode parent, IAVLNode child, IAVLNode
	 * grand_child)
	 *
	 * Performs the left_right rotation, as presented in class Returns false in case
	 * of failure, otherwise, returns true. Time complexity: O(1)
	 */
	protected boolean left_right_rotation(IAVLNode parent, IAVLNode child, IAVLNode grand_child) {
		boolean success = left_rotation(child, grand_child);
		if (!success) {
			return false;
		}
		AVLTree.hs_modifier(child);
		AVLTree.hs_modifier(grand_child);
		if (!right_rotation(parent, grand_child)) {
			right_rotation(grand_child, child);
			return false;
		}
		AVLTree.hs_modifier(grand_child);
		AVLTree.hs_modifier(parent);

		return true;
	}

	/**
	 * private boolean right_left_rotation(IAVLNode parent, IAVLNode child, IAVLNode
	 * grand_child)
	 *
	 * Performs the right_left rotation, as presented in class Returns false in case
	 * of failure, otherwise, returns true. Time complexity: O(1)
	 */
	protected boolean right_left_rotation(IAVLNode parent, IAVLNode child, IAVLNode grand_child) {
		boolean success = right_rotation(child, grand_child);
		if (!success) {
			return false;
		}
		AVLTree.hs_modifier(child);
		AVLTree.hs_modifier(grand_child);
		if (!left_rotation(parent, grand_child)) {
			left_rotation(grand_child, child);
			return false;
		}
		AVLTree.hs_modifier(grand_child);
		AVLTree.hs_modifier(parent);

		return true;
	}

	/**
	 * public static void hs_modifier(IAVLNode node)//change to private
	 *
	 * Fixes the height and size fields of the input node according to the right and
	 * left sons Time complexity: O(1)
	 */
	public static void hs_modifier(IAVLNode node) {// change to private
		if (node.isRealNode()) {
			node.setHeight(Math.max(node.getLeft().getHeight(), node.getRight().getHeight()) + 1);
			node.setSize(node.getLeft().getSize() + node.getRight().getSize() + 1);
		}
	}
	// this is printer

	public List<List<String>> printTree(IAVLNode root) {
		int height = root.getHeight();

		List<List<String>> result = new ArrayList<List<String>>();
		for (int i = 0; i <= height; i++) {
			result.add(new ArrayList<String>());
		}

		int columnCount = (int) Math.pow(2, height + 1) - 1;
		printTreeHelper(root, 0, (columnCount - 1) / 2, height, result);

		for (List<String> list : result) {
			fillHolesWithEmpty(list, (int) Math.pow(2, height + 1) - 1);
		}

		return result;
	}

	protected void printTreeHelper(IAVLNode root, int rowIndex, int columnIndex, int height,
			List<List<String>> matrix) {
		if (!root.isRealNode()) {
			return;
		}

		List<String> currentRow = matrix.get(rowIndex);
		fillHolesWithEmpty(currentRow, columnIndex);
		currentRow.add(String.valueOf(root.getKey()));

		int leftChildColumnIndex = columnIndex - (int) Math.pow(2, height - rowIndex - 1);
		int rightChildColumnIndex = columnIndex + (int) Math.pow(2, height - rowIndex - 1);

		printTreeHelper(root.getLeft(), rowIndex + 1, leftChildColumnIndex, height, matrix);
		printTreeHelper(root.getRight(), rowIndex + 1, rightChildColumnIndex, height, matrix);
	}

	protected void fillHolesWithEmpty(List<String> list, int targetSize) {
		while (list.size() < targetSize) {
			list.add("");
		}
	}

	public String toString() {
		String ret = "";
		List<List<String>> toPrint = printTree(this.getRoot());
		int n = toPrint.size();
		int m = 0;
		if (n != 0) {
			m = toPrint.get(0).size();
		}
		for (int i = 0; i < toPrint.size(); i++) {
			for (int j = 0; j < toPrint.get(i).size(); j++) {
				boolean ishere = toPrint.get(i).get(j).equals("");
				if (!ishere) {
					ret += "-";
				}
				ret += toPrint.get(i).get(j);
				if (!ishere) {
					ret += "-";

				}
				if (ishere) {
					ret += " ";
				}
			}
			ret += "\n";
		}
		return ret;
	}

	public IAVLNode getMax() {
		return this.max;
	}

	/**
	 * public interface IAVLNode ! Do not delete or modify this - otherwise all
	 * tests will fail !
	 */
	public interface IAVLNode {
		public int getKey(); // Returns node's key (for virtual node return -1).

		public String getValue(); // Returns node's value [info], for virtual node returns null.

		public void setLeft(IAVLNode node); // Sets left child.

		public IAVLNode getLeft(); // Returns left child, if there is no left child returns null.

		public void setRight(IAVLNode node); // Sets right child.

		public IAVLNode getRight(); // Returns right child, if there is no right child return null.

		public void setParent(IAVLNode node); // Sets parent.

		public IAVLNode getParent(); // Returns the parent, if there is no parent return null.

		public boolean isRealNode(); // Returns True if this is a non-virtual AVL node.

		public void setHeight(int height); // Sets the height of the node.

		public int getHeight(); // Returns the height of the node (-1 for virtual nodes).

		public int getSize();// Returns the size of the sub-tree with this node as its root

		public void setSize(int size);// Sets the size of the current node's subtree
	}

	/**
	 * public class AVLNode
	 *
	 * If you wish to implement classes other than AVLTree (for example AVLNode), do
	 * it in this file, not in another file.
	 *
	 * This class can and MUST be modified (It must implement IAVLNode).
	 */
	public static class AVLNode implements IAVLNode {
		private String info;// the value of the node
		private Integer key;// the key of the node
		private IAVLNode left;// the left son
		private IAVLNode right;// the right son
		private IAVLNode parent;// the father node
		private int height;
		private int size;

		/**
		 * public AVLNode()
		 *
		 * Constructor for the virtual leaf node Time complexity: O(1)
		 */
		public AVLNode() {
			this.info = null;
			this.key = null;
			this.left = null;
			this.right = null;
			this.height = -1;
			this.parent = null;
			this.size = 0;
		}

		/**
		 * public AVLNode(String info, int key)
		 *
		 * Constructs a new AVLNode with the key: key and the info: info Time
		 * complexity: O(1)
		 */
		public AVLNode(String info, int key) {
			this.key = key;
			this.info = info;
			this.left = virtual_leaf;
			this.right = virtual_leaf;
			this.parent = null;
			this.height = 0;
			this.size = 1;
		}

		/**
		 * public int getKey()
		 *
		 * Returns the key of the current node Time complexity: O(1)
		 */
		public int getKey() {
			if (!isRealNode()) {
				return -1;
			}
			return this.key;
		}

		/**
		 * public int getValue()
		 *
		 * Returns the info of the current node Time complexity: O(1)
		 */
		public String getValue() {
			if (!isRealNode()) {
				return null;
			}
			return this.info;
		}

		/**
		 * public void setLeft(IAVLNode node)//change to private
		 *
		 * Sets the left son of the current node to be the input node Time complexity:
		 * O(1)
		 */
		public void setLeft(IAVLNode node) {// change to private
			this.left = node;
		}

		/**
		 * public IAVLNode getLeft()
		 *
		 * Returns the left son of the current node Time complexity: O(1)
		 */
		public IAVLNode getLeft() {
			return this.left;
		}

		/**
		 * public void setRight(IAVLNode node)//change to private
		 *
		 * Sets the right son of the current node to be the input node Time complexity:
		 * O(1)
		 */
		public void setRight(IAVLNode node) {// change to private
			this.right = node;
		}

		/**
		 * public IAVLNode getRight()
		 *
		 * Returns the right son of the current node Time complexity: O(1)
		 */
		public IAVLNode getRight() {
			return this.right;
		}

		/**
		 * public void setParent(IAVLNode node)//change to private
		 *
		 * Sets the parent of the current node to be the input node Time complexity:
		 * O(1)
		 */
		public void setParent(IAVLNode node) {// change to private
			this.parent = node;
		}

		/**
		 * public void getParent(IAVLNode node)//change to private
		 *
		 * Returns the parent of the current node Time complexity: O(1)
		 */
		public IAVLNode getParent() {// change to private
			return this.parent;
		}

		/**
		 * public boolean isRealNode()
		 *
		 * Returns true is this node is not a virtual leaf, else, returns false Time
		 * complexity: O(1)
		 */
		public boolean isRealNode() {
			return !(this.key == null);
		}

		/**
		 * public void setHeight(int height)//change to private
		 *
		 * Sets the height of the current node to be the given integer "height" Time
		 * complexity: O(1)
		 */
		public void setHeight(int height) {// change to private
			this.height = height;
		}

		/**
		 * public int getHeight()
		 *
		 * Returns the height of the current node Time complexity: O(1)
		 */
		public int getHeight() {
			if (!isRealNode()) {
				return -1;
			}
			return this.height;
		}

		/**
		 * public int getSize()//change to private
		 *
		 * Returns the size of the current node's subtree Time complexity: O(1)
		 */
		public int getSize() {// change to private
			return this.size;
		}

		/**
		 * public void setSize(int size)//change to private
		 *
		 * Sets the size field of this node to the given integer "size" Time complexity:
		 * O(1)
		 */
		public void setSize(int size) {// change to private
			this.size = size;
		}

	}

}