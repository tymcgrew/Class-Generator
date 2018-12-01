import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class ClassProperty {


	//============================================= Properties
	private String name;
	private String type;
	private String scope;
	private boolean getter;
	private String getterScope;
	private boolean setter;
	private String setterScope;

	//============================================= Constructors
	public ClassProperty (String name, String type, String scope, boolean getter, String getterScope, boolean setter, String setterScope) {
		setName(name);
		setType(type);
		setScope(scope);
		setGetter(getter);
		setGetterScope(getterScope);
		setSetter(setter);
		setSetterScope(setterScope);		
	}

	public ClassProperty (RandomAccessFile rafReader) throws Exception{
		this (
				Utilities.nextLineData(rafReader),
				Utilities.nextLineData(rafReader),
				Utilities.nextLineData(rafReader),
				Utilities.nextLineData(rafReader).equalsIgnoreCase("true"),
				Utilities.nextLineData(rafReader),
				Utilities.nextLineData(rafReader).equalsIgnoreCase("true"),
				Utilities.nextLineData(rafReader)
				);
	}

	//============================================= Methods
	public void writeProperty(PrintWriter pw) {
		if (scope.equalsIgnoreCase("private"))
			pw.print("\tprivate ");
		else
			pw.print("\tpublic ");
		pw.print(type + " ");
		pw.println(name + ";");
	}

	public void writeHeader(PrintWriter pw) {
		pw.print(type + " " + name);
	}
	
	public void writeSetterCaller(PrintWriter pw) {
		pw.println("\t\t" + "set" + name.substring(0,1).toUpperCase() + name.substring(1) + "(" + name + ");");
	}
	
	public void writeEmptySetterCaller(PrintWriter pw) {
		if (type.equalsIgnoreCase("String"))
			pw.print("\"\"");
		else if (type.equals("int"))
			pw.print("0");
		else if (type.equals("double"))
			pw.print("0.0");
		else if (type.equals("boolean"))
			pw.print("false");
		else if (type.equals("char")) 
			pw.print("\u0000");
		else
			pw.print("null");
	}
	
	public void writeCopySetterCaller(PrintWriter pw, String objName) {
		pw.print(objName + "." + name);		
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

	public void writeSetter(PrintWriter pw) {
		if (setter == false)
			return;
		if (setterScope.equals("public"))
			pw.print("\tpublic ");
		else
			pw.print("\tprivate ");
		pw.println(type + " set" + name.substring(0,1).toUpperCase() + name.substring(1) + "(" + type + " " + name + ") {");
		pw.println("\t\tthis." + name + " = " + name + ";");
		pw.println("\t}");
	}

	@Override
	public String toString() {
		return String.format(
				"ClassProperty [name=%s, type=%s, scope=%s, getter=%s, getterScope=%s, setter=%s, SetterScope=%s]",
				name, type, scope, getter, getterScope, setter, setterScope);
	}

	//============================================= Getters and Setters
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getScope() {
		return scope;
	}

	public boolean isGetter() {
		return getter;
	}

	public String getGetterScope() {
		return getterScope;
	}

	public boolean isSetter() {
		return setter;
	}

	public String getSetterScope() {
		return setterScope;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public void setGetter(boolean getter) {
		this.getter = getter;
	}

	public void setGetterScope(String getterScope) {
		this.getterScope = getterScope;
	}

	public void setSetter(boolean setter) {
		this.setter = setter;
	}

	public void setSetterScope(String setterScope) {
		this.setterScope = setterScope;
	}



}
