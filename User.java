/* Name: Gareth Postma
 * MacID: postmagn
 * Student Number: 001422248
 * Name: Noah Zwiep
 * MacID: zwiepn
 * Student Number: 001424643
 * Name: Mitchell Overbeeke
 * MacID: overbeml
 * Student Number: 001422018
 * Description:
 * */
public class User {
	public ShoppingCart cart;
	public User(String name,int numOfItems){
		getUsername(name);
		cart = new ShoppingCart(numOfItems);}
	User(){getUsername(username);}
	protected int numOfItems;
	public String username;
	public String getUsername(String name){
		username = name;
		return name;
	}
}
