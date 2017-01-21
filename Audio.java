/* 
 * Description: This class define all the functions necessary for making a audioproduct Item
 * */
public class Audio extends Item { //audio extends the class Item
	protected String artistName; //define a string for storing the artist's name 
	public int getPrice(){return price;} // returns the price of the item
	public String getInfo(){ //define function for setting the info
		info = ""; //set info equal to empty string
		for (int i=0;i<5;i++){ //iterate through the array arr where the info will be stored
			if(i==4){info = info+arr[i];} //put the contents of arr into the string info 
			else{info = info + arr[i]+", ";}} //if it is not the last value put commas in between 
		return info;} // returns the string with the info
	public String setInfo(String info1) { //initiate function to set the info
		info = info1; //sets the string info equal to the input string info1
		arr = info.split(", "); //stores the input info into array arr
		price = Integer.parseInt(arr[3]); // sets the price
		sNo = Integer.parseInt(arr[0]); //sets the serial number
		artistName = arr[2]; //sets the artist's name
		String getInfo = ""; //initiate new string
		for(int n =0;n<arr.length;n++){ //iterate through the content of arr
			getInfo = getInfo + " " +arr[n];} //stores the info in string getInfo
		return getInfo;} //returns info as string with spaces not commas
}
