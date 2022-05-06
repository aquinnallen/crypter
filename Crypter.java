/*
Crypterv0.0.1(a)
Written by Quinn Allen

This code is my response to assignment two in CSF Spring 2022 at the evergreen state college.

All code is my own, though I of course referenced the documentation while creating this.

This code, called Crypter, is a demonstration of multi-alphabetic 

This code successfully creates a 1 of a kind key set from user inputted nonce, and then utizilizes a poly-alphabetic cipher method based on a Vignère cipher to encode a message

The code also can functionally decode any message encoded with it, as long as correct nonce are provided.

nonce are semi-obfuscated by internal calculations in order to offer some protection from a known plaintext/ciphertext pair attack

the progressive cycling through key:value pair sets is certainly sufficient to eschew statistical analysis.


This code is clearly a work in progress, and I hope to resolve the unfinished portions of it at some point
*/


//imports some functions for doing thinnnngs
import java.util.*;

//is a cryptic thing
public class Crypter {

	//does the cryptic stuff
	public static void main(String[] args) {
		
		//instance self
		Crypter crypter = new Crypter();
		//instance self controller
		CryptCtrl cryptCtrl0 = crypter.new CryptCtrl();
		//run control function
		cryptCtrl0.Ctrl();
		
		
	}
	
	//controller class, arriess out primary actions/services of program with its methods and subclasses
	
	public class CryptCtrl {
	
		int[] nonce = new int[4];
				
		
		
		//Sets up cryptographic system control object
		//gets our input for key generation, calculates the values it will use from the values that are input
		//adds 1 if number is even, multiplies values by 7 and modulates by 33 to carry this out. 
		//I found that using odd numbers improved speed and reduced likelihood of infinite loops
		
		
		
		
		CryptCtrl(){
			System.out.println("Hello user!\n\nWelcome to the crypter!\nLet's get cryptin!\n");
			Scanner scan0 = new Scanner(System.in);
		
			for (int i = 0; i<4; i ++) {
				int usrVal;
				System.out.println("You were given four positve integer nonce values, what is value #"+(i+1)+"??\n");
				String response = scan0.nextLine();
			
				try {
					usrVal = Integer.parseInt(response);
					
					//makes negative user input positive
					if(usrVal<0) {
						usrVal = -usrVal;
						System.out.println("You must have meant "+ usrVal+"! Let's just use that.\n");
					}
					//using this if i=3 modulate usrVal to allow limit of key array size
					//minimum of 5, max of 24, sets nonce3
					if(i==3) {
						usrVal%=20;
						usrVal+=5;
						nonce[i] = usrVal;
					}
					//creates emergence in nonce values 1 and 2, where they are seeded by nonce0
					if(i>0) {
						usrVal*=nonce[i-1];
						usrVal*=(i+1);
					}
					//maintains oddness
					else if(usrVal%2==0) {
						usrVal+=1;

					}
					//sets nonce if not i == 3
					else {
						nonce[i]=usrVal;
						System.out.println("Value accepted!");
					}
				}
			catch (Exception InputMismatchException) {
					System.out.println("\n\nThat's not an integer number!!");
				}
			}
			System.out.println("Looks like those settings will work.");
			
		scan0.close();
		}
		
		
		
		
		
		
		
		//Ctrl() method is primary action after crypcontrol sets up with its nonce
		//Ctrl() begins by creating a cryptbox withe the nonce the user provided
		//this runs the code in CryptBox, which creates arrays of keyphrases for the encryption and decryption process
		
		void Ctrl(){
			

//################# I WAS UNABLE TO GET THE SCANNER HERE TO WORK  TO GET A CUSTOM MESSAGE FROM USER, RAN OUT OF TIME TO MAKE IT HAPPEN########
//Error was NoSuchElementException: No line found when trying to scan for new user input...

			//boolean running = true;
			/*
			while(running) {
				Scanner scan1 = new Scanner(System.in);
				String message = "";
				boolean decrypt = false;
				boolean set0 = false;
				boolean set1 = false;
				boolean set2 = false;
				String response1 = "";
				String response2 = "";
				while(!set0) {
					while(!set1) {
						System.out.println("Are we encrypting a message or decrypting a message today?\n");
						response1 = scan1.nextLine();
						System.out.println("\nEnter E for encrypt and D for decrypt.\n");
						if(!response1.toLowerCase().contentEquals("d")) {
							decrypt = true;
							set1 = true;
						}
						if(!response1.toLowerCase().contentEquals("e")) {
							set1 = true;
						}
						else{
							System.out.println("Unrecognized response...\n");
						}
						//scan1.close();
					}
					while(!set2) {
						System.out.println("\n\nWhat is the message you would like to process?");
						response1 = scan1.nextLine();
						System.out.println("\n\nExcellent!");
						message = response2;
						set2 = true;
					}
					scan1.close();
					set0=true;
				}
				*/


				
//Since I could not get the control system to work as I was hoping or develop that further, 
//I made a quick demonstration that encrypts and decrypts a preset message using the nonce the user selects during setup.			

			
			CryptBox cryptbox = new CryptBox(nonce);

			System.out.println("\n\n\nCRYPTOGRAPHIC FUNCTIONS LOADED\n\n\n\n\nSYSTEM READY FOR ENCRYPTION\n\n\n\n\n\n\n\n\nDO NOT LOSE YOUR NONCE WORDS THEY ARE THE ONLY HUMAN READABLE KEY TO YOUR MESSAGES");
				String message="Greetings fellow cryptographers! I hope that your messages are secure and authentic this morrow!";
				String messageCrypt = "";
				System.out.println("\nLet's demonstrait cryptography on this message:\n"+message);
				System.out.println("\n\nProcessing....\n\n\n");
				if(true) {
					String messageOut = cryptbox.Encrypt(message);
					System.out.println("--==Message Encrypted==--");
					System.out.println(messageOut);
					messageCrypt = messageOut;
				}
				if(true) {
					String messageOut = cryptbox.Decrypt(messageCrypt);
					System.out.println("--==Message Decrypted==--");
					System.out.println(messageOut);
				}
					
					
				
		}
		
		
		
		
		
		
		
		
		public class CryptBox {
			
			//gonna need an alphabet of sorts
			final String alph = "abcdefghijklmnopqrstuvwxyz ,.!?$#@()-_=+[]:;0123456789";
			
			//sets up holders for nonce array and alphKeys arrays
			//I was trying to get the array size of the alphKeys array to set dynamically but I was having errors I couldn't figure out at the time
			private int[] nonce = new int[4];
			private String[] alphKeys = new String[32];
			
			CryptBox(int[] nonceIn){
			
			//sets the nonce inside the box
				nonce=nonceIn;
			//creates keys for the box	
				
				alphKeys = SetupKeys();				
			}
			String[] SetupKeys(){
				for(int i=0;i<nonce[3];i++) {
					String genString = "";
					boolean generating = true;
					int tick = 0;
					int offset = 0;
					while(generating) {
						
						//doing some simple proceedural math here, introduced an offset and a tick that osccilates to prevent infinite loops
						// while still insuring different outcomes per key in a key set
						
						// creating a value to use to select which letter will go into key string next
						
						int genVal = Math.abs( ((nonce[0]-nonce[1]+nonce[2]) - tick * offset) + nonce[1] * tick + (i*3) )% alph.length();
						String genChar = "";
						try {
						genChar=alph.substring(genVal, genVal+1);
						}
						catch(StringIndexOutOfBoundsException e){
							System.out.print("oops!");
						}
						
						//cheks if generated Char is already in key string, appends it if not
						if(!genString.contains(genChar)) {
							genString = genString+genChar;
						}
						//stops process when whole message is encoded
						if(genString.length()==alph.length()) {
							generating = !generating;
						}
						
						//tick and offset changes
						if(tick<=8) {
						tick++;
						}
						if(tick>8) {
							tick-=16;
							offset+=1;
						}
						
					}
					System.out.print(genString);
					alphKeys[i] = genString;
				}
				return alphKeys;
			}

			
			//cleans input to be all lower case
			//individually encrypts characters, building an output string
			//cycles through table set in order and translates using indexof from original alph to current
			String Encrypt(String messageIn){
				String message = messageIn.toLowerCase();
				String returnMessage = "";
				int count = 0;
				while(returnMessage.length()<message.length()) {
					String currKey = alphKeys[count%nonce[3]];
					String currChar = message.substring(count,count+1);
					String cryptChar =currKey.substring(alph.indexOf(currChar),alph.indexOf(currChar)+1);
					returnMessage+= cryptChar;
					count++;
				}
				return returnMessage;
			}

			
			//inverse of the above
			String Decrypt(String messageIn) {
				String message = messageIn.toLowerCase();   //in case we want to add a scrambler that randomizes casing of letters in cyphertext to make encryption apparently more complex while remaining simple ;)
				String returnMessage = "";
				int count = 0;
				while(returnMessage.length()<message.length()) {
					String currKey = alphKeys[count%nonce[3]];
					String currChar = message.substring(count,count+1);
					String plainChar = alph.substring(currKey.indexOf(currChar),currKey.indexOf(currChar)+1);
					returnMessage+= plainChar;
					count++;
				}
				return returnMessage;
			}
		}
	}
}
