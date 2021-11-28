import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Exp {
	public static void main(String[] args) {
		int [] arr=buildRand(7);
		System.out.println(Arrays.toString(arr));
	}
	public static Pair<AVLTree.IAVLNode,Integer> search(AVLTree tree, int key){
		int dist=0;
		AVLTree.IAVLNode node = tree.getMax();
		while(node.getKey()>key&& node!=null) {
			node=node.getParent();
			dist++;
		}
		while(node.isRealNode()) {
			if(node.getKey()==key) {
				return new Pair<>(node,dist);
			}
			if(node.getKey()<key) {
				node=node.getRight();
			}
			else {
				node=node.getLeft();
			}
			dist++;
		}
		return null;
	}
	
	public static int[] buildReverse(int k) {
		int [] arr= new int [k];
		for(int i=0;i<k;i++) {
			arr[i]=k-i;
		}
		return arr;
	}
	public static int[] buildRand(int k) {
		int [] arr= new int [k];
		List<Integer> vals=new ArrayList<>();
		for(int i=0;i<k;i++) {
			vals.add(i);
		}
		Random rand=new Random();
		for(int i=0;i<k;i++) {
			int x=rand.nextInt(k-i);
			arr[i]=vals.remove(x);
		}
		return arr;
	}
}
