/* 
 * Description: this is the abstract class to outline the necessary information for each item
 * */
public abstract class Item {
	public abstract String getInfo (); //each class that extends item needs a getInfo function
	public abstract String setInfo(String val);//also need a function to set the info based on input
	public abstract int getPrice(); //they also need a function for getting the price
	
	//public String info;
	protected int price; //price of the item is stored here
	protected int sNo; //serial number is stored here
	protected String [] arr; //the input string will be split and stored in this array
	protected int quantity; // the quantity of items purchased is stored here as an integer
	protected String type; //this is the type of item
	protected String info; //the string will contain all the information for the item
	protected String quant;//this will store quantity as a string
	protected String date;//this will store the date the item was purchased
}

