import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Type {

	//============================================= Properties
	private String name;
	private String[] dataTypes;
	private ArrayList<String> data;
	
	//============================================= Constructors
	public Type(String name, String[] dataTypes, ArrayList<String> data) {
		setName(name);
		setDataTypes(dataTypes);
		setData(data);
	}
	
	public Type(RandomAccessFile rafReader) throws Exception {
		this(Utilities.nextLineData(rafReader), Utilities.nextLineData(rafReader).split(","), new ArrayList<String>());
		constructData(rafReader);
	}
	
	
	//============================================= Methods
	private void constructData(RandomAccessFile rafReader) throws Exception {
		boolean cont = true;
		while (cont) {
			String line = rafReader.readLine();
			if (line.split(":")[0].trim().equals("Data")) {
				String dataPiece = line.split(":")[1].trim();
				data.add(dataPiece);
			}
			else 
				cont = false;
		}
	}
	
	public void writeEnum(PrintWriter pw) {
		pw.print("\t" + name + " (");
		for (int i = 0; i < dataTypes.length; i++) {
			if (dataTypes[i].equals("String")) 
				pw.print("\"" + data.get(i) + "\"");
			else 
				pw.print(data.get(i));
			if (i != dataTypes.length-1)
				pw.print(", ");
		}
		pw.print(")");
	}
	
	//============================================= Getters & Setters
	public String getName() {
		return name;
	}
	public String[] dataTypes() {
		return dataTypes;
	}
	public ArrayList<String> getData() {
		return data;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDataTypes(String[] dataTypes) {
		this.dataTypes = dataTypes;
	}
	public void setData(ArrayList<String> data) {
		this.data = data;
	}
	
}
