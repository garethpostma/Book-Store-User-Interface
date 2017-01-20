/* Name: Gareth Postma
 * MacID: postmagn
 * Student Number: 001422248
 * Name: Noah Zwiep
 * MacID: zwiepn
 * Student Number: 001424643
 * Name: Mitchell Overbeeke
 * MacID: overbeml
 * Student Number: 001422018
 * Description: class for making an Item that is a book
 * */
public class Book extends Readable{
	public Book(){ //automatically calls function when class is called
		type = "Book"; // sets the type of the item to book
	}
	public int getPrice(){//function for getting the price with extra tax
		int taxPrice = (int)Math.round(price*0.02)+price; //sets value
		return taxPrice;}}//returns the value
