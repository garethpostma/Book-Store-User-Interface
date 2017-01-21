/* 
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
