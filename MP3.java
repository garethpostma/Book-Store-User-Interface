/* 
 * Description: this class has the functions for an Item of type mps
 * */
public class MP3 extends Audio{ //this class extends the class audio
	public MP3(){
		type = "MP3";} //set the type of the item to MP3
	@Override
	public int getPrice(){ return price;} //return the price of the item
	}
