/* 
 * Description: class for setting information of item that is a cd
 * */
public class CD extends Audio{
	public CD(){
		type = "CD"; //automatically sets the type of the item to CD
	}
	@Override
	public int getPrice(){ return (int)Math.round(price*0.02)+price;} //gets the price with the extra tax
	} 
