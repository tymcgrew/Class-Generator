import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class InterfaceInfo {

	//============================================= Properties
	private String name;
	private ArrayList<String> methods;
	
	
	//============================================= Constructors
	public InterfaceInfo(String name, ArrayList<String> methods) {
		setName(name);
		setMethods(methods);		
	}
	
	public InterfaceInfo(RandomAccessFile rafReader) throws Exception {
		this(Utilities.nextLineData(rafReader), new ArrayList<String>());
		constructMethods(rafReader);
	}
	
	//============================================= Methods
	private void constructMethods(RandomAccessFile rafReader) throws Exception {
		boolean cont = true;
		while (cont) {
			String line = rafReader.readLine();
			if (line.split(":")[0].trim().equals("Method")) {
				String method = line.split(":")[1].trim();
				methods.add(method);
			}
			else 
				cont = false;
		}
	}
	
	public void writeInterface(PrintWriter pw) throws Exception {
		String newline = System.getProperty("line.separator");
		pw.println("/*" +newline+ " * This file was written by the Java Class Generator" +newline+" * @Tyler McGrew" +newline+ " * 11/3/2018" +newline+ " */" +newline);
		pw.println("public interface " + name + " {" + newline);
		for (String m : methods) {
			pw.println("\t" + m);
		}
		pw.println();
		pw.print("}");
	}
	
	//============================================= Getters & Setters
	public String getName() {
		return name;
	}
	public ArrayList<String> getMethods() {
		return methods;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMethods(ArrayList<String> methods) {
		this.methods = methods;
	}
	
}
