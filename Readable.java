/* 
 * Description: this class sets all the necessary information and functions for readables
 * */
public class Readable extends Item{
	public String getInfo() { //function for getting the info of this item
		info = ""; 
		for (int i=0;i<5;i++){ //go through the content of array arr
			if(i==4){info = info+arr[i];} //store the content of arr in string info
			else{info = info + arr[i]+", ";}} //put commas if not the last value of arr
		return info;}  //return the string with the info
	public String authorName; //stores the name of the author
	public String setInfo(String info1){ //function that takes input string and sets info
		info = info1; //set info string of the class to the input string
		arr = info.split(", "); //store the input info into array arr
		price = Integer.parseInt(arr[3]); //set price
		sNo = Integer.parseInt(arr[0]); //set serial number
		authorName = arr[2]; //set author's name
		String getInfo = "";
		for(int n =0;n<arr.length;n++){ //go through info and return as string with no commas
			getInfo = getInfo + " " +arr[n];  }
		return getInfo; // return the string with spaces
	
	}
	public int getPrice(){ //function for returning the price of the item
		return price;}
	
}
