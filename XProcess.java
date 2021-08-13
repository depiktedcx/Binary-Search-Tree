import java.io.*;
public class XProcess{
    public static void main(String[] args){
	//Check for value from cl
	if(args.length != 1){
	    //If incorrectly formatted, print error and return
	    System.err.println("Invalid format entered!");
	    return;
	}
        try{
	    //Create new BankBST
	    BankBST bst = new BankBST();
	    //Create reader
	    BufferedReader read = new BufferedReader(new FileReader(args[0]));
	    //Check if ready to read
	    while(read.ready()){
		//Read line from file and split it from spaces
		String[] value = read.readLine().split(" ");
		if(value.length == 3){
		    //Parse each value into corresponding type
		    boolean accOK = intParse(value[0]);
		    char type = value[1].charAt(0);
		    boolean amountOK = doubleParse(value[2]);
		    if(accOK && amountOK && (type == 'd' || type == 'w' || type == 'c')){
			int acc = Integer.parseInt(value[0]);
		        double amount = Double.parseDouble(value[2]);
			//Check if account exists
			Account ref = bst.find(acc);
			//Create account if not found and add to bst
			if(ref == null){
			    ref = new Account(acc, 0);
			    System.out.print(ref.getKey() + " ");
			    bst.add(ref);
			}
			//Switch case to determine method
			switch(type){
			case 'd':
			    ref.setBalance(amount);
			    System.out.println("DEPOSIT");
			    break;
			case 'w':
			    amount = 0 - amount;
			    ref.setBalance(amount);
			    System.out.println("WITHDRAW");
			    break;
			case 'c':
			    if(ref != null){
				bst.remove(ref);
				System.out.println("CLOSE");
			    }
			    else{
				System.err.println("null value provided");
			    }
			    break;
			default:
			    //If it reaches the default, then an invalid character was found
			    System.err.println("Invalid transaction type encountered!");
			    break;
			}
		    }
		}
	    }
	    System.out.println("RESULT");
	    bst.traverse();
	}catch(Exception ex){
	    //Print exception type
	    System.err.println("Exception encountered");
	    System.err.println("Exception: " + ex);
	}
    }

    private static boolean doubleParse(String s){
	try{
	    double d = Double.parseDouble(s);
	    return true;
	}catch(NumberFormatException e){
	    return false;
	}
    }

    private static boolean intParse(String s){
	try{
	    int i = Integer.parseInt(s);
	    return true;
	}catch(NumberFormatException e){
	    return false;
	}
    }
}
