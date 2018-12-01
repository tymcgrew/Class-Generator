/*
 * 
 * 
 */

import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class Tester {

	public static void main(String[] args) {

	
		
		
		RandomAccessFile rafReader = null;
		PrintWriter pw = null;

		try {
			//================================================================= Parse next line of input file
			rafReader = new RandomAccessFile("input.txt","r");
			rafReader.readLine();
			while (rafReader.getFilePointer() < rafReader.length()) {
				String nextLine = rafReader.readLine().trim();
				
				//============================================================= Read a class
				if (nextLine.equals("Class:")) {
					ClassInfo c = new ClassInfo(rafReader);
					System.out.println("class read");
					
					//========================================================= Write the class
					pw = new PrintWriter(c.getName() + ".java");
					c.writeClass(pw);
					System.out.println("class written");
					try {pw.close();} catch (Exception e) {e.printStackTrace();}
				}

				//============================================================= Read an enum
				else if (nextLine.equals("Enumeration:")) {
					EnumInfo enu = new EnumInfo(rafReader);
					System.out.println("enum read");

					//========================================================= Write the enum
					pw = new PrintWriter(enu.getName() + ".java");
					enu.writeEnum(pw);
					System.out.println("enum written");
					try {pw.close();} catch (Exception e) {e.printStackTrace();}
				}
				
				//============================================================= Read an interface
				else if (nextLine.equals("Interface:")) {	
					InterfaceInfo i = new InterfaceInfo(rafReader);
					System.out.println("interface read");
					
					//========================================================= Write the interface
					pw = new PrintWriter(i.getName() + ".java");
					i.writeInterface(pw);
					System.out.println("interface written");
					try {pw.close();} catch (Exception e) {e.printStackTrace();}
				}

				//============================================================= If next line is not a class, enum, or interface- error out 
				else {
					System.out.println(" ------- Text Parsing Error!!!! -------- ");
					System.out.println("Next line ---> " + nextLine);
					System.exit(-1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {rafReader.close();} catch (Exception e) {}
		}
	}
	
}
