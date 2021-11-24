import java.util.Arrays;

public class Tests {
	public static void main(String [] arg) {
		AVLTree tree= new AVLTree();
		AVLTree tree2= new AVLTree();
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
		tree2.insert(100000, "2");
		tree2.insert(81, "15");
		tree2.insert(42, "!!!");
		tree2.insert(73, "26");
		tree2.insert(91, "7");
		tree2.insert(728, "6");
		tree2.insert(1030000, "2");
		tree2.insert(812, "15");
		tree2.insert(421, "!!!");
		tree2.insert(7334, "26");
		tree2.insert(33121, "7");
		tree2.insert(715148, "6");
		tree2.insert(1500000, "2");
		tree2.insert(412381, "15");
		tree2.insert(443212, "!!!");
		tree2.insert(711113, "26");
		tree2.insert(398881, "7");
		tree2.insert(7100038, "6");
		tree2.insert(10000, "2");
		tree2.insert(832781, "15");
		tree2.insert(43242, "!!!");
		tree2.insert(73321, "26");
		System.out.println(Arrays.toString(tree.keysToArray()));
		System.out.println(Arrays.toString(tree.infoToArray()));
		System.out.println(Arrays.toString(tree2.keysToArray()));
		System.out.println(Arrays.toString(tree2.infoToArray()));
		System.out.println("This is Min");
		System.out.println(tree.min());

		
		System.out.println(tree.delete(1));
		AVLTree.IAVLNode node2=tree.goTo(7);
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
		
		System.out.println(tree.insert(4,"wow"));
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
		AVLTree seventeen=new AVLTree();
		seventeen.insert(17, "no");
		AVLTree.IAVLNode x=seventeen.goTo(17);
		tree2.join(x, tree);
		System.out.println("HOR");
		System.out.println(Arrays.toString(tree2.keysToArray()));
		System.out.println(Arrays.toString(tree2.infoToArray()));
		System.out.println(tree2.getRoot().getKey());
		System.out.println(x.getLeft().getKey());
		
		AVLTree[] trees=tree2.split(17);
		
		System.out.println(Arrays.toString(trees[0].keysToArray()));
		System.out.println(Arrays.toString(trees[0].infoToArray()));
		System.out.println(Arrays.toString(trees[1].keysToArray()));
		System.out.println(Arrays.toString(trees[1].infoToArray()));
		
		
		
		
		
	//	AVLTree.IAVLNode x=new AVLNode(17,"ka");
		
	}
}
