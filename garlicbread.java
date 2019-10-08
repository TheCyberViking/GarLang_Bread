import java.io.*;
import java.util.*;

/** GarlicBread Interpretator V1.0
	Author @TheCyberVIking
	
**/
public class garlicbread{
	
	String[] tokens;
	
	byte[] data;

	BufferedReader fileReader;
	
	FileOutputStream fos;
	
	InputStreamReader consoleIn;
	
	File f;
	File f_out;
	Scanner in = new Scanner(System.in);
	
	int dataPointer = 0;
	int entryPointer = 0;
	
	int lineCount = 0;
	
	String fileName;

	
	public garlicbread(File f, String Name){
		try{
			this.f = f;
			fileReader = new BufferedReader(new FileReader(f));
			String content = "";
			String line = "";
			int length = 0;
			File f_out = new File(Name + "_out.txt");
			f_out.createNewFile();
			fos = new FileOutputStream(f_out);
			while((line = fileReader.readLine()) != null){
				content += line + " ";
				lineCount++;
			}
			interpret(content);
			fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public garlicbread(File f, File f_out){
		try{
			this.f = f;
			this.f_out = f_out;
			f_out.createNewFile();
			fileReader = new BufferedReader(new FileReader(f));
			String content = "";
			String line = "";
			fos = new FileOutputStream(f_out);
			while((line = fileReader.readLine()) != null){
				content += line;
				lineCount++;
			}
			interpret(content);
			fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void interpret(String s){
		System.out.println(s);
		System.out.println("Tokenizing the input");
		String[] tokens = tokenizer(s);
		data = new byte[genCells(tokens)];
		for(; entryPointer < tokens.length; entryPointer++){
			interpret(tokens[entryPointer], tokens);
		}
	}
	
	public int genCells(String[] a){
		int count = 1;
		for(int i = 0; i < a.length; i++){
			if(a[i].equals("gb")){
				count++;
			}else if(a[i].equals("garlic")){
				count--;
			}
		}
		return count;
	}
	
	public String[] tokenizer(String s){
		String temp = "";
		System.out.println(s);
		System.out.println("String is " + s.length() + " characters long");
		ArrayList<String> tokens = new ArrayList<String>();
		for(int i = 0; i < s.length(); i++){
			temp += s.charAt(i);
			System.out.println(temp);
			if(temp.equals("gb")){
				tokens.add(temp);
				System.out.println(temp);
				temp = "";
			}else if(temp.equals("garlic")){
				tokens.add(temp);
				System.out.println(temp);
				temp = "";
			}else if(temp.equals("garbred")){
				tokens.add(temp);
				System.out.println(temp);
				temp = "";
			}else if(temp.equals("agarlic")){
				tokens.add(temp);
				System.out.println(temp);
				temp = "";
			}else if(temp.equals("gonbread")){
				tokens.add(temp);
				System.out.println(temp);
				temp = "";
			}else if(temp.equals("gaaaar")){
				tokens.add(temp);
				System.out.println(temp);
				temp = "";
			}else if(temp.equals("ogarbred")){
				tokens.add(temp);
				System.out.println(temp);
				temp = "";
			}else if(temp.equals("aagarlic")){
				tokens.add(temp);
				System.out.println(temp);
				temp = "";
			}else if(temp.equals(" ")){
				temp = "";
			}
		}
		
		System.out.println(tokens.size() + " entries in the array!");
		
		String[] complete = tokens.toArray(new String[tokens.size()]);
		for(int j = 0; j < complete.length; j++){
			System.out.print(complete[j] + " ");
		}
		return complete;
	}
	
	public void interpret(String s, String[] sa){
		System.out.println(s);
		switch(s){
			case "gb":
				if((dataPointer + 1) > data.length){
					System.out.println("Error on line " + lineCount + "\n data pointer " + dataPointer + " on postion " + entryPointer + " is out of range.");
					System.exit(1);
				}else{
					dataPointer++;
				}
				break;
			
			case "garlic":
				if((dataPointer - 1) < 0){
					System.out.println("Error on line " + lineCount + "\n data pointer " + dataPointer + " on postion " + entryPointer + " is negative.");
					System.exit(1);
				}else{
					dataPointer--;
				}
				break;
			
			case "garbred":
				data[dataPointer]++;
				break;
				
			case "agarlic":
				data[dataPointer]--;
				break;
			
			case "gonbread":
				if(data[dataPointer] == 0){
					int i = 1;
					while(i > 0){
						String s2 = sa[++entryPointer];
						if(s2.equals("gonbread")){
							i++;
						}else if(s2.equals("gaaaar")){
							i--;
						}
						System.out.println(i + "  " + s2);
					}
				}
				break;
				
			case "gaaaar":
				int i = 1;
				while(i > 0){
					String s2 = sa[--entryPointer];
					if(s2.equals("gonbread")){
						i--;
					}else if(s2.equals("gaaaar")){
						i++;
					}
					System.out.println(i + " " + s2);
				}
				entryPointer--;
				break;
			
			case "ogarbred":
			try{
				fos.write((char) data[dataPointer]);
			}catch(Exception e){
				e.printStackTrace();
			}
				break;
				
			case "aagarlic":
				data[dataPointer] = (byte) in.next().charAt(0);
				break;
		}
	}
	
	public static void main(String[] args){
		try{
			if(args[0].contains(".gbread")){
				if(args.length == 1){
					File f = new File(args[0]);
					garlicbread w = new garlicbread(f, f.getName());
				}else if(args.length == 2){
					File f = new File(args[0]);
					File f1 = new File(args[1]);
					garlicbread w = new garlicbread(f, f1);
				}					
			}else{
				System.out.println("Invalid file!  Please use a .gbread file!");
			}
		}catch(Exception e){
			System.out.println("Usage: java garlicbread <Input file> [output file]");
		}
	}
}