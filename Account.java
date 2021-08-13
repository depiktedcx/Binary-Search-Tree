public class Account{
    private int key;
    private double balance;
    //Create account with variables given
    public Account(int acc, double sBal){
	key = acc;
	balance = sBal;
    }
    //Return the key(account number)
    public int getKey(){
	return key;
    }
    //Return the balance in the account
    public double getBalance(){
	return balance;
    }
    //Increase account by value (pass negative to subtract)
    public void setBalance(double value){
	balance += value;
    }
}
