/* 
 * Description:
 * */
//Import files
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

//Class definition
public class UserInterface {	

	//Variable Definitions
	private Item[][] readables;			//Readables list (all stored as items)
	private Item[][] audioProducts; //Autio products list
	private int readablesLength;
	private int audioProdLength;
	private int currentPage;
	private User user;
	private int purchaseNumb;
	private int confId;
  
	//Create scanner
	public Scanner scanner = new Scanner(System.in);
  
  //Methods
	public UserInterface(){ //Things to do when the user interface is created
		confId = 1000;		//Start at 1000
		getReadables();		//Call the getReadables method
		getAudioProducts(); //Same with audioproducts
		readablesLength = readables[0].length+readables[1].length; //Counts number of books and ebooks
		audioProdLength = audioProducts[0].length+audioProducts[1].length;} //Counts number of cds and mp3s
    
	public int getCurrentPage(String input){ //getCurrentPage method
		if(currentPage==1){ 
			if(input.equals("1")) currentPage = 3; //If the user wants to change to page 3, they type 1
			else if (input.equals("2")) currentPage=2; //Otherwise we go to page 2
			else{System.out.println("Please input 1 or 2.");}}  //If they do not type 1 or 2, don't do anything but tell them to.
		else if(currentPage==5){
			if (input.equals("1")) {currentPage = 6;return currentPage;}	//Change to page 6 
			else if (input.equals("2")) {currentPage =7;return currentPage;} //Change to page 7
			else if (input.equals("3")) {currentPage = 1;} //Go back to page 1
			else {System.out.println("Please input 1, 2 or 3.");}} //Else, we tell them to enter a valid input
		else if(currentPage==6){
			if (input.equals("1")) {currentPage = 8;return currentPage;} //Change to page 8
			else if (input.equals("2")) {currentPage = 9;return currentPage;} //Change to page 9
			else if (input.equals("-1")) currentPage =5; //Go back to page 5
			else {System.out.println("Please input 1 or 2.");}} //Enter a valid input
		else if(currentPage==7){	
			if(input.equals("-1")){currentPage = 5;} //Go back to page 5
			else if(user.cart.content[0] !=null && input.equals("0")){ currentPage =10; return currentPage;} //Go to page 10 if the user has something in their cart
			else{System.out.println("Please input -1 to return to go back or the serial number of an item.");}} //Else, just print this
		else if(currentPage==8 || currentPage==9){	//Page 8 or 9
			try{
				int value = Integer.parseInt(input);		//Convert input to a integer
				purchaseNumb = value;										//Our purchase number is the input
				if (currentPage==8 && checksNo() == true){		//If on page 8, and there is a readable with that serial number
					System.out.println("Enter quantity:");		//Ask how many 
					String quant = scanner.nextLine();			
					int quantity = Integer.parseInt(quant);		//Number of readables we want
          //Iterate through all our readables to look for which book actually has the sNo
					for(int u=0;u<readables.length;u++){
						for(int t=0;t<readables[0].length;t++){
							if (readables[u][t]!=null && readables[u][t].sNo == purchaseNumb){	//If we have a readable with that serial number
								if (quantity > 0 && quantity <= Integer.parseInt(readables[u][t].arr[4])){	//They ask for a number larger than 0, and less than what we have in stock
									user.cart.addItem(readables[u][t],quant,null); 			//Add that book to the cart, and the number of books
									readables[u][t].arr[4] = Integer.toString(Integer.parseInt(readables[u][t].arr[4])-quantity); //Changes the number of books availible
									writeShopCart();	//Writes to our shopping cart what we have
									System.out.println(quant + " "+ readables[u][t].arr[1] + " "+ readables[u][t].type +"s successfully added to your cart"); //Number of which book is successfully added
								}
								else{
									System.out.println("Please enter a quantity of books less than what we\n have have in stock or greater than zero.");	//If they enter a number less than 0 or more than what we have
								}}}}}
				else if (currentPage==9 && checksNo() == true){	//If we are on page 9, and that serial number exists
        	//Get number of books, make it into a int
					System.out.println("Enter quantity:");
					String quant = scanner.nextLine();
					int quantity = Integer.parseInt(quant);
          //Iterate through our audioproducts looking for that book
					for(int u=0;u<audioProducts.length;u++){
						for(int t=0;t<audioProducts[0].length;t++){
							if (audioProducts[u][t]!=null && audioProducts[u][t].sNo == purchaseNumb){
								if (0 < quantity && quantity <= Integer.parseInt(audioProducts[u][t].arr[4])){ //If number is greater than 0 and there are that many
									user.cart.addItem(audioProducts[u][t],quant,null); //Add to the cart the book and quanitity
									audioProducts[u][t].arr[4] = Integer.toString(Integer.parseInt(audioProducts[u][t].arr[4])-quantity); //Remove from book what we have
									writeShopCart(); //call the function to rewrite shopping cart file
									System.out.println(quant + " " +audioProducts[u][t].arr[1]+" "+audioProducts[u][t].type+"s successfully added to your cart"); //success statement
								}
								else{System.out.println("Please enter a quantity of books less than what we\n have have in stock or greater than zero."); //If not a proper amount
									}}}}}
				else if (user.cart.content[0]!=null && input.equals("0")) currentPage=10; //Go to the checkout
				else if (input.equals("-1")) currentPage =6;	//Go back to page 6
				else {System.out.println("Please input -1 to go back or the serial number of a item.");}} //If improper input
		catch(java.lang.NumberFormatException NFE){	//If they enter a string, not a number
			System.out.println("Please enter a Number"); //Print asking them to enter a number
			return currentPage;	//Return what page we are on. 
			}}
		else if(currentPage==10){	//Checkout page
			if(input.toLowerCase().equals("yes")){	//Allows non-case sensitivity
				for(int i =0;i<readablesLength+audioProdLength;i++){ //Deletes what is in the cart
					user.cart.content[i]=null;}
				writeTxts(); //Writes the texts
				System.out.println("confirmation ID: U"+confId); //Gives confirmation id
				confId++; //Adds to the confId
				System.out.println("Items Shipped to: Mr." + user.username); //Prints who item is shipped to
				System.out.println("Your cart is now empty.");
				currentPage=6;} //Goes back to page 6
			else if (input.toLowerCase().equals("no")){	//Ignores case sensitivity
				System.out.println("TRANSACTION CANCELLED."); //Cancells transaction
				currentPage=6;} //Goes back to page 6
			else{System.out.println("Please enter yes or no.");}} //If you do not enter yes or no
		return currentPage;} //Returns the new page
    
  //changeCurrentPage method
	public int changeCurrentPage(){
		String input = scanner.nextLine(); //Input == the next line the user types (so we don't have to put this every time)
		System.out.println("\n\n\n"); //Shows we went to a new page
		getCurrentPage(input); //Gets the current page we are on
    
    //Gives the page functions according to what the current page we are on
		if(currentPage==1){P1();}
		else if(currentPage==2){P2();}
		else if(currentPage==3){P3();}
		else if(currentPage==5){P5();}
		else if(currentPage==6){P6();}
		else if(currentPage==7){P7();}
		else if(currentPage==8){P8();}
		else if(currentPage==9){P9();}
		else if(currentPage==10){P10();}
		return currentPage;	}
  
  //Page Definitions, essentially printing out what we need.
	public void P1() {
		currentPage =1;	//Since we call P1 directly from the main function, we need to define this here
		System.out.println("1.Sign in\n2.Sign up\nChoose your option:");
		changeCurrentPage();}
	public void P2() {
		System.out.println("Choose your username:");
		Scanner sp = new Scanner(System.in); //New scanner for just the username
		String input = sp.nextLine();	
		if (input.equals("") || input.charAt(0) == ' ') {
      System.out.println("Please enter a user name or remove the space from the beginning."); //Robust man
      System.out.println("\n\n\n");
      P2();
    }
		else {System.out.println("\nUsername successfully added"); //Proper username added
		try{
      FileWriter write = new FileWriter("Users.txt", true);	//Writes to users.txt the username
      write.write("\n" + input);
      write.close();
      System.out.println("\n\n\n");
      P1();}
		catch(IOException ioe){};
    }
		sp.close();} //Close our new scanner
	public void P3() {
		System.out.println("Enter your username:");
		Scanner sl = new Scanner(System.in); //Enters username
		String input = sl.nextLine();
		try{
    	//Go through our usernames to check if that one exists
      FileReader fr = new FileReader("Users.txt");
      BufferedReader read = new BufferedReader(fr);
      String stringRead = read.readLine();
      int s = 0;
      while(stringRead != null){
        if (stringRead.equals(input)) { //If we have a match
          System.out.println("\nHello Mr." + input);	 //Welcome them
          user = new User(input,readablesLength+audioProdLength); //Create a user with their name
          getCart();	//Create a cart for them
          System.out.println("\n\n\n");
          P5();
          s = 1;} //We do have a user that exists
        stringRead = read.readLine();}
      read.close();
      if (s==0) {System.out.println("\n\n\n"); //If the name is not in there, go to page 4
        P4();}} 
		catch(FileNotFoundException fnfe){} //We do not have that file
		catch(IOException ioe){} //Some error
		sl.close();}
	public void P4() {System.out.println("\nNo Access"); //We have no access
		System.out.println("\n\n\n");
		P1();} //Go back to page 1
  
  //The rest of these pages are regular print stuff
	public void P5() {
		currentPage=5;
		System.out.println("1.View Items By Category\n2.View Shopping Cart\n3.Sign Out\n\nChoose your option:"); 
		changeCurrentPage();}
	public void P6(){System.out.println("1.Readables\n2.Audio\n\nChoose your option:\n\nEnter -1 to return to\nprevious menu");
		changeCurrentPage();}
	public void P7(){
		System.out.println("Shopping Cart\n\n" +user.cart.getContent());
		System.out.println("\n\nEnter -1 to return to\nprevious menu");
		if(user.cart.content[0]!=null) System.out.println("\nEnter 0 to checkout"); //if the cart isnt empty then you can go to checkout
		changeCurrentPage();}
	public void P8(){
		showReadables();
		System.out.println("\nEnter the sNo of the book you want:");
		System.out.println("\n\nEnter -1 to return to\nprevious menu");
		if(user.cart.content[0] != null) System.out.println("Enter 0 to go to checkout:");
		changeCurrentPage();}
	public void P9(){
		showAudioProducts();
		System.out.println("\n\nEnter -1 to return to\nprevious menu");
		if(user.cart.content[0] != null) System.out.println("Enter 0 to go to checkout:");
		changeCurrentPage();}
    
  //Checkout page
	public void P10(){
		System.out.println("Billing Information:\nName\t\t\tQuantity\tPrice\n"); //Headers that we print out
    
    //Local variable definitions
		int i =0;
		int envTax = 0;
		int HST = 0;
		int Shipping = 0;
		int total = 0;
    
    //Go through our cart to print
		while(user.cart.content[i]!=null && i<user.cart.content.length){ //go through the cart content
				Item [] next = user.cart.content; //store items in next
			if(next[i]!=null){	 //if there is an item in the next location
				int quantity = Integer.parseInt(next[i].quant); //get the quantity as an int
				if(next[i].type.equals("Book") || next[i].type.equals("CD")){ //check the type of item
					envTax = (int)Math.round(quantity*next[i].price*0.02) + envTax;} //add the environmental tax
				HST = HST + (int)Math.round(quantity*next[i].price*0.13); //add the hst
				Shipping = Shipping + (int)Math.round(quantity*(next[i].price*0.10)); //add the shipping
				total = total + quantity*next[i].price; //add to total
				int k =26; //add 26 spaces minus the length of the string so everything is lined up
				int l = next[i].arr[1].length();
				if(k-l<1){l=k-1; next[i].arr[1].substring(0, l);} //if string is too long we will chop it
				char[] chars = new char[k-l];
				Arrays.fill(chars, ' ');
				String d = String.valueOf(chars);
				String split1 = next[i].arr[1]+ d; //store name with correct spacing 
				String split2 = next[i].quant+ "\t\t"; //add the date
				String split3 = next[i].arr[3]; //add the quantity
				System.out.println(split1+split2+split3);	 //print everything
				i++;}}
		total = total + HST+Shipping+envTax; //get the total price and print everything
		System.out.println("\nEnvironment Tax\t\t2%\t\t" + envTax);
		System.out.println("  HST\t\t\t13%\t\t"+HST+"\n");
		System.out.println("Shipping\t\t10%\t\t"+Shipping);
		System.out.println("\t\t\t\t\t------\nTotal:\t\t\t\t\t"+ total+"$");
		System.out.println("Are you sure you want to pay? yes or no.");
		changeCurrentPage();}
    
  //Method to get all our readables
	public void getReadables(){
			try{
      //get all the books
			FileReader fb = new FileReader("Books.txt"); //read file books
			BufferedReader read = new BufferedReader(fb); //user buffer to read each line
			String stringRead = read.readLine();
			int numLines =0; //for tracking the number of lines
			String content = "";
			while (stringRead != null){ //if the line isn't null keep going
				if(!stringRead.equals("")){ //if the line is not just an empty line then do the following
					content = content+stringRead +"\n"; //add the contents to the string to content
					numLines++;} //track the number of lines
					stringRead = read.readLine();} //read the next line
			read.close();
			String[] books = content.split("\n"); //split the string that was read into books
			
      //get all the ebooks
      FileReader fe = new FileReader("Ebooks.txt"); //read file ebooks
			BufferedReader read2 = new BufferedReader(fe); // read one line at a time
			String stringRead2 = read2.readLine();
			int numLines2 = 0; //store number of lines in ebooks
			String content2 = "";
			while (stringRead2 != null){ //loop through the file and add all the lines to string content2
				if(!stringRead2.equals("")){
					content2 = content2+stringRead2+"\n";
					numLines2++;}
					stringRead2 = read2.readLine();	}
			read2.close();
			String[] ebooks = content2.split("\n"); //splint the string content2 into array ebooks
			int count =0;
			int Lines =0;
			if(numLines>numLines2) Lines = numLines; //see which text file had more lines
			else{Lines = numLines2;}
			readables = new Item[2][Lines]; //make readables the size of the bigger text file
			while(count<numLines){
				Item book1 = new Book(); //make a new book
				book1.setInfo(books[count]); //make the info of that book equal to next part of array books
				readables[0][count] = book1; //add that book to readables
				count++;}
			count = 0;
			while(count<numLines2){ //do the same as above but with ebooks
				Item ebook1 = new eBook();
				ebook1.setInfo(ebooks[count]);
				readables[1][count] = ebook1;
				count++;}}
			catch(FileNotFoundException fnfe){}
			catch(IOException ioe){}}
	
	
	public void getAudioProducts(){ //get all the audio products
			try{
      //get all the cds
			FileReader fc = new FileReader("CDs.txt"); //read text file cds
			BufferedReader read3 = new BufferedReader(fc);
			String stringRead = read3.readLine();
			int numLines =0; //store the number of lines
			String content =""; //string for stroing the content of the text file
			while (stringRead != null){
				if (!stringRead.equals("")){
					content=content+stringRead+"\n"; //add the next line to content
					numLines++;} //add to number of lines
					stringRead = read3.readLine();} //read next line
			read3.close();
			String[] CDs = content.split("\n"); //split content and store into array cds
			
      //get all the mp3s
      FileReader fe = new FileReader("MP3.txt"); //read file mp3
			BufferedReader read2 = new BufferedReader(fe);
			String stringRead2 = read2.readLine();
			int numLines2 = 0;
			String content2 = "";
			while (stringRead2 != null){ //doing the same as with the cds
				if(!stringRead2.equals("")){
					content2 = content2+stringRead2+"\n";
					numLines2++;}
					stringRead2 = read2.readLine();	}
			read2.close();
			String[] mp3s = content2.split("\n"); //store info into array mp3s
			int count =0;
			int Lines =0;
			if(numLines>numLines2) Lines = numLines; //find which file had more lines
			else{Lines = numLines2;}
			audioProducts = new Item[2][Lines]; //make audioProducts the size of the bigger text file
			while(count<numLines){ //go through cds and add all the cds to audioproducts
				Item CD = new CD();
				CD.setInfo(CDs[count]);
				audioProducts[0][count] = CD;
				count++;}
			count = 0;
			while(count<numLines2){ //go through the mp3 and add them to audioproducts
				Item MP3 = new MP3();
				MP3.setInfo(mp3s[count]);
				audioProducts[1][count] = MP3;
				count++;}}
			catch(FileNotFoundException fnfe){}
			catch(IOException ioe){}}
	
  //this method is for getting the content from the text file cart and add to cart
  public void getCart(){ 
		String fileName= "Cart_"+user.username +".txt"; //get the name of the users cart file
		try {FileReader file = new FileReader(fileName); //read the file
		BufferedReader read2 = new BufferedReader(file);
		String strR = read2.readLine();
		String content ="";
		while(strR != null){ //add all the content of the file to content
			if (!strR.equals("")){
				content = content + strR+"\n";}
				strR = read2.readLine();}
		read2.close();
		if (content.equals("")){}
		else{int i =0;
			String [] Items = content.split("\n"); //split content and store in array items
			String information = "";
			while(i<Items.length){ //split the item info by commas and store in infoRead
				String [] infoRead = Items[i].split(",");
				int j =0;
				
        //here we will go through the items in cart and compare their serial numbers with items in store to get info
        while(j<readables[0].length){//check the serial number with the numbers in book file
					if(readables[0][j]!=null && Integer.parseInt(infoRead[0]) == readables[0][j].sNo){
						information = readables[0][j].info; //get information if numbers match
						Item next = new Book(); //create new book
						next.setInfo(information); //set info of book
						user.cart.addItem(next, infoRead[3],infoRead[2]); //add book to cart with quantity and date
						}
					else if(readables[1][j]!=null && Integer.parseInt(infoRead[0]) == readables[1][j].sNo){
						information = readables[1][j].info; //check serial numbers with ebooks and get info if match
						Item next = new eBook(); //create ebook
						next.setInfo(information); //set info
						user.cart.addItem(next, infoRead[3],infoRead[2]);} //add to cart with quantity and date
					j++;}
				int k =0;
				while(k<audioProducts[0].length){ //do the same with the audioproducts
					if(audioProducts[0][k]!=null && Integer.parseInt(infoRead[0]) == audioProducts[0][k].sNo){
						information = audioProducts[0][k].info; //if serial numbers match get info
						Item next = new CD(); //create cd
						next.setInfo(information); //set info
						user.cart.addItem(next, infoRead[3],infoRead[2]);} //add to cart with quantity and date
					else if(audioProducts[1][k]!=null && Integer.parseInt(infoRead[0]) == audioProducts[1][k].sNo){
						information = audioProducts[1][k].info; //if serial numbers match get info
						Item next = new MP3(); //create mp3
						next.setInfo(information); //set info
						user.cart.addItem(next, infoRead[3],infoRead[2]);} //add to cart with quantity and date
					k++;}
				i++;}}}
		catch(FileNotFoundException fnfe){} //catch errors
		catch(IOException ioe){}}
	
 //this function will display the readables for the view 
  public void showReadables(){	
  	//print the header of the page
		System.out.println("Readables:\n\nS.No\tName of the Book\tAuthor\t\tPrice($)\tQuantiity in Store\tType");
		String show = "";
		for (int i = 0;i<readables.length;i++){ //iterate through all the readables
			for (int j = 0; j<readables[0].length;j++){
				if(readables[i][j]!=null){ //make sure there is actually an item there
					Item next = readables[i][j]; //create the item
					String split1 = "";
					int k = 0;
					for(int n=0;n<next.arr.length;n++){
          //here we have a set number of spaces for each piece of info
						if(n==0) k=9; if(n==1) k=26; if(n==2) k=15; if(n==3) k=20; if(n==4) k = 18;
						int l = next.arr[n].length(); //get the length of the string
						if(k-l<=1){l=k-1; next.arr[n] = next.arr[n].substring(0,l);} //if string is too long it will be chopped
						char[] chars = new char[k-l]; //get the number of spaces to be added
						Arrays.fill(chars, ' '); //make the number of spaces needed
						String d = String.valueOf(chars); //put the spaces in d
						split1 = split1 + next.arr[n]+ d;} // add the string and the spaces to string split1
					split1 = split1 + next.type; //add type to then end of the string
					show = show + split1 + "\n";}}} //put this line in show and add and endline
		System.out.println(show);} //print the info
	
  //this function will print all the audioproducts, works the same as the getReadables function
  public void showAudioProducts(){
  	//print the header
		System.out.println("Audio:\n\nS.No\t Name\t\t\t  Artist\tPrice($)\tQuantiity in Store\tType");
		String show = "";
		for (int i = 0;i<audioProducts.length;i++){ //iterate through all the audioproducts
			for (int j = 0; j<audioProducts[0].length;j++){
				if(audioProducts[i][j]!=null){	 //if there is a audioproduct there then contintu
					Item next = audioProducts[i][j]; //make an item
					String split1 = "";
					int k =0;
					for(int n=0;n<next.arr.length;n++){ //go through the info and put in nice string for printing
						if(n==0) k=9; if(n==1) k=26; if(n==2) k=15; if(n==3) k=20; if(n==4) k = 18;
						int l = next.arr[n].length(); //get length of the sting
						if(k-l<=1){l=k-1; next.arr[n] = next.arr[n].substring(0,l);}
						char[] chars = new char[k-l]; //find number of needed spaces
						Arrays.fill(chars, ' '); //make the spaces
						String d = String.valueOf(chars); //store the spaces in d
						split1 = split1 + next.arr[n]+ d;} //put the info for this item in split1
					split1 = split1 +next.type; //add the item's type
					show = show + split1 + "\n";}}} //put each item info into show
		System.out.println(show);} //print all the information
	
  //this function is for checking the serial number of the input when purchasing books to make sure the serial number exists
  public boolean  checksNo(){
		boolean output = false; //set the output to false
		if(currentPage == 8){ //if we are on page 8 we will do this
			for(int i=0;i<2;i++){ //iterate through the content of readables
				for(int j=0;j<readables[0].length;j++){
					if(readables[i][j] != null && readables[i][j].sNo==purchaseNumb){
						output = true;}}}} //if the serial number match a serial number in readables then output will become true
		else if (currentPage==9){ //if current page is 9 we will check the audioProducts
			for(int i=0;i<2;i++){
				for(int j=0;j<audioProducts[0].length;j++){ //iterate through the audioproducts
					if(audioProducts[i][j] != null && audioProducts[i][j].sNo==purchaseNumb){
						output = true;}}}} //if the serial numbers match the set output to true
		return output;} //return the output boolean
	
  //this function rewrites the text file shopping cart
  public void writeShopCart(){
		String name = "Cart_"+user.username+".txt"; //get the file name based on the users name
		try{FileWriter writeSC = new FileWriter(name,false); //create filewriter
		writeSC.write(user.cart.getContent()); //write the content from the cart to the file
		writeSC.close();} //close the writer
		catch(IOException ioe){};} //catch error
	
  //this function will rewrite all the text files
  public void writeTxts(){
		try{writeShopCart(); //first we will rewrite the shopping cart
			FileWriter writeB = new FileWriter("Books.txt", false); //open all the filewriters
			FileWriter writeE = new FileWriter("Ebooks.txt",false);
			FileWriter writeC = new FileWriter("CDs.txt",false);
			FileWriter writeM = new FileWriter("MP3.txt",false);
      //go through the content of each type of item and write the info to the file
			for(int i=0;i<readables[0].length;i++) if(readables[0][i]!=null){writeB.write(readables[0][i].getInfo()+"\n");}
			for(int t =0;t<readables[1].length;t++) if(readables[1][t]!=null){writeE.write(readables[1][t].getInfo()+"\n");}
			for(int j =0;j<audioProducts[0].length;j++) if(audioProducts[0][j]!=null){writeC.write(audioProducts[0][j].getInfo()+"\n");}
			for(int l=0;l<audioProducts[1].length;l++) if(audioProducts[1][l]!=null){writeM.write(audioProducts[1][l].getInfo()+"\n");}
			writeB.close(); //close all the file writers 
			writeC.close();
			writeE.close();
			writeM.close();}
		catch(IOException ioe){};}} //catch the error
