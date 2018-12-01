import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class ClassInfo {


	//============================================= Properties
	private String name;
	private boolean abstrct;
	private String extnds;
	private String[] implemnts;
	private boolean cloneMethod;
	private boolean emptyConstructor;
	private boolean workhorseConstructor;
	private boolean copyConstructor;
	private ArrayList<ClassProperty> classProperties;

	//============================================= Constructors
	public ClassInfo (String name, boolean abstrct, String extnds, String[] implemnts, boolean cloneMethod, boolean emptyConstructor, boolean workhorseConstructor, boolean copyConstructor, ArrayList<ClassProperty> classProperties) {
		setName(name);
		setAbstrct(abstrct);
		setExtnds(extnds);
		setImplemnts(implemnts);
		setCloneMethod(cloneMethod);
		setEmptyConstructor(emptyConstructor);
		setWorkhorseConstructor(workhorseConstructor);
		setCopyConstructor(copyConstructor);
		setProperties(classProperties);
	}

	public ClassInfo (RandomAccessFile rafReader) throws Exception{
		this (
				Utilities.nextLineData(rafReader),                                // name
				Utilities.nextLineData(rafReader).equalsIgnoreCase("true"),       // abstrct
				Utilities.nextLineData(rafReader),                                // extnds
				Utilities.nextLineData(rafReader).split(","),                     // implemnts
				Utilities.nextLineData(rafReader).equalsIgnoreCase("true"),       // cloneMethod
				Utilities.nextLineData(rafReader).equalsIgnoreCase("true"),       // emptyConstructor
				Utilities.nextLineData(rafReader).equalsIgnoreCase("true"),       // workhorseConstructor
				Utilities.nextLineData(rafReader).equalsIgnoreCase("true"),       // copyConstructor
				new ArrayList<ClassProperty>()                                         // classProperties
				);
		constructProperties(rafReader);

	}

	//============================================= Methods
	private void constructProperties(RandomAccessFile rafReader) throws Exception {
		while(rafReader.readLine().trim().equals("Property:")) {
			classProperties.add(new ClassProperty(rafReader));
		}
	}

	public void writeClass(PrintWriter pw) throws Exception {
		String newline = System.getProperty("line.separator");

		//========================================= Write Header
		pw.println("/*" +newline+ " * This file was written by the Java Class Generator" +newline+" * @Tyler McGrew" +newline+ " * 11/3/2018" +newline+ " */" +newline);
		pw.print("public ");
		if (abstrct)
			pw.print("abstract ");
		pw.print("class " + name + " ");
		if (!extnds.equals(""))
			pw.print("extends " + extnds + " ");
		if (!implemnts[0].equals("")) {
			pw.print("implements ");
			for (int i = 0; i < implemnts.length; i++) {
				pw.print(implemnts[i].trim());
				if (i != implemnts.length-1)
					pw.print(", ");
				else
					pw.println(" {" + newline);
			}
		}
		else 
			pw.println("{");

		//========================================= Write Properties
		pw.println("\t//================================================= Properties");
		for (ClassProperty p : classProperties) {
			p.writeProperty(pw);
		}
		pw.println(newline);

		//========================================= Write Constructors
		pw.println("\t//================================================= Constructors");

		//Workhorse Constructor
		if (workhorseConstructor) {
			pw.println("\t//Workhorse Constructor");
			pw.print("\tpublic " + name + "(");
			for (int i = 0; i < classProperties.size(); i++) {
				classProperties.get(i).writeHeader(pw);
				if (i != classProperties.size()-1)
					pw.print(", ");
			}
			pw.println(") {");
			for (ClassProperty p : classProperties) {
				p.writeSetterCaller(pw);
			}
			pw.println("\t}" + newline);
		}

		//Empty Constructor
		if (emptyConstructor) {
			pw.println("\t//Empty Constructor");
			pw.println("\tpublic " + name + "() {");
			pw.print("\t\tthis(");
			for (int i = 0; i < classProperties.size(); i++) {
				classProperties.get(i).writeEmptySetterCaller(pw);
				if (i != classProperties.size()-1)
					pw.print(", ");
			}
			pw.println(");");
			pw.println("\t}" + newline);
		}

		//Copy Constructor
		if(copyConstructor) {
			pw.println("\t//Copy Constructor");
			pw.println("\tpublic " + name + "(" + name + " " + name.substring(0,1).toLowerCase() + name.substring(1) + ") {");
			pw.print("\t\tthis(");
			for (int i = 0; i < classProperties.size(); i++) {
				classProperties.get(i).writeCopySetterCaller(pw, name.substring(0,1).toLowerCase() + name.substring(1));
				if (i != classProperties.size()-1)
					pw.print(", ");
			}
			pw.println(");");
			pw.println("\t}" + newline);
		}
		
		pw.println();
		
		//========================================= Write Methods
		pw.println("\t//================================================= Methods");
		
		if (cloneMethod) {
			pw.println("\t//Clone Method");
			pw.println("\tpublic " + name + " clone() {");
			pw.println("\t\treturn new " + name + "(this);");
			pw.println("\t}");
		}
		
		pw.println();
		
		//========================================= Write Getters & Setters
		pw.println("\t//================================================= Getters & Setters");
		for (ClassProperty p : classProperties) {
			p.writeGetter(pw);			
		}
		for (ClassProperty p : classProperties) {
			p.writeSetter(pw);
		}
		
		pw.println();
		pw.print("}");
	}

	//============================================= Getters and Setters
	public String getName() {
		return name;
	}

	public boolean isAbstrct() {
		return abstrct;
	}

	public String getExtnds() {
		return extnds;
	}

	public String[] getImplemnts() {
		return implemnts;
	}

	public boolean isCloneMethod() {
		return cloneMethod;
	}

	public boolean isEmptyConstructor() {
		return emptyConstructor;
	}

	public boolean isWorkhorseConstructor() {
		return workhorseConstructor;
	}

	public boolean isCopyConstructor() {
		return copyConstructor;
	}

	public ArrayList<ClassProperty> getProperties() {
		return classProperties;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setAbstrct(boolean abstrct) {
		this.abstrct = abstrct;
	}


	public void setExtnds(String extnds) {
		this.extnds = extnds;
	}


	public void setImplemnts(String[] implemnts) {
		this.implemnts = implemnts;
	}


	public void setCloneMethod(boolean cloneMethod) {
		this.cloneMethod = cloneMethod;
	}


	public void setEmptyConstructor(boolean emptyConstructor) {
		this.emptyConstructor = emptyConstructor;
	}


	public void setWorkhorseConstructor(boolean workhorseConstructor) {
		this.workhorseConstructor = workhorseConstructor;
	}


	public void setCopyConstructor(boolean copyConstructor) {
		this.copyConstructor = copyConstructor;
	}


	public void setProperties(ArrayList<ClassProperty> classProperties) {
		this.classProperties = classProperties;
	}


}
