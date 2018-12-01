import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class EnumProperty {

	//============================================= Properties
	private String name;
	private String type;
	private boolean getter;
	private String getterScope;

	//============================================= Constructors
	public EnumProperty(String name, String type, boolean getter, String getterScope) {
		setName(name);
		setType(type);
		setGetter(getter);
		setGetterScope(getterScope);		
	}

	public EnumProperty(RandomAccessFile rafReader) throws Exception {
		this (
				Utilities.nextLineData(rafReader),
				Utilities.nextLineData(rafReader),
				Utilities.nextLineData(rafReader).equalsIgnoreCase("true"),
				Utilities.nextLineData(rafReader)
				);
	}

	//============================================= Methods
	public void writeEnumProperty(PrintWriter pw) {
		pw.println("\tprivate " + type + " " + name + ";");
	}

	public void writeHeader(PrintWriter pw) {
		pw.print(type + " " + name);
	}

	public void writeSetter(PrintWriter pw) {
		pw.println("\t\t" + "this." + name + " = " + name + ";");
	}

	public void writeGetter(PrintWriter pw) {
		if (getter == false)
			return;
		if (getterScope.equals("public"))
			pw.print("\tpublic ");
		else
			pw.print("\tprivate ");
		pw.println(type + " get" + name.substring(0,1).toUpperCase() + name.substring(1) + "() {");
		pw.println("\t\treturn " + name + ";");
		pw.println("\t}");
	}

	//============================================= Getters & Setters
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public boolean isGetter() {
		return getter;
	}
	public String getGetterScope() {
		return getterScope;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setGetter(boolean getter) {
		this.getter = getter;
	}
	public void setGetterScope(String getterScope) {
		this.getterScope = getterScope;
	}


}
