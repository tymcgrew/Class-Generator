import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class EnumInfo {

	//============================================= Properties
	private String name;
	private ArrayList<EnumProperty> enumProperties;
	private ArrayList<Type> types;

	//============================================= Constructors
	public EnumInfo(String name, ArrayList<EnumProperty> enumProperties, ArrayList<Type> types) {
		setName(name);
		setEnumProperties(enumProperties);
		setTypes(types);
	}

	public EnumInfo(RandomAccessFile rafReader) throws Exception {
		this(Utilities.nextLineData(rafReader), new ArrayList<EnumProperty>(), new ArrayList<Type>());
		construct(rafReader);
	}

	//============================================= Methods
	private void construct(RandomAccessFile rafReader) throws Exception {
		boolean cont = true;
		while (cont) {
			String line = rafReader.readLine();
			if (line.trim().equals("Property:")) {
				enumProperties.add(new EnumProperty(rafReader));
			}
			else if (line.trim().equals("Type:")) {
				types.add(new Type(rafReader));
			}
			else
				cont = false;
		}
	}

	public void writeEnum(PrintWriter pw) {
		String newline = System.getProperty("line.separator");
		pw.println("/*" +newline+ " * This file was written by the Java Class Generator" +newline+" * @Tyler McGrew" +newline+ " * 11/3/2018" +newline+ " */" +newline);
		pw.println("public enum " + name + " {" + newline);

		pw.println("\t//================================================= Enumerations");
		for (int i = 0; i < types.size(); i++) {
			types.get(i).writeEnum(pw);
			if (i != types.size()-1)
				pw.println(",");
			else
				pw.println(";");
		}
		pw.println(newline);

		pw.println("\t//================================================= Properties");
		for (EnumProperty p : enumProperties) {
			p.writeEnumProperty(pw);
		}
		pw.println(newline);


		pw.println("\t//================================================= Constructors");
		pw.print("\tprivate " + name + "(");
		for (int i = 0; i < enumProperties.size(); i++) {
			enumProperties.get(i).writeHeader(pw);
			if (i != enumProperties.size()-1)
				pw.print(", ");
		}
		pw.println(") {");
		for (EnumProperty p : enumProperties) {
			p.writeSetter(pw);
		}
		pw.println("\t}");
		pw.println(newline);

		pw.println("\t//================================================= Getters");
		for (EnumProperty p : enumProperties) {
			p.writeGetter(pw);			
		}
		
		pw.println(newline + "}");

	}
	//============================================= Getters & Setters
	public String getName() {
		return name;
	}
	public ArrayList<EnumProperty> getProperties() {
		return enumProperties;
	}
	public ArrayList<Type> getTypes() {
		return types;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEnumProperties(ArrayList<EnumProperty> enumProperties) {
		this.enumProperties = enumProperties;
	}
	public void setTypes(ArrayList<Type> types) {
		this.types = types;
	}

}
