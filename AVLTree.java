/**
 *
 * AVLTree
 *
 * An implementation of aמ AVL Tree with
 * distinct integer keys and info.
 *
 */

public class AVLTree {
	private IAVLNode virtual_leaf = new AVLNode();
	private IAVLNode root;
	private IAVLNode min;
	private IAVLNode max;

	public AVLTree(){
		this.root = virtual_leaf;
		this.min = null;
		this.max = null;
	}


  /**
   * public boolean empty()
   *
   * Returns true if and only if the tree is empty.
   *
   */
  public boolean empty() {
	  return this.root == null;
  }

 /**
   * public String search(int k)
   *
   * Returns the info of an item with key k if it exists in the tree.
   * otherwise, returns null.
   */
   //time complexity: O(log(n))
  public String search(int k)
  {
	IAVLNode node = this.root;
	while(node.isRealNode()){
		if(node.getKey() == k){
			return node.getValue();
		}
		node = node.getKey() < k ? node.getRight() : node.getLeft();
	}
	return null;
  }

  /**
   * public int insert(int k, String i)
   *
   * Inserts an item with key k and info i to the AVL tree.
   * The tree must remain valid, i.e. keep its invariants.
   * Returns the number of re-balancing operations, or 0 if no re-balancing operations were necessary.
   * A promotion/rotation counts as one re-balance operation, double-rotation is counted as 2.
   * Returns -1 if an item with key k already exists in the tree.
   */
   public int insert(int k, String i) {
	  return 420;	// to be replaced by student code
   }

  /**
   * public int delete(int k)
   *
   * Deletes an item with key k from the binary tree, if it is there.
   * The tree must remain valid, i.e. keep its invariants.
   * Returns the number of re-balancing operations, or 0 if no re-balancing operations were necessary.
   * A promotion/rotation counts as one re-balance operation, double-rotation is counted as 2.
   * Returns -1 if an item with key k was not found in the tree.
   */
   public int delete(int k)
   {
	   return 421;	// to be replaced by student code
   }

   /**
    * public String min()
    *
    * Returns the info of the item with the smallest key in the tree,
    * or null if the tree is empty.
    */
   public String min()
   {
	   if(empty()){
		   return null;
	   }
	   return this.min.getValue();
   }

   /**
    * public String max()
    *
    * Returns the info of the item with the largest key in the tree,
    * or null if the tree is empty.
    */
   public String max()
   {
	   if(empty()){
		   return null;
	   }
	   return this.max.getValue();
   }

  /**
   * public int[] keysToArray()
   *
   * Returns a sorted array which contains all keys in the tree,
   * or an empty array if the tree is empty.
   */
   //time complexity: O(n)
  public int[] keysToArray()
  {
        int[] res = new int[this.root.getSize()];
		keysToArrayHelp(res,this.root,0);
		return res;

  }

  private int keysToArrayHelp(int[] arr, IAVLNode node, int index){
	  if(!node.isRealNode()){
		  return index;
	  }

	  index = keysToArrayHelp(arr,node.getLeft(),index);
	  arr[index] = node.getKey();
	  return keysToArrayHelp(arr,node.getRight(),index+1);
  }
  /**
   * public String[] infoToArray()
   *
   * Returns an array which contains all info in the tree,
   * sorted by their respective keys,
   * or an empty array if the tree is empty.
   */
  public String[] infoToArray()
  {
	  String[] res = new String[this.root.getSize()];
	  infoToArrayHelp(res,this.root,0);
	  return res;
  }

  private int infoToArrayHelp(String[] arr, IAVLNode node, int index){
	  if(!node.isRealNode()){
		  return index;
	  }

	  index = infoToArrayHelp(arr,node.getLeft(),index);
	  arr[index] = node.getValue();
	  return infoToArrayHelp(arr,node.getRight(),index+1);
  }
   /**
    * public int size()
    *
    * Returns the number of nodes in the tree.
    */
   public int size()
   {
	   return this.root.getSize();
   }
   
   /**
    * public int getRoot()
    *
    * Returns the root AVL node, or null if the tree is empty
    */
   public IAVLNode getRoot()
   {
	   return this.root;
   }
   
   /**
    * public AVLTree[] split(int x)
    *
    * splits the tree into 2 trees according to the key x. 
    * Returns an array [t1, t2] with two AVL trees. keys(t1) < x < keys(t2).
    * 
	* precondition: search(x) != null (i.e. you can also assume that the tree is not empty)
    * postcondition: none
    */   
   public AVLTree[] split(int x)
   {
	   return null; 
   }
   
   /**
    * public int join(IAVLNode x, AVLTree t)
    *
    * joins t and x with the tree. 	
    * Returns the complexity of the operation (|tree.rank - t.rank| + 1).
	*
	* precondition: keys(t) < x < keys() or keys(t) > x > keys(). t/tree might be empty (rank = -1).
    * postcondition: none
    */   
   public int join(IAVLNode x, AVLTree t)
   {
	   return -1;
   }

	/** 
	 * public interface IAVLNode
	 * ! Do not delete or modify this - otherwise all tests will fail !
	 */
	public interface IAVLNode{	
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
		public int getSize();//Returns the size of the sub-tree with this node as its root
		public void setSize(int size);//Sets the size of the current node's subtree
	}

   /** 
    * public class AVLNode
    *
    * If you wish to implement classes other than AVLTree
    * (for example AVLNode), do it in this file, not in another file. 
    * 
    * This class can and MUST be modified (It must implement IAVLNode).
    */
  public class AVLNode implements IAVLNode{
	   private String info;//the value of the node
	   private Integer key;//the key of the node
	   private IAVLNode left;//the left son
	   private IAVLNode right;//the right son
	   private IAVLNode parent;//the father node
	   private int height;
	   private int size;


	   public AVLNode(){
		   this.info = null;
		   this.key = null;
		   this.left = null;
		   this.right = null;
		   this.height = -1;
		   this.parent = null;
		   this.size = 0;
	   }

	   public AVLNode(String info, int key){
		   this.key = key;
		   this.info = info;
		   this.left = virtual_leaf;
		   this.right = virtual_leaf;
		   this.parent = null;
		   this.height = 0;
		   this.size = 1;
	   }


	   //returns the key of the node
	   public int getKey()
	   {
		   if(!isRealNode()){
			   return -1;
		   }
		   return this.key;
	   }
	   //returns the info (value) of the node
	   public String getValue()
	   {
		   if(!isRealNode()){
			   return null;
		   }
		   return this.info;
	   }
		public void setLeft(IAVLNode node)
		{
			this.left = node;
		}
		public IAVLNode getLeft()
		{
			return this.left;
		}
		public void setRight(IAVLNode node)
		{
			this.right = node;
		}
		public IAVLNode getRight()
		{
			return this.right;
		}
		public void setParent(IAVLNode node)
		{
			this.parent = node;
		}
		public IAVLNode getParent()
		{
			return this.parent;
		}
		public boolean isRealNode()
		{
			return !(this.key == null);
		}
	    public void setHeight(int height)
		{
	      this.height = height;
	    }
	    public int getHeight()
	    {
	        if(!isRealNode()){
				return -1;
			}
			return this.height;
	    }

		public int getSize(){
		   return this.size;
		}

		public void setSize(int size){
		   this.size = size;
		}

  }

}
  
