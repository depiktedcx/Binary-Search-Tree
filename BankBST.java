public class BankBST{
    Account root, parent;
    BankBST left, right;
    public BankBST(){
	//Set null
	root = null;
	left = right = null;
    }
    //Add account to bst
    public void add(Account acc){
	//Check if root is null
	if(root == null){
	    //Set root to new account
	    root = acc;
	    //Create new bst for left and right from null
	    left = new BankBST();
	    right = new BankBST();
	    return;
	}else{
	    //Add on to left
	    if(acc.getKey() < root.getKey()){
		left.add(acc);
	    }else{
		//Add to right
		right.add(acc);
	    }
	    return;
	}
    }
    //Remove specified account
    public void remove(Account acc){
        if(root == null){
	    return;
	}
	if(root == acc){
	    //Check if leaf node, if is, remove and return
	    if(left.root == null && right.root == null){
	        root = null;
		return;
	    }
	    //Check if node with one branch, if is replace this with that
	    boolean xor = left.root == null ^ right.root == null;
	    if(xor){
	        //Replace with left
		if(left.root != null){
		    root = left.root;
		    right = left.right;
		    left = left.left;
		    return;
		}
		//Replace with right
		root = right.root;
		left = right.left;
		right = right.right;
		return;
	    }
	    //Two branches
	    //Get left most node of right hand side
	    BankBST leftMostNode = right;
	    BankBST leftMostParent = this;
	    while(leftMostNode.left.root != null){
		leftMostParent = leftMostNode;
		leftMostNode = leftMostNode.left;
	    }
	    //Replace the root with the left most
	    root = leftMostNode.root;
	    //Check if left most had right branch
	    if(leftMostNode.right.root != null){
		//Assign right branch to left most's parent's left
		leftMostParent.left = leftMostNode.right;
	    }else{
		//Assign a new bst to left of parent
		leftMostParent.left = new BankBST();
	    }
	    return;
	}
	else{
	    //Check if account is lower number
	    if(acc.getKey() < root.getKey()){
		//Remove on left side
		left.remove(acc);
	    }else{
		//Remove on right side
		right.remove(acc);
	    }
	    return;
	}
    }
    //Go through tree and print account number and balance
    public void traverse(){
	//Check if none exist
	if(root == null){
	    return;
	}
	//Go to bottom of tree
	left.traverse();
	//Print current root key
	System.out.println(root.getKey() + " " + root.getBalance());
	//Go to right branch
	right.traverse();
    }
    //Find the specified account number
    public Account find(int k){
	//Check if root is null or the value
	if(root == null || root.getKey() == k){
	    if(root != null){
		System.out.print(root.getKey() + " ");
	    }
	    return root;
	}
	System.out.print(root.getKey() + " ");
	//Check if a lower number
        if(k < root.getKey()){
	    //Check lower branch
	    return left.find(k);
	}
	//Check higher branch
	return right.find(k);
    }
}
