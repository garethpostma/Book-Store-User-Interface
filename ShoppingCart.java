/* 
 * Description: this class is for adding the purchased items and displaying the content
 * */
//import all necessary files
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class ShoppingCart extends User { //shopping cart extends class user
	public ShoppingCart(int numOfItems){ //function that takes integer input
		numItems = numOfItems; //sets numItems equal to the input integer
		location =0; //sets location as 0, for keeping track of number of items added to cart
		content = new Item[numItems];} //create array for string the items added to the cart
	private int numItems; //integer for string the number of items added
	public Item[]content;//array of items to store content of cart
	private int location;//integer for tracking location in array content
	private String quant1; //string for quantity added
	public String getContent(){ //function for getting the content of cart
		String contentSt = "";
		for (int i = 0; i< content.length; i++){ //iterate through the content of array content
			if (content[i]!=null){ //make sure the content at i is not empty
					Item item = content[i]; //create item equal to content at i
					contentSt = contentSt + item.arr[0]+","+item.arr[1]+","+item.date +","+ item.quant +"\n";} //print all the info from this item
			}
		return contentSt; //return the string of info for this item
	}
	public void addItem(Item add, String quant, String dateAdd){ //function for adding item to cart
		quant1 = quant; //set the quantity equal to the input quantity
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd"); //set format for date
		Date date = new Date(); //get new date
		for(int i =0; i<content.length; i++){ //iterate through the content of content
			if(content[i] != null && add.sNo==content[i].sNo){ //check to see if content at i is not empty and if this item is already in the cart
				content[i].quant = Integer.toString(Integer.parseInt(content[i].quant)+Integer.parseInt(quant)); //if item is already in cart, just add the new quantity to what is already there
				i = content.length;} //we don't need to go through the rest of the content
			else if(i!=content.length && content[i+1] !=null){} //if serial numbers didn't match then move to next item in cart
			else {content[location] = add; //if this item is not already in the cart then add it to next location in content
				content[location].quant = quant1; //set the quantity of this item
				if (dateAdd ==null) content[location].date = dateFormat.format(date);//set the date of purchase unless it was purchased before
				else content[location].date = dateAdd; //if it was purchased before keep that date 
				location++; //increase location 
				i=content.length;}}}} //don't need to go through the rest of the content in content
